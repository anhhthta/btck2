package view.components.chatArea;

import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.JScrollPane;
import model.ModelUser;
import net.miginfocom.swing.MigLayout;
import swing.JIMSendTextPane;
import swing.ScrollBar;

public class Bottom extends javax.swing.JPanel {
    private JIMSendTextPane txt;
    private ModelUser user;

    public Bottom() {
        initComponents();
        init();
    }

    private void init(){
        btnSend.setHover(new Color(204,204,255));
        txtArea.setLayout(new MigLayout("fillx, filly", "" ,""));
        JScrollPane scroll = new JScrollPane();
        scroll.setBorder(null);
        txt = new JIMSendTextPane();
        txt.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                refresh();
            }
        });
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

    private void refresh() {
        revalidate();
    }
    
    public JIMSendTextPane getTextBox() {
        return txt;
    }
    
    public void addEvent(ActionListener event) {
        btnSend.addActionListener(event);
    }

    public void setUser(ModelUser user) {
        this.user = user;
    }

    public ModelUser getUser() {
        return user;
    }
    
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        txtArea = new swing.radius.PanelRadius();
        button2 = new swing.Button();
        btnSend = new swing.Button();

        setBackground(new java.awt.Color(255, 255, 255));

        txtArea.setBgColor(new java.awt.Color(224, 224, 224));
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

        button2.setText("button2");

        btnSend.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/send-28.png"))); // NOI18N
        btnSend.setActionCommand("send");
        btnSend.setRadius(40);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(button2, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtArea, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnSend, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(8, 8, 8))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(button2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addComponent(btnSend, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addContainerGap())))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(txtArea, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addContainerGap())))
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private swing.Button btnSend;
    private swing.Button button2;
    private swing.radius.PanelRadius txtArea;
    // End of variables declaration//GEN-END:variables
}