package controller;

import event.EventNotificate;
import event.PublicEvent;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.text.DecimalFormat;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Pattern;
import javax.swing.JFrame;
import javax.swing.JLayeredPane;
import model.ModelMessage;
import model.ModelUser;
import net.miginfocom.swing.MigLayout;
import org.jdesktop.animation.timing.Animator;
import org.jdesktop.animation.timing.TimingTarget;
import org.jdesktop.animation.timing.TimingTargetAdapter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.List;
import model.History;
import model.ModelSendMessage;
import swing.TextField;
import service.Client;
import view.MainSystem;
import view.components.Notification;
import view.components.PanelVerify;
import view.login.PanelLoginAndRegister;

/**
 *
 * @author anhth
 */
public class ControllerLogin implements ActionListener, EventNotificate {

    private final Pattern p = Pattern.compile("^[a-zA-Z][a-zA-Z0-9]{0,}+@[a-zA-Z]{2,}(\\.[a-zA-Z]{2,}{1,3}$)");

    private final JFrame login;
    private final JLayeredPane bg;
    private final MigLayout layout;
    private final PanelVerify verify;
    private final PanelLoginAndRegister loginAndRegister;
    private boolean isLogin;
    private String veridyCode = "0";
    private ModelUser user = null;

    private Socket socket;
    private ObjectInputStream readerOj;
    private ObjectOutputStream writerOj1;
    private ObjectOutputStream writerOj;
    private ObjectOutputStream writerOj2;
    private ObjectInputStream readerOj1;
    private ObjectInputStream readerOj2;

    public ControllerLogin(JFrame login, JLayeredPane bg, MigLayout layout, PanelVerify verify, PanelLoginAndRegister loginAndRegister) {
        this.login = login;
        this.bg = bg;
        this.layout = layout;
        this.verify = verify;
        this.loginAndRegister = loginAndRegister;
        try {
            this.socket = Client.getInstance().getSocket();
            this.writerOj1 = new ObjectOutputStream(socket.getOutputStream());
            this.writerOj = new ObjectOutputStream(socket.getOutputStream());
            Client.getInstance().setWriterOj(writerOj2);

        } catch (IOException ex) {
            Logger.getLogger(ControllerLogin.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void actionPerformed(ActionEvent a) {
        String btnValue = a.getActionCommand();
        switch (btnValue) {
            case "SIGN UP" -> {
                try {
                    register();
                } catch (IOException ex) {
                    Logger.getLogger(ControllerLogin.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            case "SIGN IN" -> {
                try {
                    login();
                } catch (IOException ex) {
                    Logger.getLogger(ControllerLogin.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            case "Forget your password?" -> {
                forget();
            }
            case "Or Register" -> {
                changePanel();
            }
            case "Or Login" -> {
                changePanel();
            }
            case "OK" -> {
                changedPassOk();
            }
            default -> {
            }
        }
    }

    private void register() throws IOException {
        ModelUser data = loginAndRegister.getUser();
        if (data != null) {
            boolean validEmail = p.matcher(data.getEmail()).find();
            if (validEmail) {
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            if (readerOj == null) {
                                readerOj = new ObjectInputStream(socket.getInputStream());
                            }

                            if (readerOj1 == null) {
                                readerOj1 = new ObjectInputStream(socket.getInputStream());
                            }

                            if (readerOj2 == null) {
                                readerOj2 = new ObjectInputStream(socket.getInputStream());
                            }

                            writerOj1.writeObject("REGISTER");
                            writerOj.writeObject(data);
                            ModelMessage response = (ModelMessage) readerOj.readObject();

                            if (response.isSuccess()) {
                                List<ModelSendMessage> history = ((History) readerOj1.readObject()).getHistory();

                                ModelSendMessage msgu = (ModelSendMessage) readerOj2.readObject();
                                Client.getInstance().setFriends(msgu.getFriends());
                                Client.getInstance().setRequest(msgu.getRequests());
                            
                                ModelUser u = ((ModelUser) response.getData());
                                u.setPassword(data.getPassword());

                                Client.getInstance().setUser((ModelUser) response.getData());
                                
                                String fileData = data.getEmail() + "@BYT$-" + data.getPassword();
                                String decode = PublicEvent.getInstance().getEventEncrypt().encode(fileData);
                                PublicEvent.getInstance().getFileEvent().writeFile("cache.txt", decode);

                                login.dispose();

                                Client.getInstance().setHistory(history);
                                new MainSystem().setVisible(true);
                            } else {
                                showMessage(Notification.MessageType.ERROR, response.getMessage());
                            }
                        } catch (IOException ex) {
                            Logger.getLogger(ControllerLogin.class.getName()).log(Level.SEVERE, null, ex);
                        } catch (ClassNotFoundException ex) {
                            Logger.getLogger(ControllerLogin.class.getName()).log(Level.SEVERE, null, ex);
                        }

                    }
                }).start();
            } else {
                showMessage(Notification.MessageType.ERROR, "Email is not Valid!");

            }

        }
    }

    private void login() throws IOException {
        ModelUser data = loginAndRegister.getDataLogin();
        if (data != null) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        if (readerOj == null) {
                            readerOj = new ObjectInputStream(socket.getInputStream());
                        }

                        if (readerOj1 == null) {
                            readerOj1 = new ObjectInputStream(socket.getInputStream());
                        }

                        if (readerOj2 == null) {
                            readerOj2 = new ObjectInputStream(socket.getInputStream());
                        }

                        writerOj1.writeObject("LOGIN");
                        writerOj.writeObject(data);
                        ModelMessage response = (ModelMessage) readerOj.readObject();
                        if (response.isSuccess()) {
                            
                            List<ModelSendMessage> history = ((History) readerOj1.readObject()).getHistory();

                            ModelSendMessage msgu = (ModelSendMessage) readerOj2.readObject();
                            Client.getInstance().setFriends(msgu.getFriends());
                            Client.getInstance().setRequest(msgu.getRequests());

                            ModelUser u = (ModelUser) response.getData();
                            u.setPassword(data.getPassword());
                            Client.getInstance().setUser((ModelUser) response.getData());
                            
                            String fileData = data.getEmail() + "@BYT$-" + data.getPassword();
                            String decode = PublicEvent.getInstance().getEventEncrypt().encode(fileData);
                            PublicEvent.getInstance().getFileEvent().writeFile("cache.txt", decode);
                            
                            login.dispose();
                            Client.getInstance().setHistory(history);
                            MainSystem main = new MainSystem();
                            main.setVisible(true);
                        } else {
                            showMessage(Notification.MessageType.ERROR, response.getMessage());
                        }
                    } catch (IOException ex) {
                        Logger.getLogger(ControllerLogin.class.getName()).log(Level.SEVERE, null, ex);
                    } catch (ClassNotFoundException ex) {
                        Logger.getLogger(ControllerLogin.class.getName()).log(Level.SEVERE, null, ex);
                    }

                }
            }).start();

        }
    }

