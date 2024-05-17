//package main.newpackage;
//
//import DAO.MesageDAO;
//import DAO.UserDAO;
//import java.io.*;
//import java.net.*;
//import java.sql.SQLException;
//import java.util.*;
//import java.util.logging.Level;
//import java.util.logging.Logger;
//import javax.swing.JTextArea;
//import model.ModelMessage;
//import model.ModelSendMessage;
//import model.ModelUser;
//import model.ModelUserReceive;
//import utilites.UserAction;
//
//public class Server {
//
//    private static final int PORT = 9999;
//    private static HashSet<ModelUserReceive> clientWriters = new HashSet<>();
//
//    private JTextArea jTextArea;
//    public Server(JTextArea jTextArea) {
//        this.jTextArea = jTextArea;
//        try (ServerSocket serverSocket = new ServerSocket(PORT)) {
//            jTextArea.append("Server is running... (" + PORT +")\n");
//
//            while (true) {
//                Socket socket = serverSocket.accept();
//                System.out.println();
//                jTextArea.append("Client connected: " + socket.getInetAddress() +"\n");
//                Thread thread = new Thread(new ClientHandler(socket));
//                thread.start();
//            }
//        } catch (IOException e) {
//            jTextArea.append("Client Closed\n");
//            e.printStackTrace();
//        }
//
//    }
//
//    class ClientHandler implements Runnable {
//
//        private Socket socket;
//
//        public ClientHandler(Socket socket) {
//            this.socket = socket;
//        }
//
//        public void run() {
//            try {
//                ObjectInputStream readerOj1 = new ObjectInputStream(socket.getInputStream());
//                ObjectInputStream readerOj = new ObjectInputStream(socket.getInputStream());
//                ObjectOutputStream writerOj1 = new ObjectOutputStream(socket.getOutputStream());
//                ObjectOutputStream writerOj2 = new ObjectOutputStream(socket.getOutputStream());
//                ObjectOutputStream writerOj3 = new ObjectOutputStream(socket.getOutputStream());
//
//                String request;
//                while ((request = (String) readerOj1.readObject()) != null) {
//                    if (request != null && request.equals("LOGIN")) {
//                        handleLogin(readerOj, writerOj1, writerOj2, writerOj3, socket, jTextArea);
//                    } else if (request != null && request.equals("REGISTER")) {
//                        handleRegister(readerOj, writerOj1, writerOj2, writerOj3, socket, jTextArea);
//                    }
//
//                }
//
//                socket.close();
//            } catch (IOException e) {
//                e.printStackTrace();
//            } catch (ClassNotFoundException ex) {
//                Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
//            } catch (SQLException ex) {
//                Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
//            }
//        }
//    }
//
//    private void handleLogin(ObjectInputStream ois, ObjectOutputStream writerOj1, ObjectOutputStream writerOj2, ObjectOutputStream writerOj3, Socket socket, JTextArea jTextArea) throws IOException, ClassNotFoundException, SQLException {
//        ModelUser user;
//        if ((user = (ModelUser) ois.readObject()) != null) {
//            ModelMessage login = new UserDAO().login(user);
//
//            if (login.isSuccess()) {
//                int userID = ((ModelUser) login.getData()).getUserID();
//                writerOj1.writeObject(login);
//                List<ModelSendMessage> history = new MesageDAO().getMessage();
//                writerOj2.writeObject(history);
//                List<ModelUser> users = new UserDAO().getUsers();
//                writerOj3.writeObject(users);
//                new Handler(socket, userID, jTextArea);
//            } else {
//                writerOj1.writeObject(login);
//            }
//        }
//
//    }
//
//    private void handleRegister(ObjectInputStream ois, ObjectOutputStream writerOj1, ObjectOutputStream writerOj2, ObjectOutputStream writerOj3, Socket socket, JTextArea jTextArea) throws IOException, SQLException, ClassNotFoundException {
//        ModelUser user;
//        if ((user = (ModelUser) ois.readObject()) != null) {
//            ModelMessage register = new UserDAO().insert(user);
//
//            if (register.isSuccess()) {
//                int userID = ((ModelUser) register.getData()).getUserID();
//
//                writerOj1.writeObject(register);
//                List<ModelSendMessage> history = new MesageDAO().getMessage();
//                writerOj2.writeObject(history);
//                List<ModelUser> users = new UserDAO().getUsers();
//                writerOj3.writeObject(users);
//                new Handler(socket, userID, jTextArea);
//            } else {
//                writerOj1.writeObject(register);
//            }
//        }
//    }
//
//    private static class Handler {
//
//        private final String SEND_RECEIVE = "msg";
//
//        private Socket socket;
//        private ObjectOutputStream writer;
//        ModelUserReceive mr;
//        private int userID;
//        private JTextArea jTextArea1;
//
//        public Handler(Socket socket, int userId, JTextArea jTextArea) throws SQLException {
//            this.socket = socket;
//            this.userID = userId;
//            this.jTextArea1 = jTextArea;
//            run();
//        }
//
//        public void run() throws SQLException {
//            try {
//                int i = 0;
//                ObjectInputStream reader = new ObjectInputStream(socket.getInputStream());
//                writer = new ObjectOutputStream(socket.getOutputStream());
//                mr = new ModelUserReceive(writer, userID);
//                clientWriters.add(mr);
//                
//                ModelSendMessage firstMesage = new ModelSendMessage();
//                firstMesage.setTo(-1);
//                firstMesage.setFrom(userID);
//                firstMesage.setAction(UserAction.LOGIN);
//                firstMesage.setUsers(new UserDAO().getUsers());
//                broadcast(firstMesage);
//
//                
//                ModelSendMessage message;
//                
//                while ((message = (ModelSendMessage) reader.readObject()) != null) {
//                    System.out.println("DAY ner:: " + message.getUser().getUserName());
//                    System.out.println("TxT " + message.getText());
//
//                    if (message.getAction() == UserAction.SEND_RECEIVE) {
////                        ModelUser info = new UserDAO().getUser(message.getUser());
////                        message.setUser(info);
//                        new MesageDAO().insertMessage(message);
//                    } else if(message.getAction() == UserAction.UPDATE_INFO) {
//                        new UserDAO().update(message.getUser());
//                        System.out.println("UserName: " + message.getUser().getUserName());
//                    } else if (message.getAction() == UserAction.DELETE_USER){
//                        new UserDAO().delete(message.getUser());
//                        message.setUsers(new UserDAO().getUsers());
//                        System.out.println(message.getUser().getUserID());
//                        new MesageDAO().delete(message.getUser().getUserID());
//                    }
//                    
//                    broadcast(message);
//                }
//            } catch (IOException ex) {
//                ex.printStackTrace();
//            } catch (ClassNotFoundException ex) {
//                Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
//            } finally {
//                if (writer != null) {
//                    clientWriters.remove(mr);
//                }
//                try {
//                    jTextArea1.append("Client Closed: " + socket.getInetAddress() +"\n");
//                    socket.close();
//                } catch (IOException ex) {
//                    ex.printStackTrace();
//                }
//            }
//        }
//
//        private void broadcast(ModelSendMessage message) throws IOException {
//            
//            if (message.getTo() == -1) {
//                for (ModelUserReceive clientWriter : clientWriters) {
//                    if (clientWriter.getOb() != writer) {
//                        clientWriter.getOb().writeObject(message);
//                    }
//                }
//            } else{
//                for (ModelUserReceive clientWriter : clientWriters) {
//                    if (clientWriter.getUserID() == message.getTo()) {
//                        clientWriter.getOb().writeObject(message);
//                        break;
//                    }
//                }
//            }
//        }
//    }
//
////    public static void main(String[] args) {
////        new Server();
////    }
//}
