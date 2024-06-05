package view.components.chatArea;

import event.PublicEvent;
import java.awt.Image;
import java.util.Base64;
import javax.swing.ImageIcon;
import model.ModelFriend;
import model.ModelUser;

public class Header extends javax.swing.JPanel {

    private ModelFriend user;

    public Header() {
        initComponents();
    }

    public void setName(ModelFriend user) {
        this.user = user;
        txtName.setText(user.getFriendName());

        //        Decode
        try {
            byte[] imageByte = Base64.getDecoder().decode(user.getFriendImage());
            Image image = PublicEvent.getInstance().getEventEncrypt().decodeImage(imageByte);
            avatar1.setIcon(new ImageIcon(image));

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void updateUser(ModelFriend user) {
        if (this.user == user) {
            this.user = user;
            txtName.setText(user.getFriendName());

            //        Decode
            try {
                byte[] imageByte = Base64.getDecoder().decode(user.getFriendImage());
                Image image = PublicEvent.getInstance().getEventEncrypt().decodeImage(imageByte);
                avatar1.setIcon(new ImageIcon(image));

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public ModelFriend getFriend() {
        return user;
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        avatar1 = new swing.Avatar();
        layer = new javax.swing.JLayeredPane();
        txtName = new javax.swing.JLabel();
        txtStatus = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();

        setBackground(new java.awt.Color(193, 211, 254));

        jPanel1.setBackground(new java.awt.Color(193, 211, 254));

        avatar1.setForeground(new java.awt.Color(255, 255, 255));
        avatar1.setBorderColor(new java.awt.Color(204, 204, 204));
        avatar1.setBorderSize(1);
        avatar1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/avatar2-50.png"))); // NOI18N

        layer.setLayout(new java.awt.GridLayout(0, 1));

        txtName.setFont(new java.awt.Font("Segoe UI", 1, 15)); // NOI18N
        txtName.setForeground(new java.awt.Color(51, 51, 51));
        layer.add(txtName);

        txtStatus.setForeground(new java.awt.Color(51, 255, 51));
        layer.add(txtStatus);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(avatar1, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel1Layout.createSequentialGroup()
                    .addGap(57, 57, 57)
                    .addComponent(layer)
                    .addGap(343, 343, 343)))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(avatar1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 46, Short.MAX_VALUE)
            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(layer, javax.swing.GroupLayout.DEFAULT_SIZE, 38, Short.MAX_VALUE))
        );

        jSeparator1.setForeground(new java.awt.Color(0, 0, 153));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(3, 3, 3)
                        .addComponent(jSeparator1))
                    .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 253, Short.MAX_VALUE))
                .addGap(3, 3, 3))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 3, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(2, 2, 2)
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(2, 2, 2))
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private swing.Avatar avatar1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JLayeredPane layer;
    private javax.swing.JLabel txtName;
    private javax.swing.JLabel txtStatus;
    // End of variables declaration//GEN-END:variables
}