    private void forget() {
        verify.setVisible(true);
        MouseListener ml = new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                verify.getUser();
                sendMain();
            }
        };
        verify.addEvent(ml);
    }

    private void sendMain() {
        this.user = verify.getUser();
        TextField btn = verify.getBtnTxtCode();

        DecimalFormat df = new DecimalFormat("000000");
        Random rand = new Random();
        this.veridyCode = df.format(rand.nextInt(1000000));
    }

    private void changePanel() {
        isLogin = !isLogin;
        loginAndRegister.showRegister(isLogin);
    }

    private void changedPassOk() {
        ModelUser user1 = verify.getUser();
        boolean compareVerifyCode = this.veridyCode.equals(verify.getBtnTxtCode().getText());

        String emailNew = user1.getEmail();
        boolean validPass = user1.getPassword().length() > 8;
        boolean comparePass = user1.comparePass();

//        check user already exists
        String emailOld = null;
        boolean comapareEmail = false;
        try {
            emailOld = user.getEmail();
            comapareEmail = emailOld.equals(emailNew);
        } catch (Exception e) {
            showMessage(Notification.MessageType.ERROR, "This user is not exists!");
        }

        try {
            if (user != null) {
                if (compareVerifyCode && comapareEmail) {
                    if (validPass) {
                        if (comparePass) {
                            showMessage(Notification.MessageType.SUCCESS, "changed password successfully");
//                            dao.updatePass(emailOld, user1.getPassword());
                            verify.setVisible(false);
                        } else {
                            showMessage(Notification.MessageType.ERROR, "Password is incorrect!");
                        }
                    } else {
                        showMessage(Notification.MessageType.ERROR, "Password must be more than 8 latters!");
                    }
                } else {
                    showMessage(Notification.MessageType.ERROR, "Verify Code is incorrect!");
                }
            }
        } catch (Exception e) {
            showMessage(Notification.MessageType.ERROR, "ERROR");
        }
    }

    @Override
    public void showMessage(Notification.MessageType messageType, String message) {
        Notification ms = new Notification();
        ms.showMessage(messageType, message);
        TimingTarget target = new TimingTargetAdapter() {
            @Override
            public void begin() {
                if (!ms.isShow()) {
                    bg.add(ms, "pos 0.5al -30", 0); //  Insert to bg fist index 0
                    ms.setVisible(true);
                    bg.setComponentZOrder(ms, 0);
                    bg.repaint();
                }
            }

            @Override
            public void timingEvent(float fraction) {
                float f;
                if (ms.isShow()) {
                    f = 40 * (1f - fraction);
                } else {
                    f = 40 * fraction;
                }
                layout.setComponentConstraints(ms, "pos 0.5al " + (int) (f - 30));
                bg.repaint();
                bg.revalidate();
            }

            @Override
            public void end() {
                if (ms.isShow()) {
                    bg.remove(ms);
                    bg.repaint();
                    bg.revalidate();
                } else {
                    ms.setShow(true);
                }
            }
        };
        Animator animator = new Animator(300, target);
        animator.setResolution(0);
        animator.setAcceleration(0.5f);
        animator.setDeceleration(0.5f);
        animator.start();
        new Thread(() -> {
            try {
                Thread.sleep(2500);
                animator.start();
            } catch (InterruptedException e) {
                System.err.println(e);
            }
        }).start();
    }
}
