package service;

import DAO.FriendDAO;
import DAO.MesageDAO;
import DAO.UserDAO;
import event.Event1;
import event.PublicEvent;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JTextArea;
import model.ModelFriend;
import model.ModelSendMessage;
import model.ModelUserReceive;
import utilites.UserAction;

/**
 *
 * @author anhth
 */
public class Handler {

    private final String SEND_RECEIVE = "msg";
    private static HashSet<ModelUserReceive> clientWriters = new HashSet<>();

    private Socket socket;
    private ObjectOutputStream writer;
    ModelUserReceive mr;
    private int userID;
    private JTextArea jTextArea1;
    private String as;

    public Handler(Socket socket, int userId, JTextArea jTextArea, String as) throws SQLException {
        this.socket = socket;
        this.userID = userId;
        this.jTextArea1 = jTextArea;
        this.as = as;
        run();
    }

    public void run() throws SQLException {
        try {

            int i = 0;
            ObjectInputStream reader = new ObjectInputStream(socket.getInputStream());
            writer = new ObjectOutputStream(socket.getOutputStream());
            mr = new ModelUserReceive(writer, userID);
            clientWriters.add(mr);

            PublicEvent.getInstance().addEvent1(new Event1() {
                @Override
                public void send(ModelSendMessage msg) {
                    try {
                        msg.setTo(-11);
                        broadcast(msg);
                    } catch (IOException ex) {
                        Logger.getLogger(Handler.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            });
            
            if(as.equals("register")) {
                ModelSendMessage msgRegister = new ModelSendMessage();
                msgRegister.setTo(-1);
                msgRegister.setFrom(userID);
                msgRegister.setAction(UserAction.REGISTER);
                broadcast(msgRegister);
            }

            ModelSendMessage message;

            while ((message = (ModelSendMessage) reader.readObject()) != null) {
 
                UserAction uas = message.getAction();
                if (uas == UserAction.SEND_RECEIVE) {
                    new MesageDAO().insertMessage(message);
                } else if (uas == UserAction.UPDATE_INFO) {
                    new UserDAO().update(message.getUser());
                    PublicEvent.getInstance().getEventt().setData();

                } else if (uas == UserAction.DELETE_USER) {
                    new UserDAO().delete(message.getUser());
                    new MesageDAO().delete(message.getUser().getUserID());
                    PublicEvent.getInstance().getEventt().setData();
                } else if (uas == UserAction.REQUEST_UNFRIEND 
                        || uas == UserAction.REFUSE_REQUEST 
                        || uas == UserAction.CANCEL_REQUESR 
                    ) {
                    new FriendDAO().delete(message.getFrom(), message.getTo());
                } else if (message.getAction() == UserAction.CONFIRM_REQUEST) {
                    new FriendDAO().update(message.getFrom(), message.getTo(), "accepted");
                } else if (message.getAction() == UserAction.REQUEST_FRIEND) {
                    ModelFriend friend = new ModelFriend(message.getFrom(), message.getTo(), "pending");
                    new FriendDAO().insert(friend);
                }

                broadcast(message);
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            if (writer != null) {
                clientWriters.remove(mr);
            }
            try {
                jTextArea1.append("Client Closed: " + socket.getInetAddress() + "\n");
                socket.close();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
    }

    private void broadcast(ModelSendMessage message) throws IOException {
        UserAction uas = message.getAction();
        if (message.getTo() == -1) {
            for (ModelUserReceive clientWriter : clientWriters) {
                if (clientWriter.getOb() != writer) {
                    if( uas == UserAction.DELETE_USER 
                            || uas == UserAction.LOGIN){
                        message.setRequests(new UserDAO().getRequestUsers(clientWriter.getUserID(), ""));
                        message.setFriends(new FriendDAO().getFriends(clientWriter.getUserID()));
                    } else if (uas == UserAction.REGISTER) {
                        message.setRequests(new UserDAO().getRequestUsers(clientWriter.getUserID(), ""));
                    }
                    clientWriter.getOb().writeObject(message);
                }
            }
        } else if(message.getTo() == -11) {
            for (ModelUserReceive clientWriter : clientWriters) {
                message.setFriends(new FriendDAO().getFriends(clientWriter.getUserID()));
                clientWriter.getOb().writeObject(message);
                if(clientWriter.getUserID() == userID) {
                    clientWriters.remove(clientWriter);
                }
            }
            if (writer != null) {
                clientWriters.remove(mr);
            }
        }else {
            for (ModelUserReceive clientWriter : clientWriters) {
                if (clientWriter.getUserID() == message.getTo()) {
                    
                    if(uas == UserAction.REQUEST_UNFRIEND
                            || uas == UserAction.REFUSE_REQUEST
                            || uas == UserAction.CANCEL_REQUESR
                            || uas == UserAction.CONFIRM_REQUEST 
                            || uas == UserAction.REQUEST_FRIEND
                            ) {
                        message.setRequests(new UserDAO().getRequestUsers(clientWriter.getUserID(), ""));
                        message.setFriends(new FriendDAO().getFriends(clientWriter.getUserID()));
                    } else if(message.getAction() == UserAction.SEARCH) {
                        message.setRequests(new UserDAO().getRequestUsers(clientWriter.getUserID(), message.getText()));
                    }
                    clientWriter.getOb().writeObject(message);
                    break;
                }
            }
        }
    }
}
