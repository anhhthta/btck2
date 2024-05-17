package service;

import java.io.*;
import java.net.*;
import javax.swing.JTextArea;

public class Server {

    private static final int PORT = 9999;
    
    private Socket socket;

    private JTextArea jTextArea;
    public Server(JTextArea jTextArea) {
        this.jTextArea = jTextArea;
        try (ServerSocket serverSocket = new ServerSocket(PORT)) {
            jTextArea.append("Server is running... (" + PORT +")\n");

            while (true) {
                socket = serverSocket.accept();
                System.out.println();
                jTextArea.append("Client connected: " + socket.getInetAddress() +"\n");
                Thread thread = new Thread(new ClientHandler(socket, jTextArea));
                thread.start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Socket getSocket() {
        return this.socket;
    }
   
}
