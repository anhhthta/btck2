package controller;

import event.EventContent;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import model.ModelUser;
import swing.Avatar;
import view.MainSystem;
import view.components.Content;

public class ControllerContent implements ActionListener, EventContent{

    private Content content;
    private MainSystem main;
    private Avatar btnAvatar;
    
    public ControllerContent(MainSystem main, Content content, Avatar btnAvatar) {
        this.main = main;
        this.content = content;
        this.btnAvatar = btnAvatar;
        
        btnAvatar.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                content.openInfo();
                main.isBack(true);
            }            
        });
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
