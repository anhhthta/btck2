package view;

import controller.ControllerContent;
import event.EventContent;
import event.EventUpdate;
import event.PublicEvent;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Image;
import java.awt.event.ActionListener;
import java.awt.event.MouseListener;
import java.util.Base64;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import model.ModelUser;
import service.Client;
import swing.ComponentResizer;

/**
 *
 * @author anhth
 */
public class MainSystem extends javax.swing.JFrame {
    
    private int pX, pY;
    public static String groupImage;
    
    

    public MainSystem() {
        Image image = new ImageIcon(getClass().getResource("/icon/avatarG-50.png")).getImage();
        groupImage = PublicEvent.getInstance().getEventEncrypt().encodeImage(image, "png");

        initComponents();
        init();
        btnBack.setVisible(false);
        btnBack.setHover(new Color(171, 196, 255));
//        btnChats.setHover(new Color(171, 196, 255));
//        btnPeople.setHover(new Color(171, 196, 255));
    }

    private void init() {
        btnClose.setHover(new Color(255, 51, 51));
        btnMinimize.setHover(new Color(72, 72, 72));
        ComponentResizer com = new ComponentResizer();
        com.registerComponent(this);
        com.setMinimumSize(new Dimension(340, 539));
        com.setMaximumSize(new Dimension(340, 539));
        com.setSnapSize(new Dimension(10, 10));
        ModelUser user = Client.getInstance().getUser();

        userName.setText(user.getUserName());
//        Decode
        try {
            byte[] imageByte = Base64.getDecoder().decode(user.getImage());
            Image image = PublicEvent.getInstance().getEventEncrypt().decodeImage(imageByte);
            btnAvatar.setIcon(new ImageIcon(image));

        } catch (Exception e) {
            e.printStackTrace();
        }

        ActionListener event = new ControllerContent(this, content, btnAvatar);
        btnBack.addActionListener(event);
        btnChats.addActionListener(event);
        btnPeople.addActionListener(event);

        PublicEvent.getInstance().addEventContent((EventContent) event);
        
        MainSystem main = this;
        PublicEvent.getInstance().addEventUpdate(new EventUpdate() {
            @Override
            public void updateHeader() {
                ModelUser user = Client.getInstance().getUser();
                userName.setText(user.getUserName());
                try {
                    byte[] imageByte = Base64.getDecoder().decode(user.getImage());
                    Image image = PublicEvent.getInstance().getEventEncrypt().decodeImage(imageByte);
                    btnAvatar.setIcon(new ImageIcon(image));
                    main.repaint();
                    main.revalidate();
                } catch (Exception e) {
                    e.printStackTrace();
                }
                revalidate();
            }

            @Override
            public void updateMenu() {
                content.setMenu();
            }

            @Override
            public void clearMenu() {
                content.clearMenu();
                for(MouseListener event : btnAvatar.getMouseListeners()) {
                    btnAvatar.removeMouseListener(event);
                    userName.setForeground(new Color(204,0,0));
                    userName.setText("Tài khoản của bạn đã bị xóa");
                }
            }

            @Override
            public void updateMenuAll() {
                content.setMenuAll();
            }

            
        });
    }

    public void isBack(boolean c) {
        btnBack.setVisible(c);
        if(c) {
            btnPeople.setBackground(new Color(193,211,254));
            btnChats.setBackground(new Color(193,211,254));
        }
    }
    
    public void chatsActive(){
        btnChats.setBackground(new Color(72, 149, 239));
        btnPeople.setBackground(new Color(193,211,254));
    }
    
