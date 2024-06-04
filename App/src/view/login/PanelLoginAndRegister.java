
package view.login;

import event.PublicEvent;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import javax.swing.AbstractAction;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.KeyStroke;
import model.ModelUser;
import net.miginfocom.swing.MigLayout;
import swing.Button;
import swing.PassField;
import swing.TextField;
import view.components.Notification;

/**
 *
 * @author anhth
 */
public class PanelLoginAndRegister extends javax.swing.JPanel {
    
    private ActionListener event;
    
    private ModelUser user;
    private ModelUser userLogin;
    
    public ModelUser getUser() {
        return user;
    }
    
    public ModelUser getDataLogin(){
        return userLogin;
    }
    
    public PanelLoginAndRegister() {
        initComponents();
        initRegister();
        initLogin();
        login.setVisible(true);
        register.setVisible(false);
    }
    
    private void initRegister() {
        register.setLayout(new MigLayout("wrap", "push[center]push", "push[]25[]10[]10[]10[]25[]push"));
    
        JLabel label = new JLabel("Create Account");
        label.setFont(new Font("sansserif", 1, 30));
        label.setForeground(new Color(7, 150, 222));
        register.add(label);
        
        TextField txtUser = new TextField();
        txtUser.setHint("Name");
        txtUser.setPrefixIcon(new ImageIcon(getClass().getResource("/icon/user_login.png")));
        TextField txtEmail = new TextField();
        txtEmail.setHint("Eamil");
        txtEmail.setPrefixIcon(new ImageIcon(getClass().getResource("/icon/mail.png")));

        PassField txtPassword = new PassField();
        txtPassword.setHint("Password");
        txtPassword.setPrefixIcon(new ImageIcon(getClass().getResource("/icon/password.png")));
        txtPassword.setSuffixIcon(new ImageIcon(getClass().getResource("/icon/eye-off.png")),
                new ImageIcon(getClass().getResource("/icon/eye.png")));

        PassField txtRepassword = new PassField();
        txtRepassword.setHint("Re-Password");
        txtRepassword.setPrefixIcon(new ImageIcon(getClass().getResource("/icon/password.png")));
        txtRepassword.setSuffixIcon(new ImageIcon(getClass().getResource("/icon/eye-off.png")), new ImageIcon(getClass().getResource("/icon/eye.png")));

        
        register.add(txtUser, "w 300");
        register.add(txtEmail, "w 300");
        register.add(txtPassword, "w 300");
        register.add(txtRepassword, "w 300");
        
        Button btn =  new Button();
        btn.setBackground(new Color(7,100,255));     
        btn.setForeground(new Color(250,250,250));
        btn.setText("SIGN UP");
        btn.addActionListener((ActionEvent e) -> {
            event.actionPerformed(e);
        });
        
        Button btnChange = new Button();
        btnChange.setBackground(new Color(7,100,255));
        btnChange.setForeground(new Color(250,250,250));
        btnChange.setText("Or Login");
        btnChange.addActionListener((ActionEvent e) -> {
            event.actionPerformed(e);
        });
        
        register.add(btn, "w 300, h 40");
        register.add(btnChange, "w 300, h 40");

        btn.addActionListener((ActionEvent ae) -> {
            String userName = txtUser.getText().trim();
            String email = txtEmail.getText().trim();
            String pass = String.valueOf(txtPassword.getPassword());
            String repass = String.valueOf(txtRepassword.getPassword());
            if(userName.equals("")){
                txtUser.grabFocus();
                PublicEvent.getInstance().getEventNotificate().showMessage(Notification.MessageType.ERROR, "Name cannot be blank");
            } else if(email.equals("")){
                txtEmail.grabFocus();
                PublicEvent.getInstance().getEventNotificate().showMessage(Notification.MessageType.ERROR, "Email cannot be blank");
            } else if(pass.equals("")){
                txtPassword.grabFocus();
                PublicEvent.getInstance().getEventNotificate().showMessage(Notification.MessageType.ERROR, "Password cannot be blank");
            } else if(!repass.equals(pass)){
                txtRepassword.grabFocus();
                PublicEvent.getInstance().getEventNotificate().showMessage(Notification.MessageType.ERROR, "Incorrect password");

            } else {
                user = new ModelUser(0, userName, email, pass, repass);
            }
        });
    }
    
