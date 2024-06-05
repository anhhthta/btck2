package controller;

import event.PublicEvent;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import model.ModelFriend;
import model.ModelSendMessage;
import model.ModelUser;
import model.RequestFriend;
import service.Client;
import utilites.UserAction;

public class ControllerRequestFriend implements ActionListener{
    private RequestFriend request;
    private ModelSendMessage msg;
    private ModelUser user;
    public ControllerRequestFriend(RequestFriend request) {
        this.request = request;
        user = Client.getInstance().getUser();
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        String command = e.getActionCommand();
        
        if(command.equals("delete")) {
            delete();
        } else if(command.equals("confirm")) {
            confirm();
        } else if(command.equals("request")) {
            request();
        }
    }
    
    private void delete() {
        request.setRequester(-10);
        msg = new ModelSendMessage();
        msg.setAction(UserAction.DELETE_REQUEST);
    
        msg.setTo(request.getFriend().getFriendId());
        msg.setFrom(user.getUserID());
        
        updateRqFriend();
        int i=0;
        for(ModelFriend f : Client.getInstance().getFriends()) {
            if(f.getFriendId() == request.getFriend().getFriendId()) {
                Client.getInstance().getFriends().remove(i);
                break;
            }
            i++;
        }
        
        PublicEvent.getInstance().getEventUpdate().updateMenu();
        
        PublicEvent.getInstance().getEventToServer().send(msg);
    }
    
    public void confirm() {
        request.getFriend().setStatus("accepted");
        msg = new ModelSendMessage();
        msg.setAction(UserAction.CONFIRM_REQUEST);
    
        msg.setTo(request.getFriend().getFriendId());
        msg.setFrom(user.getUserID());
        
        updateRqFriend();
        
        Client.getInstance().getFriends().add(request.getFriend());
        PublicEvent.getInstance().getEventUpdate().updateMenu();
        
        PublicEvent.getInstance().getEventToServer().send(msg);
    }
    
    public void request() {
        request.setRequester(user.getUserID());
        request.getFriend().setStatus("pending");
        
        msg = new ModelSendMessage();
        msg.setAction(UserAction.REQUEST_FRIEND);
    
        msg.setTo(request.getFriend().getFriendId());
        msg.setFrom(user.getUserID());

        updateRqFriend();
        
        PublicEvent.getInstance().getEventToServer().send(msg);
    }
    public void updateRqFriend() {
        int i = 0;
        for(RequestFriend rq : Client.getInstance().getRequest()) {
            if(rq.getFriend() == request.getFriend()) {
                Client.getInstance().getRequest().set(i, request);
                break;
            }
            i++;
        }
        PublicEvent.getInstance().getEventUpdate().updateMenuAll();
    }
}
