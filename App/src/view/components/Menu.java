package view.components;

import event.EventLastTime;
import event.PublicEvent;
import java.awt.Component;
import java.util.ArrayList;
import java.util.List;
import model.ModelFriend;
import model.ModelSend;
import model.ModelUser;
import net.miginfocom.swing.MigLayout;
import service.Client;
import swing.ScrollBar;
import view.MainSystem;

/**
 *
 * @author anhth
 */
public class Menu extends javax.swing.JPanel {

    private List<ModelFriend> listFriends;
    private ModelUser user;

    public Menu() {
        initComponents();
        init();
    }

    private void init() {
        menuList.setLayout(new MigLayout("fillx", "0[313::313]0", "0[]0"));
        ScrollBar sb = new ScrollBar();
        sb.setSizeThumb(5, 12);
        scroll.setVerticalScrollBar(sb);

        listFriends = new ArrayList<>();
        user = Client.getInstance().getUser();
        
        setPeople();
        
        Menu t = this;
        
        PublicEvent.getInstance().addEventLastTime(new EventLastTime() {
            @Override
            public void setLastTime(ModelSend msg) {
                for(Component com : menuList.getComponents()) {
                    ItemFriend item = (ItemFriend) com;
                    int idItem = item.getFriend().getFriendId();
                    if(idItem == msg.getUser().getUserID() || idItem == msg.getTo()) {

                        item.setLastText(msg);
                        break;
                    }
                }
            }
        });
        
    }

    public void setPeople() {
        listFriends = Client.getInstance().getFriends();
        if (listFriends == null) {
            listFriends = new ArrayList<>();
        }
        menuList.removeAll();
        menuList.add(new ItemFriend(new ModelFriend(0, -1, "Group", MainSystem.groupImage, "")), "wrap");
        for (ModelFriend us : listFriends) {
            if (user.getUserID() != us.getFriendId()) {
                menuList.add(new ItemFriend(us), "wrap");
            }
        }
        refreshMenuList();
    }

    public void updatePeople(ModelFriend nf) {
        for(Component m : menuList.getComponents()) {
            ItemFriend item = (ItemFriend) m;
            if(nf.getFriendId() == item.getFriend().getFriendId()) {
                item.setDataFriend(nf);
            }
        }
    }
    
    public void addPeople(ModelFriend nf) {
        menuList.add(new ItemFriend(nf), "wrap");
    } 
    
    public void removePeople(int nf) {
        for(Component m : menuList.getComponents()) {
            ItemFriend item = (ItemFriend) m;
            if(nf == item.getFriend().getFriendId()) {
                menuList.remove(m);
            }
        }
        refreshMenuList();

    } 
    
    public void clearP() {
        menuList.removeAll();
        refreshMenuList();
    }
    
    private void refreshMenuList() {
        menuList.repaint();
        menuList.revalidate();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        scroll = new javax.swing.JScrollPane();
        menuList = new javax.swing.JLayeredPane();

        setBackground(new java.awt.Color(215, 227, 252));

        jPanel1.setBackground(new java.awt.Color(193, 211, 254));

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel1.setText("Chats");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(8, 8, 8)
                .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jLabel1)
                .addGap(0, 4, Short.MAX_VALUE))
        );

        scroll.setBackground(new java.awt.Color(255, 255, 255));
        scroll.setBorder(null);
        scroll.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);

        menuList.setBackground(new java.awt.Color(215, 227, 252));
        menuList.setOpaque(true);

        javax.swing.GroupLayout menuListLayout = new javax.swing.GroupLayout(menuList);
        menuList.setLayout(menuListLayout);
        menuListLayout.setHorizontalGroup(
            menuListLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 325, Short.MAX_VALUE)
        );
        menuListLayout.setVerticalGroup(
            menuListLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 488, Short.MAX_VALUE)
        );

        scroll.setViewportView(menuList);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(scroll)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(scroll)
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JLayeredPane menuList;
    private javax.swing.JScrollPane scroll;
    // End of variables declaration//GEN-END:variables
}
