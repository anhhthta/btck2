package view.components.chatArea;

import event.PublicEvent;
import java.awt.Image;
import java.util.Base64;
import javax.swing.ImageIcon;
import model.ModelSend;
import model.ModelUser;
//255,213,161
public class ItemChatEmojiLeft extends javax.swing.JPanel {
    public ItemChatEmojiLeft() {
        initComponents();
    }
    
    public void setData(ModelSend data) {
        ModelUser user = data.getUser();
        String name = user.getUserName();
        chatItemEmoji.setTextLeft(data.getText(), data.getTime(), name);
        userNama.setText(name);
        
        if(user.getImage() != null) {
            avatar.setIcon(new ImageIcon(user.getImage()));
        } else {
            //      Decode
            new Thread(() -> {
                try {
                    while(data.getUser().getImage() == null) {
                        byte[] imageByte = Base64.getDecoder().decode(user.getImageString());
                        Image image = PublicEvent.getInstance().getEventEncrypt().decodeImage(imageByte);
                        data.getUser().setImage(image);
                        avatar.setIcon(new ImageIcon(image));
                        refreshAvatar();
                    }
                    
                    Thread.sleep(300);
                    refreshAvatar();
                    Thread.sleep(400);
                    refreshAvatar();
                    Thread.sleep(500);
                    refreshAvatar();
                     Thread.sleep(650);
                    refreshAvatar();
                    Thread.sleep(880);
                    refreshAvatar();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }).start();
        }
    }
    
    private void refreshAvatar() {
        avatar.repaint();
        avatar.revalidate();
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        avatar = new swing.Avatar();
        userNama = new javax.swing.JLabel();
        chatItemEmoji = new view.components.chatArea.ChatItemEmoji();

        setOpaque(false);

        avatar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/avatar2-50.png"))); // NOI18N

        userNama.setText("jLabel1");
        userNama.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 10, 1, 1));

        chatItemEmoji.setBackground(new java.awt.Color(255, 213, 161));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(avatar, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(userNama)
                        .addContainerGap(14, Short.MAX_VALUE))
                    .addComponent(chatItemEmoji, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(avatar, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(layout.createSequentialGroup()
                .addComponent(userNama)
                .addGap(0, 0, 0)
                .addComponent(chatItemEmoji, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(0, 0, 0))
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private swing.Avatar avatar;
    private view.components.chatArea.ChatItemEmoji chatItemEmoji;
    private javax.swing.JLabel userNama;
    // End of variables declaration//GEN-END:variables
}
