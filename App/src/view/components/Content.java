package view.components;


import model.ModelUser;


public class Content extends javax.swing.JLayeredPane {

    public Content() {
        initComponents();
        System.out.println("sout: " + chat.isVisible());
    }
    
    public void setUser(ModelUser user) {
        chat.setUser(user);
        chat.setVisible(true);
        menuLeft.setVisible(false);

//        menuLeft.setVisible(false);
    }
    
    public void updateUser(ModelUser user) {
        chat.updateUser(user);
    }
    
    public void back() {
        menuLeft.setVisible(true);
        chat.setVisible(false);

    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        menuLeft = new view.components.MenuLeft();
        chat = new view.components.Chat();

        setLayout(new java.awt.CardLayout());
        add(menuLeft, "card2");
        add(chat, "card3");
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private view.components.Chat chat;
    private view.components.MenuLeft menuLeft;
    // End of variables declaration//GEN-END:variables
}
