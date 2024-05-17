package controller;

import event.EventToServer;
import event.PublicEvent;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.ModelSendMessage;
import model.ModelUser;
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
                if (data.getAction() == UserAction.SEND_RECEIVE) {
                    PublicEvent.getInstance().getEventChat().ReceiveMessage(data);
                    Client.getInstance().getHistory().add(data);
                    PublicEvent.getInstance().getEventLastTime().setLastTime(data);
                } else if (data.getAction() == UserAction.UPDATE_INFO) {
                    int i = 0;
                    for (ModelUser user : Client.getInstance().getUsers()) {
                        if (user.getUserID() == data.getFrom()) {
                            Client.getInstance().getUsers().set(i, data.getUser());
                            break;
                        }
                        i++;
                    }

                    PublicEvent.getInstance().getEventUpdate().updateMenu();
                } else if(data.getAction() == UserAction.BAN) {
                    
                    if(data.getUser().getUserID() == Client.getInstance().getUser().getUserID()) {
                        PublicEvent.getInstance().getEventUpdate().clearMenu();
                    } else {
                        Client.getInstance().setUsers(data.getUsers());
                        PublicEvent.getInstance().getEventUpdate().updateMenu();
                    }
                } else{
                    Client.getInstance().setUsers(data.getUsers());
                    PublicEvent.getInstance().getEventUpdate().updateMenu();
                }
            }
        } catch (IOException ex) {
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ControllerToServer.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

}
