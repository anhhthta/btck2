package controller;

import event.PublicEvent;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import model.ModelFriend;
import model.ModelSend;
import model.ModelUser;
import model.RequestFriend;
import service.Client;
import utilites.UserAction;

public class ControllerRequestFriend implements ActionListener{
    private RequestFriend request;
    private ModelSend msg;
    private ModelUser user;
    public ControllerRequestFriend(RequestFriend request) {
        this.request = request;
        user = Client.getInstance().getUser();
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        String command = e.getActionCommand();
        
        if(command.equals("cancel")) {
            msg = new ModelSend();
            msg.setAction(UserAction.CANCEL_REQUESR);
            delete(msg);
        } else if(command.equals("refuse")) {
            msg = new ModelSend();
            msg.setAction(UserAction.REFUSE_REQUEST);
            delete(msg);
        } else if(command.equals("unfriend")) {
            msg = new ModelSend();
            msg.setAction(UserAction.REQUEST_UNFRIEND);
            delete(msg);
        } else if(command.equals("confirm")) {
            confirm();
        } else if(command.equals("request")) {
            request();
        }
    }
    
    private void delete(ModelSend msg) {
        request.setRequester(-10);
        
    
        msg.setTo(request.getFriend().getFriendId());
        msg.setFrom(user.getUserID());
        
        updateRqFriend();
        int i=0;
        
        List<ModelFriend> friend = Client.getInstance().getFriends();
        for(ModelFriend f : friend) {
            if(f.getFriendId() == request.getFriend().getFriendId()) {
                friend.remove(i);
                Client.getInstance().setFriends(friend);
                break;
            }
            ++i;
        }
        
        
        
        PublicEvent.getInstance().getEventUpdate().removeMenuItem(request.getFriend().getFriendId());
        
        PublicEvent.getInstance().getEventToServer().send(msg);
    }
    
    public void confirm() {
        request.getFriend().setStatus("accepted");
        request.setRequester(request.getFriend().getFriendId());
        msg = new ModelSend();
        msg.setAction(UserAction.CONFIRM_REQUEST);
    
        msg.setTo(request.getFriend().getFriendId());
        msg.setFrom(user.getUserID());
        
        updateRqFriend();
        
        Client.getInstance().getFriends().add(request.getFriend());
        PublicEvent.getInstance().getEventUpdate().addMenuItem(request.getFriend());
        
        PublicEvent.getInstance().getEventToServer().send(msg);
    }
    
    public void request() {
        request.setRequester(user.getUserID());
        request.getFriend().setStatus("pending");
        
        msg = new ModelSend();
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
    
    public void setRequest(RequestFriend request) {
        this.request = request;
    }
}
