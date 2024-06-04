package service;

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

    public Handler(Socket socket, int userId, JTextArea jTextArea) throws SQLException {
        this.socket = socket;
        this.userID = userId;
        this.jTextArea1 = jTextArea;
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
                    for (ModelUserReceive clientWriter : clientWriters) {
                        try {

                            clientWriter.getOb().writeObject(msg);
                        } catch (IOException ex) {
                            Logger.getLogger(Handler.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }

                    if (writer != null) {
                        clientWriters.remove(mr);
                    }
                }
            });

            ModelSendMessage firstMesage = new ModelSendMessage();
            firstMesage.setTo(-1);
            firstMesage.setFrom(userID);
            firstMesage.setAction(UserAction.LOGIN);
            firstMesage.setUsers(new UserDAO().getUsers());
            broadcast(firstMesage);

            ModelSendMessage message;

            while ((message = (ModelSendMessage) reader.readObject()) != null) {
                System.out.println("DAY ner:: " + message.getUser().getUserName());
                System.out.println("TxT " + message.getText());

                if (message.getAction() == UserAction.SEND_RECEIVE) {
                    new MesageDAO().insertMessage(message);
                } else if (message.getAction() == UserAction.UPDATE_INFO) {
                    new UserDAO().update(message.getUser());
                    System.out.println("UserName: " + message.getUser().getUserName());
                    PublicEvent.getInstance().getEventt().setData();
                } else if (message.getAction() == UserAction.DELETE_USER) {
                    new UserDAO().delete(message.getUser());
                    message.setUsers(new UserDAO().getUsers());
                    System.out.println(message.getUser().getUserID());
                    new MesageDAO().delete(message.getUser().getUserID());
                    PublicEvent.getInstance().getEventt().setData();
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

        if (message.getTo() == -1) {
            for (ModelUserReceive clientWriter : clientWriters) {
                if (clientWriter.getOb() != writer) {
                    clientWriter.getOb().writeObject(message);
                }
            }
        } else {
            for (ModelUserReceive clientWriter : clientWriters) {
                if (clientWriter.getUserID() == message.getTo()) {
                    clientWriter.getOb().writeObject(message);
                    break;
                }
            }
        }
    }
}
