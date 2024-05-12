package view.components.chatArea;

import event.PublicEvent;
import java.awt.Image;
import java.util.Base64;
import javax.swing.ImageIcon;
import model.ModelSendMessage;

public class ItemChatLeft extends javax.swing.JPanel {
    public ItemChatLeft() {
        initComponents();
    }
    
    public void setData(ModelSendMessage data) {
        String name = data.getUser().getUserName();
        chatItem.setTextLeft(data.getText(), data.getTime(), name);
        userNama.setText(name);
//      Decode
        try {
            byte[] imageByte = Base64.getDecoder().decode(data.getUser().getImage());
            Image image = PublicEvent.getInstance().getEventEncrypt().decodeImage(imageByte);
            avatar.setIcon(new ImageIcon(image));
            System.out.println(imageByte);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        chatItem = new view.components.chatArea.ChatItem();
        avatar = new swing.Avatar();
        userNama = new javax.swing.JLabel();

        setOpaque(false);

        avatar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/avatar2-50.png"))); // NOI18N

        userNama.setText("jLabel1");
        userNama.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 10, 1, 1));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(avatar, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(userNama)
                    .addComponent(chatItem, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(0, 0, 0))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(avatar, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addComponent(userNama)
                .addGap(0, 0, 0)
                .addComponent(chatItem, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private swing.Avatar avatar;
    private view.components.chatArea.ChatItem chatItem;
    private javax.swing.JLabel userNama;
    // End of variables declaration//GEN-END:variables
}
