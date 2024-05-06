package controller;

import event.EventContent;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import model.ModelUser;
import view.MainSystem;
import view.components.Content;

public class ControllerContent implements ActionListener, EventContent{

    private Content content;
    private MainSystem main;
    
    public ControllerContent(MainSystem main, Content content) {
        this.main = main;
        this.content = content;
    }
    
    @Override
    public void selectedUser(ModelUser user) {
        content.setUser(user);
        main.isBack(true);
    }

    @Override
    public void updateUser(ModelUser user) {
        content.updateUser(user);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String command = e.getActionCommand();
        if(command.equals("back")) {
            System.out.println("was");
            content.back();
            main.isBack(false);
        }
    }
    
}
