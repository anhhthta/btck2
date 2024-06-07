package controller;

import event.EventToServer;
import event.PublicEvent;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.ModelFriend;
import model.ModelSendMessage;
import model.ModelUser;
import model.RequestFriend;
import service.Client;
import utilites.UserAction;

public class ControllerToServer implements EventToServer {

    private Socket socket;
    private ObjectInputStream readerOj;
    private ObjectOutputStream writerOj;

    public ControllerToServer() {
        try {
            if ((this.socket = Client.getInstance().getSocket()) != null) {
                this.socket = Client.getInstance().getSocket();
                this.writerOj = new ObjectOutputStream(socket.getOutputStream());
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        receive();
                    }
                }).start();
            }

        } catch (IOException ex) {
            Logger.getLogger(ControllerLogin.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    int i = 0;

    @Override
    public void send(ModelSendMessage data) {
        try {
            writerOj.writeObject(data);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void receive() {
        ModelSendMessage data;
        try {
            if (readerOj == null) {
                readerOj = new ObjectInputStream(socket.getInputStream());
            }
            while ((data = (ModelSendMessage) readerOj.readObject()) != null) {
                UserAction uas = data.getAction();

                if (uas == UserAction.SEND_RECEIVE) {
                    PublicEvent.getInstance().getEventChat().ReceiveMessage(data);
                    Client.getInstance().getHistory().add(data);
                    PublicEvent.getInstance().getEventLastTime().setLastTime(data);
                } else if(uas == UserAction.REGISTER) {
                    int i = 0;
                    Client.getInstance().setRequest(data.getRequests());
                    for (RequestFriend request : Client.getInstance().getRequest()) {
                        if (request.getFriend().getFriendId()== data.getFrom()) {
                            
                            Client.getInstance().getRequest().set(i, request);
                            PublicEvent.getInstance().getEventUpdate().addMenuAllItem(request);
                            break;
                        }
                        i++;
                    }
                } else if (uas == UserAction.UPDATE_INFO) {
                    int i = 0;
                    for (ModelFriend friend : Client.getInstance().getFriends()) {
                        if (friend.getFriendId() == data.getFrom()) {
                            ModelUser uu = data.getUser();
                            ModelFriend nf = new ModelFriend(
                                            uu.getUserID(), 
                                            friend.getFriendId(), 
                                            uu.getUserName(), 
                                            uu.getImageString(),
                                            friend.getStatus()
                                    );
                            Client.getInstance().getFriends().set(i,nf);
                            
                            PublicEvent.getInstance().getEventUpdate().updateMenuItem(nf);
                            break;
                        }
                        i++;
                    }
                    i = 0;
                    for (RequestFriend request : Client.getInstance().getRequest()) {
                        if (request.getFriend().getFriendId()== data.getFrom()) {
                            ModelUser uu = data.getUser();
                            RequestFriend nrq = new RequestFriend(
                                            request.getRequester(),
                                            new ModelFriend(
                                                uu.getUserID(), 
                                                request.getFriend().getFriendId(), 
                                                uu.getUserName(), 
                                                uu.getImageString(),
                                                request.getFriend().getStatus()
                                ));
                            Client.getInstance().getRequest().set(i, nrq);
                            PublicEvent.getInstance().getEventUpdate().updateMenuAllItem(nrq);
                            break;
                        }
                        i++;
                    }
                    
                } else if(uas == UserAction.BAN || uas == UserAction.DELETE_USER) {
                    if(uas == UserAction.BAN) {
                        
                        if(data.getUser().getUserID() == Client.getInstance().getUser().getUserID()) {
                            PublicEvent.getInstance().getEventUpdate().clearMenu();
                            PublicEvent.getInstance().getEventUpdate().clearMenuAll();
                            socket.close();
                        } else {
                            Client.getInstance().setFriends(data.getFriends());
                            PublicEvent.getInstance().getEventUpdate().removeMenuItem(data.getUser().getUserID());
                        
                            Client.getInstance().setRequest(data.getRequests());
                            PublicEvent.getInstance().getEventUpdate().removeMenuAllItem(data.getUser().getUserID());
                        }
                    } else {
                        Client.getInstance().setFriends(data.getFriends());
                        PublicEvent.getInstance().getEventUpdate().removeMenuItem(data.getFrom());
                        
                        Client.getInstance().setRequest(data.getRequests());
                        PublicEvent.getInstance().getEventUpdate().removeMenuAllItem(data.getFrom());
                    }
                } else if(uas == UserAction.SEARCH) {
                    Client.getInstance().setRequest(data.getRequests());
                    PublicEvent.getInstance().getEventUpdate().setMenuAll();
                } else if(uas == UserAction.REQUEST_FRIEND
                        || uas == UserAction.CANCEL_REQUESR
                        || uas == UserAction.REFUSE_REQUEST
                    ) {
                    int i = 0;
                    Client.getInstance().setRequest(data.getRequests());
                    for (RequestFriend request : Client.getInstance().getRequest()) {
                        if (request.getFriend().getFriendId()== data.getFrom()) {
                            
                            Client.getInstance().getRequest().set(i, request);
                            PublicEvent.getInstance().getEventUpdate().updateMenuAllItem(request);
                            break;
                        }
                        i++;
                    }
                } else if(uas == UserAction.CONFIRM_REQUEST) {
                    int i = 0;
                    Client.getInstance().setRequest(data.getRequests());
                    for (RequestFriend request : Client.getInstance().getRequest()) {
                        if (request.getFriend().getFriendId()== data.getFrom()) {
                            
                            Client.getInstance().getRequest().set(i, request);
                            PublicEvent.getInstance().getEventUpdate().updateMenuAllItem(request);
                            PublicEvent.getInstance().getEventUpdate().addMenuItem(request.getFriend());
                            break;
                        }
                        i++;
                    }
                } else if(uas == UserAction.REQUEST_UNFRIEND) {
                    int i = 0;
                    Client.getInstance().setRequest(data.getRequests());
                    for (RequestFriend request : Client.getInstance().getRequest()) {
                        if (request.getFriend().getFriendId()== data.getFrom()) {
                            
                            Client.getInstance().getRequest().set(i, request);
                            PublicEvent.getInstance().getEventUpdate().updateMenuAllItem(request);
                            break;
                        }
                        i++;
                    }
                    PublicEvent.getInstance().getEventUpdate().removeMenuItem(data.getFrom());
                } else{
                    Client.getInstance().setRequest(data.getRequests());
                    Client.getInstance().setFriends(data.getFriends());
                    PublicEvent.getInstance().getEventUpdate().setMenu();
                    PublicEvent.getInstance().getEventUpdate().setMenuAll();
                }
            }
        } catch (IOException ex) {
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ControllerToServer.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

}
