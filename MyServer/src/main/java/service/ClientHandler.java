/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package service;

import DAO.FriendDAO;
import DAO.MesageDAO;
import DAO.UserDAO;
import event.PublicEvent;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JTextArea;
import model.ModelFriend;
import model.ModelMessage;
import model.ModelSendMessage;
import model.ModelUser;
import model.RequestFriend;

/**
 *
 * @author anhth
 */
public class ClientHandler implements Runnable {

    private Socket socket;
    private JTextArea jTextArea;
    
    public ClientHandler(Socket socket, JTextArea jTextArea) {
        this.socket = socket;
        this.jTextArea = jTextArea;
    }

    public void run() {
        try {
            ObjectInputStream readerOj1 = new ObjectInputStream(socket.getInputStream());
            ObjectInputStream readerOj = new ObjectInputStream(socket.getInputStream());
            ObjectOutputStream writerOj1 = new ObjectOutputStream(socket.getOutputStream());
            ObjectOutputStream writerOj2 = new ObjectOutputStream(socket.getOutputStream());
            ObjectOutputStream writerOj3 = new ObjectOutputStream(socket.getOutputStream());

            String request;
            while ((request = (String) readerOj1.readObject()) != null) {
                if (request != null && request.equals("LOGIN")) {
                    handleLogin(readerOj, writerOj1, writerOj2, writerOj3, socket, jTextArea);
                } else if (request != null && request.equals("REGISTER")) {
                    handleRegister(readerOj, writerOj1, writerOj2, writerOj3, socket, jTextArea);
                }

            }

            socket.close();
        } catch (IOException e) {
        } catch (ClassNotFoundException | SQLException ex) {
            jTextArea.append("Client Closed\n");
            Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
        private void handleLogin(ObjectInputStream ois, ObjectOutputStream writerOj1, ObjectOutputStream writerOj2, ObjectOutputStream writerOj3, Socket socket, JTextArea jTextArea) throws IOException, ClassNotFoundException, SQLException {
        ModelUser user;
        if ((user = (ModelUser) ois.readObject()) != null) {
            ModelMessage login = new UserDAO().login(user);

            if (login.isSuccess()) {
                int userID = ((ModelUser) login.getData()).getUserID();
                writerOj1.writeObject(login);
                List<ModelSendMessage> history = new MesageDAO().getMessage();
                writerOj2.writeObject(history);
                
                List<RequestFriend> requests = new UserDAO().getRequestUsers(userID, "");
                List<ModelFriend> friends = new FriendDAO().getFriends(userID);
                ModelSendMessage msgu = new ModelSendMessage(requests, friends);
                writerOj3.writeObject(msgu);
                new Handler(socket, userID, jTextArea);
            } else {
                writerOj1.writeObject(login);
            }
        }

    }

    private void handleRegister(ObjectInputStream ois, ObjectOutputStream writerOj1, ObjectOutputStream writerOj2, ObjectOutputStream writerOj3, Socket socket, JTextArea jTextArea) throws IOException, SQLException, ClassNotFoundException {
        ModelUser user;
        if ((user = (ModelUser) ois.readObject()) != null) {
            ModelMessage register = new UserDAO().insert(user);

            if (register.isSuccess()) {
                int userID = ((ModelUser) register.getData()).getUserID();

                writerOj1.writeObject(register);
                List<ModelSendMessage> history = new MesageDAO().getMessage();
                writerOj2.writeObject(history);
                
                List<RequestFriend> requests = new UserDAO().getRequestUsers(userID, "");
                List<ModelFriend> friends = new FriendDAO().getFriends(userID);
                ModelSendMessage msgu = new ModelSendMessage(requests, friends);
                writerOj3.writeObject(msgu);
                PublicEvent.getInstance().getEventt().setData();
                new Handler(socket, userID, jTextArea);
            } else {
                writerOj1.writeObject(register);
            }
        }
    }

}