    public void peopleActive() {
        btnPeople.setBackground(new Color(72, 149, 239));
        btnChats.setBackground(new Color(193,211,254));
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        border = new javax.swing.JPanel();
        background = new javax.swing.JPanel();
        title = new javax.swing.JPanel();
        btnMinimize = new swing.Button();
        btnClose = new swing.Button();
        body = new javax.swing.JLayeredPane();
        content = new view.components.Content();
        h = new javax.swing.JPanel();
        btnAvatar = new swing.Avatar();
        userName = new javax.swing.JLabel();
        panelCard1 = new swing.card.PanelCard();
        btnBack = new swing.Button();
        btnChats = new swing.Button();
        btnPeople = new swing.Button();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(true);

        border.setBackground(new java.awt.Color(229, 218, 255));

        background.setBackground(new java.awt.Color(255, 255, 255));

        title.setBackground(new java.awt.Color(51, 51, 51));
        title.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseDragged(java.awt.event.MouseEvent evt) {
                titleMouseDragged(evt);
            }
        });
        title.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                titleMousePressed(evt);
            }
        });

        btnMinimize.setBackground(new java.awt.Color(51, 51, 51));
        btnMinimize.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/minimize-18.png"))); // NOI18N
        btnMinimize.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnMinimizeActionPerformed(evt);
            }
        });

        btnClose.setBackground(new java.awt.Color(51, 51, 51));
        btnClose.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/close_16.png"))); // NOI18N
        btnClose.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCloseActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout titleLayout = new javax.swing.GroupLayout(title);
        title.setLayout(titleLayout);
        titleLayout.setHorizontalGroup(
            titleLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, titleLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnMinimize, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(btnClose, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        titleLayout.setVerticalGroup(
            titleLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(titleLayout.createSequentialGroup()
                .addGroup(titleLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(btnClose, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnMinimize, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(0, 0, Short.MAX_VALUE))
        );

        body.setLayout(new java.awt.CardLayout());

        content.setOpaque(true);
        body.add(content, "card2");

        h.setBackground(new java.awt.Color(193, 211, 254));

        btnAvatar.setForeground(new java.awt.Color(255, 255, 255));
        btnAvatar.setToolTipText("");
        btnAvatar.setBorderColor(new java.awt.Color(255, 204, 204));
        btnAvatar.setBorderSize(1);
        btnAvatar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/avatar2-50.png"))); // NOI18N

        userName.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        userName.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        userName.setText("jLabel1");
        userName.setToolTipText("");

        panelCard1.setBackground(new java.awt.Color(193, 211, 254));

        btnBack.setBackground(new java.awt.Color(193, 211, 254));
        btnBack.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 3));
        btnBack.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/back-20.png"))); // NOI18N
        btnBack.setActionCommand("back");
        btnBack.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        btnBack.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnBack.setMaximumSize(new java.awt.Dimension(33, 33));
        btnBack.setMinimumSize(new java.awt.Dimension(33, 33));
        btnBack.setPreferredSize(new java.awt.Dimension(33, 33));
        btnBack.setRadius(100);
        panelCard1.add(btnBack);

        btnChats.setBackground(new java.awt.Color(72, 149, 239));
        btnChats.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 3, 1, 1));
        btnChats.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/btn-message-28.png"))); // NOI18N
        btnChats.setActionCommand("getChats");
        btnChats.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        btnChats.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnChats.setMaximumSize(new java.awt.Dimension(33, 33));
        btnChats.setMinimumSize(new java.awt.Dimension(33, 33));
        btnChats.setPreferredSize(new java.awt.Dimension(33, 33));
        btnChats.setRadius(100);
        panelCard1.add(btnChats);

        btnPeople.setBackground(new java.awt.Color(193, 211, 254));
        btnPeople.setBorder(javax.swing.BorderFactory.createEmptyBorder(5, 3, 1, 1));
        btnPeople.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/btn-group-28-1.png"))); // NOI18N
        btnPeople.setActionCommand("getPeople");
        btnPeople.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        btnPeople.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnPeople.setMaximumSize(new java.awt.Dimension(33, 33));
        btnPeople.setMinimumSize(new java.awt.Dimension(33, 33));
        btnPeople.setPreferredSize(new java.awt.Dimension(33, 33));
        btnPeople.setRadius(100);
        panelCard1.add(btnPeople);

        javax.swing.GroupLayout hLayout = new javax.swing.GroupLayout(h);
        h.setLayout(hLayout);
        hLayout.setHorizontalGroup(
            hLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, hLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(panelCard1, javax.swing.GroupLayout.DEFAULT_SIZE, 136, Short.MAX_VALUE)
                .addGap(0, 0, 0)
                .addComponent(userName, javax.swing.GroupLayout.PREFERRED_SIZE, 137, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnAvatar, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        hLayout.setVerticalGroup(
            hLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(hLayout.createSequentialGroup()
                .addGroup(hLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(hLayout.createSequentialGroup()
                        .addGap(4, 4, 4)
                        .addComponent(btnAvatar, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(hLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(userName)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, hLayout.createSequentialGroup()
                .addComponent(panelCard1, javax.swing.GroupLayout.DEFAULT_SIZE, 44, Short.MAX_VALUE)
                .addContainerGap())
        );

        javax.swing.GroupLayout backgroundLayout = new javax.swing.GroupLayout(background);
        background.setLayout(backgroundLayout);
        backgroundLayout.setHorizontalGroup(
            backgroundLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(title, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(h, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(body, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
        );
        backgroundLayout.setVerticalGroup(
            backgroundLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(backgroundLayout.createSequentialGroup()
                .addComponent(title, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(h, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(body, javax.swing.GroupLayout.DEFAULT_SIZE, 484, Short.MAX_VALUE)
                .addGap(0, 0, 0))
        );

        javax.swing.GroupLayout borderLayout = new javax.swing.GroupLayout(border);
        border.setLayout(borderLayout);
        borderLayout.setHorizontalGroup(
            borderLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(borderLayout.createSequentialGroup()
                .addGap(1, 1, 1)
                .addComponent(background, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(1, 1, 1))
        );
        borderLayout.setVerticalGroup(
            borderLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(borderLayout.createSequentialGroup()
                .addGap(1, 1, 1)
                .addComponent(background, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(1, 1, 1))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(border, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(border, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void titleMouseDragged(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_titleMouseDragged
        this.setLocation(this.getLocation().x + evt.getX() - pX, this.getLocation().y + evt.getY() - pY);
    }//GEN-LAST:event_titleMouseDragged

    private void titleMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_titleMousePressed
        pX = evt.getX();
        pY = evt.getY();
    }//GEN-LAST:event_titleMousePressed

    private void btnCloseActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCloseActionPerformed
        System.exit(0);
    }//GEN-LAST:event_btnCloseActionPerformed

    private void btnMinimizeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnMinimizeActionPerformed
        this.setState(JFrame.ICONIFIED);
    }//GEN-LAST:event_btnMinimizeActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel background;
    private javax.swing.JLayeredPane body;
    private javax.swing.JPanel border;
    private swing.Avatar btnAvatar;
    private swing.Button btnBack;
    private swing.Button btnChats;
    private swing.Button btnClose;
    private swing.Button btnMinimize;
    private swing.Button btnPeople;
    private view.components.Content content;
    private javax.swing.JPanel h;
    private swing.card.PanelCard panelCard1;
    private javax.swing.JPanel title;
    private javax.swing.JLabel userName;
    // End of variables declaration//GEN-END:variables
}
