package view.components.chatArea;

import event.PublicEvent;
import java.awt.Color;
import java.awt.Image;
import java.util.Base64;
import javax.swing.ImageIcon;
import model.ModelUser;

public class Header extends javax.swing.JPanel {

    private ModelUser user;

    public Header() {
        initComponents();
    }

    public void setName(ModelUser user) {
        this.user = user;
        txtName.setText(user.getUserName());
        setStatus(user.isStatus());

        //        Decode
        try {
            byte[] imageByte = Base64.getDecoder().decode(user.getImage());
            Image image = PublicEvent.getInstance().getEventEncrypt().decodeImage(imageByte);
            avatar1.setIcon(new ImageIcon(image));

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void updateUser(ModelUser user) {
        if (this.user == user) {
            this.user = user;
            txtName.setText(user.getUserName());
            setStatus(user.isStatus());

            //        Decode
            try {
                byte[] imageByte = Base64.getDecoder().decode(user.getImage());
                Image image = PublicEvent.getInstance().getEventEncrypt().decodeImage(imageByte);
                avatar1.setIcon(new ImageIcon(image));

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public ModelUser getUser() {
        return user;
    }

    private void setStatus(boolean isStatus) {
        if (isStatus) {
            txtStatus.setText("Active now");
            txtStatus.setForeground(new Color(51, 255, 51));
        } else {
            txtStatus.setText("Offline");
            txtStatus.setForeground(new Color(51, 51, 51));
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        panelShadow1 = new swing.shadow.PanelShadow();
        layer = new javax.swing.JLayeredPane();
        txtName = new javax.swing.JLabel();
        txtStatus = new javax.swing.JLabel();
        avatar1 = new swing.Avatar();

        setBackground(new java.awt.Color(240, 240, 242));
        setOpaque(false);

        panelShadow1.setShadowOpacity(0.1F);
        panelShadow1.setShadowSize(2);

        layer.setLayout(new java.awt.GridLayout(0, 1));

        txtName.setFont(new java.awt.Font("Segoe UI", 1, 15)); // NOI18N
        layer.add(txtName);

        txtStatus.setForeground(new java.awt.Color(51, 255, 51));
        txtStatus.setText("Active Now");
        layer.add(txtStatus);

        avatar1.setForeground(new java.awt.Color(255, 255, 255));
        avatar1.setBorderColor(new java.awt.Color(204, 204, 204));
        avatar1.setBorderSize(1);
        avatar1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/avatar2-50.png"))); // NOI18N

        javax.swing.GroupLayout panelShadow1Layout = new javax.swing.GroupLayout(panelShadow1);
        panelShadow1.setLayout(panelShadow1Layout);
        panelShadow1Layout.setHorizontalGroup(
            panelShadow1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelShadow1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(avatar1, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(layer, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(287, Short.MAX_VALUE))
        );
        panelShadow1Layout.setVerticalGroup(
            panelShadow1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelShadow1Layout.createSequentialGroup()
                .addComponent(avatar1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(3, 3, 3))
            .addGroup(panelShadow1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(layer, javax.swing.GroupLayout.DEFAULT_SIZE, 41, Short.MAX_VALUE)
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panelShadow1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(panelShadow1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(0, 0, 0))
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private swing.Avatar avatar1;
    private javax.swing.JLayeredPane layer;
    private swing.shadow.PanelShadow panelShadow1;
    private javax.swing.JLabel txtName;
    private javax.swing.JLabel txtStatus;
    // End of variables declaration//GEN-END:variables
}
