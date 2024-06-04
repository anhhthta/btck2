package view.login;

import controller.ControllerLogin;
import controller.Encrypt;
import controller.FIlesHandle;
import event.EventNotificate;
import event.PublicEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JLayeredPane;
import net.miginfocom.swing.MigLayout;
import service.Client;
import view.components.PanelVerify;

/**
 *
 * @author anhth
 */
public class Login extends javax.swing.JFrame {

    private MigLayout layout;
    private PanelVerify verify;
    private PanelLoginAndRegister loginAndRegister;

    public Login() throws IOException {
        initComponents();
        init();
    }

    private void init() throws IOException {
        PublicEvent.getInstance().addEventEncrypt(new Encrypt());
        PublicEvent.getInstance().addFileEvent(new FIlesHandle());
        
        layout = new MigLayout("fill, insets 0");
        verify = new PanelVerify();
        loginAndRegister = new PanelLoginAndRegister();
        bg.setLayout(layout);
        bg.setLayer(verify, JLayeredPane.POPUP_LAYER);
        bg.add(verify, "pos 0 0 100% 100%");
        bg.add(loginAndRegister, "w 50%, pos 0.5al 0.5al");
        Client.getInstance().createSocket();

        ActionListener event = new ControllerLogin(this, bg, layout, verify, loginAndRegister);
        PublicEvent.getInstance().addEventNotificate((EventNotificate) event);
        loginAndRegister.addEvent(event);

        verify.addEventBtnOK(event);
//        Service.getInstance().startServer();

    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        bg = new javax.swing.JLayeredPane();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        bg.setBackground(new java.awt.Color(255, 255, 255));
        bg.setOpaque(true);

        javax.swing.GroupLayout bgLayout = new javax.swing.GroupLayout(bg);
        bg.setLayout(bgLayout);
        bgLayout.setHorizontalGroup(
            bgLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 500, Short.MAX_VALUE)
        );
        bgLayout.setVerticalGroup(
            bgLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 435, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(bg)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(bg)
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Login.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        java.awt.EventQueue.invokeLater(() -> {
            try {
                new Login().setVisible(true);
            } catch (IOException ex) {
                Logger.getLogger(Login.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLayeredPane bg;
    // End of variables declaration//GEN-END:variables
}