    private void initLogin() {
        login.setLayout(new MigLayout("wrap", "push[center]push" , "push[]25[]10[]10[]10[]25[]push"));
        JLabel label = new JLabel("Sign In");
        label.setFont(new Font("sansserif", 1, 30));
        label.setForeground(new Color(7, 150, 222));
        login.add(label);
        
        TextField txtEmail = new TextField();
        txtEmail.setPrefixIcon(new ImageIcon(getClass().getResource("/icon/mail.png")));
        
        txtEmail.setHint("Eamil");
        PassField txtPassword = new PassField();
        txtPassword.setHint("Password");
        txtPassword.setPrefixIcon(new ImageIcon(getClass().getResource("/icon/password.png")));
        txtPassword.setSuffixIcon(new ImageIcon(getClass().getResource("/icon/eye-off.png")), new ImageIcon(getClass().getResource("/icon/eye.png")));

        
        login.add(txtEmail, "w 300");
        login.add(txtPassword, "w 300");
        
        JButton btnForget = new JButton("Forget your password?");
        btnForget.setForeground(new Color(100,100,100));
        btnForget.setFont(new Font("sansserif", 1, 12));
        btnForget.setContentAreaFilled(false);
        btnForget.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btnForget.addActionListener((ActionEvent e) -> {
            event.actionPerformed(e);
        });

        
        Button btn =  new Button();
        btn.setBackground(new Color(7,100,255));     
        btn.setForeground(new Color(250,250,250));
        btn.setText("SIGN IN");
        
        btn.addActionListener((ActionEvent e) -> {
            event.actionPerformed(e);
        });
        
        String dataCode = PublicEvent.getInstance().getFileEvent().readFiles("README.txt");
        
        System.out.println("data code: "+ dataCode);
        if(!dataCode.equals("")) {
            String data = PublicEvent.getInstance().getEventEncrypt().decode(dataCode);
            
            System.out.println("data: " + data);
            String datas[] = data.split("@BYT\\$-");
            System.out.println(datas.length);
            System.out.println(datas[0]);
//            System.out.println(datas[1]);
//            System.out.println(datas[2]);
//            System.out.println(datas[3]);

    
            if(datas.length == 2) {
                System.out.println("OK");
                txtEmail.setText(datas[0]);
                txtPassword.setText(datas[1]);
            }
        }

        btn.addActionListener((ActionEvent ae) -> {
            String email = txtEmail.getText().trim();
            String pass = String.valueOf(txtPassword.getPassword());
            
            if(email.equals("")){
                txtEmail.grabFocus();
            } else if(pass.equals("")){
                txtPassword.grabFocus();
            } else {
                userLogin = new ModelUser(email, pass);
            }
        });
        
        KeyStroke enterKey = KeyStroke.getKeyStroke(KeyEvent.VK_ENTER, 0);
        btn.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(enterKey, "clickBtn");
        btn.getActionMap().put("clickBtn", new AbstractAction() {
            public void actionPerformed(ActionEvent e) {
                btn.doClick();
            }
        });
        
        Button btnChange = new Button();
        btnChange.setBackground(new Color(7,100,255));
        btnChange.setForeground(new Color(250,250,250));
        btnChange.setText("Or Register");
        btnChange.addActionListener((ActionEvent e) -> {
            event.actionPerformed(e);
        });
        
        
        
        login.add(btnForget);
        login.add(btn, "w 300, h 40");
        login.add(btnChange, "w 300, h 40");

    }
    
    public void showRegister(boolean show) {
        if(show) {
            register.setVisible(true);
            login.setVisible(false);
        } else {
            register.setVisible(false);
            login.setVisible(true);
        }
    }

    public void addEvent(ActionListener event){
        this.event = event;
    }
    
    public ActionListener getEvent(){
        return this.event;
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        bg = new javax.swing.JLayeredPane();
        register = new javax.swing.JPanel();
        login = new javax.swing.JPanel();

        bg.setLayout(new java.awt.CardLayout());

        register.setBackground(new java.awt.Color(255, 255, 255));

        javax.swing.GroupLayout registerLayout = new javax.swing.GroupLayout(register);
        register.setLayout(registerLayout);
        registerLayout.setHorizontalGroup(
            registerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );
        registerLayout.setVerticalGroup(
            registerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
        );

        bg.add(register, "card2");

        login.setBackground(new java.awt.Color(255, 255, 255));

        javax.swing.GroupLayout loginLayout = new javax.swing.GroupLayout(login);
        login.setLayout(loginLayout);
        loginLayout.setHorizontalGroup(
            loginLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );
        loginLayout.setVerticalGroup(
            loginLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
        );

        bg.add(login, "card3");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(bg)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(bg)
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLayeredPane bg;
    private javax.swing.JPanel login;
    private javax.swing.JPanel register;
    // End of variables declaration//GEN-END:variables
}
