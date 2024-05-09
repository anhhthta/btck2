
package view.components;

import controller.ControllerChat;
import event.EventChat;
import event.PublicEvent;
import model.ModelUser;
import net.miginfocom.swing.MigLayout;
import swing.BackgroundImage;
import view.components.chatArea.Body;
import view.components.chatArea.Bottom;
import view.components.chatArea.Header;

public class Chat extends javax.swing.JPanel {
    private Header header;
    private Body body;
    private Bottom bottom;
//    private ControllerAction event;
    private ControllerChat event;
    
    public Chat() {
        initComponents();
        init();
    }
    
    
    private void init() {
        setLayout(new MigLayout("fillx", "0[fill]0" , "0[]0[100%, b]0[shrink 0]0"));
        header = new Header();
        body = new Body();
        bottom = new Bottom();
        add(header, "wrap");
        add(body, "wrap");
        add(bottom, "h ::30%");
        
        event = new ControllerChat(header, body, bottom);
        PublicEvent.getInstance().addEventChat((EventChat) event);

        body.addEvent(event);
        bottom.addEvent(event);
    }
        
    public void setUser(ModelUser user) {
        header.setName(user);
        bottom.setUser(user);
        body.clearChat();
        event.setHistory();
    }
    
    public void updateUser(ModelUser user) {
        header.updateUser(user);
    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        setBackground(new java.awt.Color(255, 255, 255));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 544, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 470, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
