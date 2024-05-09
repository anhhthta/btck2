package view.components;

import event.PublicEvent;
import java.awt.Component;
import java.util.ArrayList;
import java.util.List;
import model.ModelUser;
import net.miginfocom.swing.MigLayout;
import service.Client;
import swing.ScrollBar;
import event.EventMenu;
import view.MainSystem;

/**
 *
 * @author anhth
 */
public class Menu extends javax.swing.JPanel {

    private List<ModelUser> listUser;
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

        listUser = new ArrayList<>();
        user = Client.getInstance().getUser();

        PublicEvent.getInstance().addEventMenu(new EventMenu() {
            @Override
            public void newUser(List<ModelUser> users) {
                for (ModelUser us : users) {
                    if (user.getUserID() != us.getUserID()) {
                        listUser.add(us);
                        menuList.add(new ItemPeople(us), "wrap");
                        refreshMenuList();
                    }
                }
            }

            @Override
            public void userConnect(int userId) {
                for (ModelUser u : listUser) {
                    if (u.getUserID() == userId) {
                        u.setStatus(true);
                        PublicEvent.getInstance().getEventContent().updateUser(u);
                        break;
                    }
                }

                for (Component com : menuList.getComponents()) {
                    ItemPeople item = (ItemPeople) com;
                    if (item.getUser().getUserID() == userId) {
                        item.updateStatus();
                        break;
                    }
                }
            }

            @Override
            public void userDisconnect(int userId) {
                for (ModelUser u : listUser) {
                    if (u.getUserID() == userId) {
                        u.setStatus(false);
                        PublicEvent.getInstance().getEventContent().updateUser(u);
                        break;
                    }
                }

                for (Component com : menuList.getComponents()) {
                    ItemPeople item = (ItemPeople) com;
                    if (item.getUser().getUserID() == userId) {
                        item.updateStatus();
                        break;
                    }
                }
            }
        });

        setPeople();
    }

     public void setPeople() {
        listUser = Client.getInstance().getUsers();
        if (listUser == null) {
            listUser = new ArrayList<>();
        }
        menuList.removeAll();
        menuList.add(new ItemPeople(new ModelUser(-1, "Group", "", "", MainSystem.groupImage, true)), "wrap");
        for (ModelUser us : listUser) {
            if (user.getUserID() != us.getUserID()) {
                menuList.add(new ItemPeople(us), "wrap");
                System.out.println("new User" +us.getUserName());
            }
        }
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

        setBackground(new java.awt.Color(255, 255, 255));

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel1.setText("Chat");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(8, 8, 8)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 86, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(139, 139, 139))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        scroll.setBackground(new java.awt.Color(255, 255, 255));
        scroll.setBorder(null);
        scroll.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);

        menuList.setBackground(new java.awt.Color(255, 255, 255));
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
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(scroll)
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
