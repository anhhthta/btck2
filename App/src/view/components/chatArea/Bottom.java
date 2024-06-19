package view.components.chatArea;

import java.awt.Color;
import java.awt.event.ActionListener;
import javax.swing.JScrollPane;
import model.ModelFriend;
import net.miginfocom.swing.MigLayout;
import swing.JIMSendTextPane;
import swing.ScrollBar;

public class Bottom extends javax.swing.JPanel {
    private JIMSendTextPane txt;
    private ModelFriend user;

    public Bottom() {
        initComponents();
        init();
        emoji.setVisible(false);
    }

    private void init(){
        btnSend.setHover(new Color(215,227,252));
        txtArea.setLayout(new MigLayout("fillx, filly", "" ,""));
        JScrollPane scroll = new JScrollPane();
        scroll.setBorder(null);
        txt = new JIMSendTextPane();
        txt.setFont(new java.awt.Font("Segoe UI", 0, 13));
        txt.setHintText("Mesage");
        txt.setBackground(txtArea.getBgColor());
        txt.setForeground(new Color(99,99,99));
        
        scroll.setViewportView(txt);
        ScrollBar sb = new ScrollBar();
        sb.setFgColor(new Color(51,51,51, 77));
        scroll.setVerticalScrollBar(sb);
        
        txtArea.add(scroll, "w 100%");
        
    }

    public void refresh() {
        revalidate();
    }
    
    public JIMSendTextPane getTextBox() {
        return txt;
    }
    
    public void addEvent(ActionListener event) {
        btnSend.addActionListener(event);
    }

    public void setUser(ModelFriend user) {
        this.user = user;
    }

    public ModelFriend getUser() {
        return user;
    }
    
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        txtArea = new swing.radius.PanelRadius();
        btnSend = new swing.Button();
        btnEmoji = new swing.Button();
        jSeparator1 = new javax.swing.JSeparator();
        emoji = new view.components.chatArea.PanelEmoji();

        setBackground(new java.awt.Color(182, 204, 254));

        txtArea.setBgColor(new java.awt.Color(215, 227, 252));
        txtArea.setRadius(30);

        javax.swing.GroupLayout txtAreaLayout = new javax.swing.GroupLayout(txtArea);
        txtArea.setLayout(txtAreaLayout);
        txtAreaLayout.setHorizontalGroup(
            txtAreaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 292, Short.MAX_VALUE)
        );
        txtAreaLayout.setVerticalGroup(
            txtAreaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        btnSend.setBackground(new java.awt.Color(182, 204, 254));
        btnSend.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 5, 1, 1));
        btnSend.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/send-28_1.png"))); // NOI18N
        btnSend.setToolTipText("");
        btnSend.setActionCommand("send");
        btnSend.setRadius(40);
        btnSend.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSendActionPerformed(evt);
            }
        });

        btnEmoji.setBackground(new java.awt.Color(182, 204, 254));
        btnEmoji.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 2, 1, 2));
        btnEmoji.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/emoji/face_25.png"))); // NOI18N
        btnEmoji.setToolTipText("");
        btnEmoji.setActionCommand("send");
        btnEmoji.setRadius(40);
        btnEmoji.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEmojiActionPerformed(evt);
            }
        });

        jSeparator1.setBackground(new java.awt.Color(182, 204, 254));
        jSeparator1.setForeground(new java.awt.Color(182, 204, 254));
        jSeparator1.setEnabled(false);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jSeparator1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(txtArea, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnEmoji, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnSend, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(8, 8, 8))
            .addComponent(emoji, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(txtArea, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnEmoji, javax.swing.GroupLayout.DEFAULT_SIZE, 34, Short.MAX_VALUE)
                    .addComponent(btnSend, javax.swing.GroupLayout.DEFAULT_SIZE, 34, Short.MAX_VALUE))
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 6, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(emoji, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void btnEmojiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEmojiActionPerformed
        if(emoji.isVisible()) {
            emoji.setVisible(false);
            revalidate();
        } else {
            emoji.setVisible(true);
            revalidate();
        }
    }//GEN-LAST:event_btnEmojiActionPerformed

    private void btnSendActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSendActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnSendActionPerformed
    
    

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private swing.Button btnEmoji;
    private swing.Button btnSend;
    private view.components.chatArea.PanelEmoji emoji;
    private javax.swing.JSeparator jSeparator1;
    private swing.radius.PanelRadius txtArea;
    // End of variables declaration//GEN-END:variables
}
