package controller;

import event.PublicEvent;
import java.awt.Color;
import java.awt.Container;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.IOException;
import java.time.DateTimeException;
import java.time.LocalDate;
import java.util.Enumeration;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.AbstractButton;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JRadioButton;
import model.ModelSendMessage;
import model.ModelUser;
import service.Client;
import swing.Avatar;
import swing.PassField;
import swing.TextField;
import swing.button.toggle.SwitchButton;
import swing.combobox.ComboBoxSuggestion;
import utilites.UserAction;
import view.MainSystem;
import view.components.InfoUser;
import view.login.Login;

public class ControllerInfo implements ActionListener {

    private InfoUser panel;
    private Avatar avatar;
    private SwitchButton isChange;
    private TextField txtName;
    private ComboBoxSuggestion cbbDay;
    private ComboBoxSuggestion cbbMonth;
    private ComboBoxSuggestion cbbYear;
    private ButtonGroup btnG;
    private PassField pass;
    private PassField newPass;
    private JLabel msgErr;

    private String imageExtension = "";

    public ControllerInfo(
            InfoUser panel,
            Avatar avatar,
            SwitchButton isChange,
            TextField txtName,
            ComboBoxSuggestion cbbDay,
            ComboBoxSuggestion cbbMonth,
            ComboBoxSuggestion cbbYear,
            ButtonGroup btnG,
            PassField pass,
            PassField newPass,
            JLabel msgErr
    ) {
        this.panel = panel;
        this.avatar = avatar;
        this.isChange = isChange;
        this.txtName = txtName;
        this.cbbDay = cbbDay;
        this.cbbMonth = cbbMonth;
        this.cbbYear = cbbYear;
        this.btnG = btnG;
        this.pass = pass;
        this.newPass = newPass;
        this.msgErr = msgErr;
        avatar.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                try {
                    chooseImage();
                } catch (IOException ex) {
                    Logger.getLogger(ControllerInfo.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
    }

    private void chooseImage() throws IOException {
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);

        int returnValue = fileChooser.showOpenDialog(panel);
        if (returnValue == JFileChooser.APPROVE_OPTION) {
            File file = fileChooser.getSelectedFile();
            Image img = ImageIO.read(file);

            String fileName = file.getName();
            imageExtension = fileName.substring(fileName.lastIndexOf(".") + 1);
            avatar.setIcon(new ImageIcon(img));
            avatar.repaint();
            avatar.revalidate();
        }
    }

    int i = 0;

    @Override
    public void actionPerformed(ActionEvent e) {
        String command = e.getActionCommand();
        if (command.equals("Remove")) {
            delete();
        } else if (command.equals("Update")) {
            update();

        } else if (command.equals("Cancel")) {
        }
    }

    public void delete() {
        ModelUser dlt = new ModelUser();
        dlt.setUserID(Client.getInstance().getUser().getUserID());
        ModelSendMessage data = new ModelSendMessage(dlt, UserAction.DELETE_USER);

        data.setTo(-1);
        data.setFrom(dlt.getUserID());
        PublicEvent.getInstance().getEventToServer().send(data);

        new Thread(() -> {
            try {
                msgErr.setForeground(new Color(255, 0, 0));
                msgErr.setText("Delete successfully (exit after 3s)");
                Thread.sleep(1000);
                msgErr.setText("Delete successfully (exit after 2s)");
                Thread.sleep(1000);
                msgErr.setText("Delete successfully (exit after 1s)");
                Thread.sleep(1000);

                Container com = panel.getParent();
                while (!(com instanceof MainSystem)) {
                    com = com.getParent();
                }

                Client.getInstance().getSocket().close();
                Login login = new Login();
                login.setVisible(true);
                ((MainSystem) com).dispose();
            } catch (InterruptedException ex) {
                Logger.getLogger(ControllerInfo.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IOException ex) {
                Logger.getLogger(ControllerInfo.class.getName()).log(Level.SEVERE, null, ex);
            }
        }).start();
    }

    public void update() {
        msgErr.setForeground(new Color(255, 0, 0));
        String name = txtName.getText();
        try {
            
            LocalDate date = LocalDate.of(
                    Integer.parseInt(cbbYear.getSelectedItem() +""), 
                    Integer.parseInt(cbbMonth.getSelectedItem() +""), 
                    Integer.parseInt(cbbDay.getSelectedItem() +"")
            );
            ModelUser oldUser = Client.getInstance().getUser();
            ModelUser newUser = new ModelUser(oldUser.getUserID(), oldUser.getUserName(), oldUser.getDate(), oldUser.getEmail(), oldUser.getGender(), oldUser.getImageString(), oldUser.getPassword(), oldUser.isStatus());

            newUser.setUserName(name);
            newUser.setDate(date);
            newUser.setGender(getSelected());

            if (!imageExtension.isEmpty()) {
                String imgString = PublicEvent.getInstance().getEventEncrypt().encodeImage(((ImageIcon) avatar.getIcon()).getImage(), imageExtension);
                newUser.setImageString(imgString);
            }

            if (!name.isEmpty()) {
                if (isChange.isSelected()) {

                    if (String.valueOf(pass.getPassword()).equals(newUser.getPassword())) {
                        String newp = String.valueOf(newPass.getPassword());
                        if (!newp.equals("")) {
                            msgErr.setForeground(new Color(0, 144, 0));
                            msgErr.setText("Update successful");
                            newUser.setPassword(newp);

                            ModelSendMessage data = new ModelSendMessage(newUser, UserAction.UPDATE_INFO);
                            data.setTo(-1);
                            data.setFrom(newUser.getUserID());
                            PublicEvent.getInstance().getEventToServer().send(data);
                            Client.getInstance().setUser(newUser);

                            PublicEvent.getInstance().getEventUpdate().updateHeader();
                        } else {
                            newPass.grabFocus();
                            msgErr.setText("Password can not be blank");
                        }
                    } else {
                        pass.grabFocus();
                        msgErr.setText("wrong password");
                    }

                } else {
                    msgErr.setForeground(new Color(0, 144, 0));
                    msgErr.setText("Update successful");
                    ModelSendMessage data = new ModelSendMessage(newUser, UserAction.UPDATE_INFO);
                    data.setTo(-1);
                    data.setFrom(newUser.getUserID());

                    Client.getInstance().setUser(newUser);

                    PublicEvent.getInstance().getEventToServer().send(data);
                    PublicEvent.getInstance().getEventUpdate().updateHeader();

                }
            } else {
                txtName.grabFocus();
                msgErr.setText("Name cannot be left blank");
            }
        } catch (DateTimeException e) {
            msgErr.setText(e.getMessage());
        }
    }

    private String getSelected() {
        Enumeration<AbstractButton> buttons = btnG.getElements();
        while (buttons.hasMoreElements()) {
            JRadioButton button = (JRadioButton) buttons.nextElement();
            if (button.isSelected()) {
                return button.getText();
            }
        }
        return "Other";
    }

}