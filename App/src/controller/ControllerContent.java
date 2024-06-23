package controller;

import event.EventContent;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import model.ModelFriend;
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
    public void selectedUser(ModelFriend user) {
        content.setUser(user);
        main.isBack(true);
    }

    @Override
    public void updateUser(ModelFriend user) {
        content.updateUser(user);
    }

    @Override
    public void changeContent(ModelFriend user) {
        content.openInfoOtherUser(user);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String command = e.getActionCommand();
        if(command.equals("back") || command.equals("getChats")) {
            content.back();
            main.isBack(false);
            main.chatsActive();
        } else if(command.equals("getPeople")) {
            content.openMenuAll();
            main.isBack(false);
            main.peopleActive();
        }
    }
    
}
