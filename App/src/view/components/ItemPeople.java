
package view.components;

import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import model.ModelUser;
import event.PublicEvent;
import java.awt.Image;
import java.util.Base64;
import javax.swing.ImageIcon;

public class ItemPeople extends javax.swing.JPanel {
    private ModelUser user;
    
    public ItemPeople(ModelUser user) {
        this.user = user;
        initComponents();
        lb.setText(user.getUserName());
        
        //        Decode
        try {
            byte[] imageByte = Base64.getDecoder().decode(user.getImage());
            Image image = PublicEvent.getInstance().getEventEncrypt().decodeImage(imageByte);
            avatar1.setIcon(new ImageIcon(image));
            
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        init();
    }
    
    private void init() {
        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                bg.setBgColor(new Color(204,204,204,70));
                bg.revalidate();
                bg.repaint();
            }

            @Override
            public void mouseExited(MouseEvent e) {
                bg.setBgColor(new Color(255,255,255));
                bg.revalidate();
                bg.repaint();
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                PublicEvent.getInstance().getEventContent().selectedUser(user);
            } 
            
        });
    }

    public ModelUser getUser() {
        return user;
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        bg = new swing.radius.PanelRadius();
        avatar1 = new swing.Avatar();
        lb = new javax.swing.JLabel();

        setBackground(new java.awt.Color(255, 204, 204));
        setOpaque(false);

        avatar1.setForeground(new java.awt.Color(204, 204, 255));
        avatar1.setBorderColor(new java.awt.Color(204, 204, 255));
        avatar1.setBorderSize(1);
        avatar1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/avatar2-50.png"))); // NOI18N

        lb.setBackground(new java.awt.Color(51, 51, 51));
        lb.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        lb.setForeground(new java.awt.Color(51, 51, 51));

        javax.swing.GroupLayout bgLayout = new javax.swing.GroupLayout(bg);
        bg.setLayout(bgLayout);
        bgLayout.setHorizontalGroup(
            bgLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(bgLayout.createSequentialGroup()
                .addGap(12, 12, 12)
                .addComponent(avatar1, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lb)
                .addContainerGap(252, Short.MAX_VALUE))
        );
        bgLayout.setVerticalGroup(
            bgLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(bgLayout.createSequentialGroup()
                .addGap(8, 8, 8)
                .addGroup(bgLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lb)
                    .addComponent(avatar1, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(8, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(bg, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(bg, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private swing.Avatar avatar1;
    private swing.radius.PanelRadius bg;
    private javax.swing.JLabel lb;
    // End of variables declaration//GEN-END:variables
}
