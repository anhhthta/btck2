package view.components;


import controller.ControllerToServer;
import event.PublicEvent;
import model.ModelFriend;
import model.RequestFriend;


public class Content extends javax.swing.JLayeredPane {

    public Content() {
        initComponents();
        initEvent();
        infoUser.setVisible(false);
        chat.setVisible(false);
        menuAll.setVisible(false);
    }
    
    private void initEvent() {
        PublicEvent.getInstance().addEventToServer(new ControllerToServer());
    }
    
    public void setUser(ModelFriend user) {
        chat.setUser(user);
        chat.setVisible(true);
        menu.setVisible(false);
        infoUser.isShow(false);
        menuAll.setVisible(false);
    }
    
    public void updateUser(ModelFriend user) {
        chat.updateUser(user);
    }
    
    public void back() {
        menu.setVisible(true);
        chat.setVisible(false);
        infoUser.isShow(false);
        menuAll.setVisible(false);
    }
    
    public void openInfo() {
        infoUser.isShow(true);
        menu.setVisible(false);
        chat.setVisible(false);
        menuAll.setVisible(false);
    }
    
    public void openMenuAll() {
        menuAll.setVisible(true);
        infoUser.isShow(false);
        menu.setVisible(false);
        chat.setVisible(false);
    }
    
    public void setMenu() {
        menu.setPeople();
    }
    
    public void updateMenuItem(ModelFriend nf) {
        menu.updatePeople(nf);
    }
    
    public void addMenuItem(ModelFriend nf) {
        menu.addPeople(nf);
    }
    
    public void removeMenuItem(int nf) {
        menu.removePeople(nf);
    }
    
    public void clearMenu() {
        menu.clearP();
    }
    
    public void setMenuAll() {
        menuAll.setPeople();
    }
    
    public void updateMenuAllItem(RequestFriend nrq) {
        menuAll.updatePeople(nrq);
    }
    
    public void addMenuAllItem(RequestFriend nrq) {
        menuAll.addPeople(nrq);
    }
    
    public void removeMenuAllItem(int id) {
        menuAll.removePeople(id);
    }
    
    public void clearMenuAll() {
        menuAll.clearP();
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        menu = new view.components.Menu();
        chat = new view.components.Chat();
        infoUser = new view.components.InfoUser();
        menuAll = new view.components.MenuAll();

        setLayout(new java.awt.CardLayout());
        add(menu, "card2");
        add(chat, "card3");
        add(infoUser, "card4");
        add(menuAll, "card5");
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private view.components.Chat chat;
    private view.components.InfoUser infoUser;
    private view.components.Menu menu;
    private view.components.MenuAll menuAll;
    // End of variables declaration//GEN-END:variables
}
