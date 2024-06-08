package view.components;

import controller.ControllerRequestFriend;
import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import event.PublicEvent;
import java.awt.Image;
import java.util.Base64;
import javax.swing.ImageIcon;
import model.ModelFriend;
import model.RequestFriend;
import service.Client;
import swing.layout.WrapLayout;

public class ItemPeople extends javax.swing.JPanel {

    private RequestFriend request;
    private ControllerRequestFriend event;

    public ItemPeople(RequestFriend request) {
        this.request = request;
        initComponents();
        layoutBtn.setAlign(WrapLayout.RIGHT);
        btnAS2.setVisible(false);
        
        lb.setText(request.getFriend().getFriendName());
        
        int rqId = request.getRequester();
        if(rqId != -10) {
            ModelFriend uss = request.getFriend();
            if(uss.getStatus().equals("accepted")) {
                btnAS1.setText("Unfriend");
                btnAS1.setActionCommand("unfriend");
            } else {
                if(rqId == Client.getInstance().getUser().getUserID()) {
                    btnAS1.setVisible(true);
                    btnAS1.setText("Cancel");
                    btnAS1.setActionCommand("cancel");
                } else {
                    btnAS2.setVisible(true);
                    btnAS1.setText("Refuse");
                    btnAS1.setActionCommand("refuse");
                    
                    btnAS2.setText("Confirm");
                    btnAS2.setActionCommand("confirm");
                }
            }
        } else {
            btnAS1.setVisible(false);
            btnAS2.setVisible(true);
            btnAS2.setText("Request");
            btnAS2.setActionCommand("request");

        }

//        ModelFriend f = request.getFriend();
//        if(f.getFriendImage() != null) {
//            avatar1.setIcon(new ImageIcon(request.getFriend().getFriendImage()));
//        } else {
//            //        Decode
////            new Thread(() -> {
                try {
                    byte[] imageByte = Base64.getDecoder().decode(request.getFriend().getFriendImageString());
                    Image image = PublicEvent.getInstance().getEventEncrypt().decodeImage(imageByte);
                    
                    if(image != null) {
                        avatar1.setIcon(new ImageIcon(image));
//                        refreshAvatar();
                        image.flush();
                        imageByte = null;
                    }
                    
//                    Thread.sleep(400);
//                    refreshAvatar();
//                    Thread.sleep(600);
//                    refreshAvatar();
//                    Thread.sleep(850);
//                    refreshAvatar();

                } catch (Exception e) {
                    e.printStackTrace();
                }
//            }).start();
//        }
        this.repaint();
        this.revalidate();
        layoutBtn.repaint();
        layoutBtn.revalidate();
        init();
    }

    private void init() {

        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                bg.setBgColor(new Color(182, 204, 254));
                bg.repaint();
            }

            @Override
            public void mouseExited(MouseEvent e) {
                bg.setBgColor(new Color(215, 227, 252));
                bg.repaint();
            }
        });
        
        btnAS1.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                bg.setBgColor(new Color(182, 204, 254));
                bg.repaint();
            }
        });      
        
        btnAS2.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                bg.setBgColor(new Color(182, 204, 254));
                bg.repaint();
            }
        });
        
        event = new ControllerRequestFriend(request);
        btnAS1.addActionListener(event);
        btnAS2.addActionListener(event);
    }
   
    private void refreshAvatar(){
        avatar1.repaint();
        avatar1.revalidate();
    };
    
    public void setDataPeople(RequestFriend nrq) {
        this.request = nrq;
        btnAS2.setVisible(false);
        
        lb.setText(request.getFriend().getFriendName());
        
        int rqId = request.getRequester();
        
        if(rqId != -10) {
            ModelFriend uss = request.getFriend();
            if(uss.getStatus().equals("accepted")) {
                btnAS1.setText("Unfriend");
                btnAS1.setActionCommand("unfriend");
            } else {
                if(rqId == Client.getInstance().getUser().getUserID()) {
                    btnAS1.setVisible(true);
                    btnAS1.setText("Cancel");
                    btnAS1.setActionCommand("cancel");

                } else {
                    btnAS1.setVisible(true);
                    btnAS2.setVisible(true);
                    btnAS1.setText("Refuse");
                    btnAS1.setActionCommand("refuse");

                    btnAS2.setText("Confirm");
                    btnAS2.setActionCommand("confirm");
                }
            }
        } else {
            btnAS1.setVisible(false);
            btnAS2.setVisible(true);
            btnAS2.setText("Request");
            btnAS2.setActionCommand("request");

        }
        try {
            byte[] imageByte = Base64.getDecoder().decode(request.getFriend().getFriendImageString());
            Image image = PublicEvent.getInstance().getEventEncrypt().decodeImage(imageByte);
            if(image != null) {
                request.getFriend().setFriendImage(image);
                avatar1.setIcon(new ImageIcon(image));
                image.flush();
                imageByte = null;
            }
            
            Thread.sleep(50);
            refreshAvatar();
            Thread.sleep(100);
            refreshAvatar();
            Thread.sleep(150);
            refreshAvatar();
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        
        event.setRequest(request);
//        layoutBtn.repaint();
//        layoutBtn.revalidate();
    }
    
    public RequestFriend getUser() {
        return request;
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        bg = new swing.radius.PanelRadius();
        avatar1 = new swing.Avatar();
        lb = new javax.swing.JLabel();
        layoutBtn = new swing.card.PanelCard();
        btnAS1 = new swing.Button();
        btnAS2 = new swing.Button();

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

        layoutBtn.setOpaque(false);

        btnAS1.setBackground(new java.awt.Color(193, 211, 254));
        btnAS1.setText("Refuse");
        btnAS1.setActionCommand("delete");
        btnAS1.setRadius(15);
        layoutBtn.add(btnAS1);

        btnAS2.setBackground(new java.awt.Color(193, 211, 254));
        btnAS2.setText("Confirm");
        btnAS2.setActionCommand("confirm");
        btnAS2.setRadius(15);
        layoutBtn.add(btnAS2);

        javax.swing.GroupLayout bgLayout = new javax.swing.GroupLayout(bg);
        bg.setLayout(bgLayout);
        bgLayout.setHorizontalGroup(
            bgLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(bgLayout.createSequentialGroup()
                .addGap(12, 12, 12)
                .addComponent(avatar1, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lb, javax.swing.GroupLayout.DEFAULT_SIZE, 125, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(layoutBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 118, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(3, 3, 3))
        );
        bgLayout.setVerticalGroup(
            bgLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(bgLayout.createSequentialGroup()
                .addGap(8, 8, 8)
                .addGroup(bgLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(avatar1, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lb, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(layoutBtn, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
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
    private swing.Button btnAS1;
    private swing.Button btnAS2;
    private swing.card.PanelCard layoutBtn;
    private javax.swing.JLabel lb;
    // End of variables declaration//GEN-END:variables
}
