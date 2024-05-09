package view.components;


import controller.ControllerToServer;
import event.PublicEvent;
import model.ModelUser;


public class Content extends javax.swing.JLayeredPane {

    public Content() {
        initComponents();
        initEvent();
        infoUser.setVisible(false);
        chat.setVisible(false);

    }
    
    private void initEvent() {
        PublicEvent.getInstance().addEventToServer(new ControllerToServer());
    }
    
    public void setUser(ModelUser user) {
        chat.setUser(user);
        chat.setVisible(true);
        menu.setVisible(false);
        infoUser.isShow(false);

    }
    
    public void updateUser(ModelUser user) {
        chat.updateUser(user);
    }
    
    public void back() {
        menu.setVisible(true);
        chat.setVisible(false);
        infoUser.isShow(false);
    }
    
    public void openInfo() {
        infoUser.isShow(true);
        menu.setVisible(false);
        chat.setVisible(false);
    }
    
    public void setMenu() {
        menu.setPeople();
    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        menu = new view.components.Menu();
        chat = new view.components.Chat();
        infoUser = new view.components.InfoUser();

        setLayout(new java.awt.CardLayout());
        add(menu, "card2");
        add(chat, "card3");
        add(infoUser, "card4");
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private view.components.Chat chat;
    private view.components.InfoUser infoUser;
    private view.components.Menu menu;
    // End of variables declaration//GEN-END:variables
}
