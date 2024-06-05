package view.components;

import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import event.PublicEvent;
import java.awt.Image;
import java.time.format.DateTimeFormatter;
import java.util.Base64;
import java.util.List;
import javax.swing.ImageIcon;
import model.ModelFriend;
import model.ModelSendMessage;
import service.Client;

public class ItemFriend extends javax.swing.JPanel {

    private ModelFriend friend;
    private DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm");

    public ItemFriend(ModelFriend friend) {
        this.friend = friend;
        initComponents();
        lb.setText(friend.getFriendName());

        //        Decode
        try {
            byte[] imageByte = Base64.getDecoder().decode(friend.getFriendImage());
            Image image = PublicEvent.getInstance().getEventEncrypt().decodeImage(imageByte);
            avatar1.setIcon(new ImageIcon(image));

        } catch (Exception e) {
            e.printStackTrace();
        }

        init();
        setData();
    }

    private void setData() {
        List<ModelSendMessage> list = Client.getInstance().getHistory();
        int id = Client.getInstance().getUser().getUserID();
        for (ModelSendMessage l : list) {
            if (friend.getFriendId()== l.getTo() || id == l.getTo()) {
                if (friend.getFriendId()== l.getFrom()) {
                    lastMsg.setText(l.getUser().getUserName() + ": " + l.getText());
                    lastTime.setText(l.getTime().format(formatter));
                } else if (id == l.getFrom()) {
                    lastMsg.setText("You: " + l.getText());
                    lastTime.setText(l.getTime().format(formatter));
                } else if (friend.getFriendId() == -1 && l.getTo() == -1) {
                    lastMsg.setText(l.getUser().getUserName() + ": " + l.getText());
                    lastTime.setText(l.getTime().format(formatter));
                }
            }
        }
    }

    private void init() {

        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                bg.setBgColor(new Color(182, 204, 254));
                bg.revalidate();
                bg.repaint();
            }

            @Override
            public void mouseExited(MouseEvent e) {
                bg.setBgColor(new Color(215,227,252));
                bg.revalidate();
                bg.repaint();
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                PublicEvent.getInstance().getEventContent().selectedUser(friend);
            }

        });
    }

    public ModelFriend getFriend() {
        return friend;
    }

    public void setLastText(ModelSendMessage data) {

        int id = Client.getInstance().getUser().getUserID();

        if (friend.getFriendId() == data.getUser().getUserID()) {
            lastMsg.setText(data.getUser().getUserName() + ": " + data.getText());
            lastTime.setText(data.getTime().format(formatter));
        } else if (id == data.getUser().getUserID()) {
            lastMsg.setText("You: " + data.getText());
            lastTime.setText(data.getTime().format(formatter));
        } else if (friend.getFriendId() == -1) {
            lastMsg.setText(data.getUser().getUserName() + ": " + data.getText());
            lastTime.setText(data.getTime().format(formatter));
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        bg = new swing.radius.PanelRadius();
        avatar1 = new swing.Avatar();
        lb = new javax.swing.JLabel();
        lastMsg = new javax.swing.JLabel();
        lastTime = new javax.swing.JLabel();

        setBackground(new java.awt.Color(255, 204, 204));
        setOpaque(false);

        bg.setBgColor(new java.awt.Color(215, 227, 252));

        avatar1.setForeground(new java.awt.Color(204, 204, 255));
        avatar1.setBorderColor(new java.awt.Color(204, 204, 255));
        avatar1.setBorderSize(1);
        avatar1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/avatar2-50.png"))); // NOI18N

        lb.setBackground(new java.awt.Color(51, 51, 51));
        lb.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        lb.setForeground(new java.awt.Color(51, 51, 51));

        lastMsg.setBackground(new java.awt.Color(51, 51, 51));
        lastMsg.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        lastMsg.setForeground(new java.awt.Color(102, 102, 102));

        lastTime.setBackground(new java.awt.Color(51, 51, 51));
        lastTime.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        lastTime.setForeground(new java.awt.Color(102, 102, 102));
        lastTime.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);

        javax.swing.GroupLayout bgLayout = new javax.swing.GroupLayout(bg);
        bg.setLayout(bgLayout);
        bgLayout.setHorizontalGroup(
            bgLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(bgLayout.createSequentialGroup()
                .addGap(12, 12, 12)
                .addComponent(avatar1, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(bgLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lastMsg, javax.swing.GroupLayout.PREFERRED_SIZE, 168, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(bgLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addComponent(lb, javax.swing.GroupLayout.PREFERRED_SIZE, 238, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(lastTime, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(14, Short.MAX_VALUE))
        );
        bgLayout.setVerticalGroup(
            bgLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(bgLayout.createSequentialGroup()
                .addGap(8, 8, 8)
                .addGroup(bgLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(avatar1, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(bgLayout.createSequentialGroup()
                        .addComponent(lb, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, 0)
                        .addGroup(bgLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lastMsg, javax.swing.GroupLayout.PREFERRED_SIZE, 18, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lastTime, javax.swing.GroupLayout.PREFERRED_SIZE, 18, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap())
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
    private javax.swing.JLabel lastMsg;
    private javax.swing.JLabel lastTime;
    private javax.swing.JLabel lb;
    // End of variables declaration//GEN-END:variables
}
