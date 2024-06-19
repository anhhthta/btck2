
package view.components.chatArea;

import Emoji.Emoji;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import javax.swing.ImageIcon;

public class ChatItemEmoji extends javax.swing.JLayeredPane {
    private DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm");
    public ChatItemEmoji() {
        initComponents();
    }

    public void setTextLeft(String text, LocalDateTime time, String name) {
        emj.setIcon(Emoji.getInstance().getEmoji(Integer.parseInt(text)).toSize(30, 30).getIcon());
        txtTime.setText(time.format(formatter));
//        setBackground(new Color(239,239,239));
    }
    
    public void setTextRight(String text, LocalDateTime time) {
        emj.setIcon(Emoji.getInstance().getEmoji(Integer.parseInt(text)).toSize(30, 30).getIcon());
        txtTime.setText(time.format(formatter));        
        setBackground(new Color(162,149,255));
    }
    
    @SuppressWarnings("unchecked")
    
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel2 = new javax.swing.JPanel();
        emj = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        txtTime = new javax.swing.JLabel();

        setLayout(new javax.swing.BoxLayout(this, javax.swing.BoxLayout.Y_AXIS));

        jPanel2.setOpaque(false);

        emj.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/emoji/icon_2.png"))); // NOI18N

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(emj)
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(emj)
        );

        add(jPanel2);

        jPanel1.setOpaque(false);
        jPanel1.setPreferredSize(new java.awt.Dimension(20, 3));
        jPanel1.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT, 0, 0));

        txtTime.setForeground(new java.awt.Color(102, 102, 102));
        txtTime.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 13, 3, 10));
        jPanel1.add(txtTime);

        add(jPanel1);
    }// </editor-fold>//GEN-END:initComponents
    @Override
    protected void paintComponent(Graphics grphcs) {
        Graphics2D g2 = (Graphics2D) grphcs;
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2.setColor(getBackground());
        g2.fillRoundRect(0, 0, getWidth(), getHeight(), 20, 20);
        super.paintComponent(grphcs);
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel emj;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JLabel txtTime;
    // End of variables declaration//GEN-END:variables
}
