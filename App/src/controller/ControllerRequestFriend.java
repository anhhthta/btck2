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
        
        if(command.equals("cancel")) {
            msg = new ModelSendMessage();
            msg.setAction(UserAction.CANCEL_REQUESR);
            System.out.println("was cancel");
            delete(msg);
        } else if(command.equals("refuse")) {
            msg = new ModelSendMessage();
            msg.setAction(UserAction.REFUSE_REQUEST);
            delete(msg);
        } else if(command.equals("unfriend")) {
            msg = new ModelSendMessage();
            msg.setAction(UserAction.REQUEST_UNFRIEND);
            delete(msg);
        } else if(command.equals("confirm")) {
            confirm();
        } else if(command.equals("request")) {
            request();
        }
    }
    
    private void delete(ModelSendMessage msg) {
        request.setRequester(-10);
        
    
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
        
        PublicEvent.getInstance().getEventUpdate().setMenu();
        
        PublicEvent.getInstance().getEventToServer().send(msg);
    }
    
    public void confirm() {
        request.getFriend().setStatus("accepted");
        request.setRequester(request.getFriend().getFriendId());
        msg = new ModelSendMessage();
        msg.setAction(UserAction.CONFIRM_REQUEST);
    
        msg.setTo(request.getFriend().getFriendId());
        msg.setFrom(user.getUserID());
        
        updateRqFriend();
        
        Client.getInstance().getFriends().add(request.getFriend());
        PublicEvent.getInstance().getEventUpdate().setMenu();
        
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
        PublicEvent.getInstance().getEventUpdate().updateMenuAllItem(request);
    }
}
