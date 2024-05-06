
package view.components.chatArea;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import javax.swing.border.EmptyBorder;

public class ChatItem extends javax.swing.JLayeredPane {
    private DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm");
    public ChatItem() {
        initComponents();
        txt.setEditable(false);
        txt.setBackground(new Color(0,0,0,0));
        txt.setOpaque(false);

    }

    public void setTextLeft(String text, LocalDateTime time) {
        txt.setText(text);
        txtTime.setText(time.format(formatter));
        setBackground(new Color(239,239,239));
    }
    
    public void setTextRight(String text, LocalDateTime time) {
        txt.setText(text);
        txtTime.setText(time.format(formatter));        
        setBackground(new Color(153,153,255));
    }
    
    public void setUserName(String userName){
        txtTime.setVisible(false);
        txt.setFont(new Font("Segoe UI", 0, 12));
        txt.setBorder(new EmptyBorder(5, 12, 0, 0));
        txt.setText(userName);
        setBackground(new Color(0,0,0,0));
    }
    @SuppressWarnings("unchecked")
    
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        txt = new swing.JIMSendTextPane();
        jPanel1 = new javax.swing.JPanel();
        txtTime = new javax.swing.JLabel();

        setLayout(new javax.swing.BoxLayout(this, javax.swing.BoxLayout.PAGE_AXIS));

        txt.setBorder(javax.swing.BorderFactory.createEmptyBorder(6, 10, 4, 10));
        txt.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        txt.setOpaque(false);
        txt.setSelectionColor(new java.awt.Color(92, 188, 255));
        add(txt);

        jPanel1.setOpaque(false);
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
    private javax.swing.JPanel jPanel1;
    private swing.JIMSendTextPane txt;
    private javax.swing.JLabel txtTime;
    // End of variables declaration//GEN-END:variables
}
