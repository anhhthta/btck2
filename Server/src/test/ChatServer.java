//package test;
//
//import java.io.*;
//import java.net.*;
//import java.util.logging.Level;
//import java.util.logging.Logger;
//
//public class Client {
//
//    private static final String SERVER_IP = "127.0.0.1";
//    private static final int SERVER_PORT = 12345;
//
//    public static void main(String[] args) {
//        new Client().LoginHandler();
//    }
//    
//    public Client() {
//        
//    }
//
//    private void LoginHandler() {
//        new Thread(() -> {
//            try {
//                Socket socket = new Socket("localhost", 12345);
//                ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
//
//                // Tạo một đối tượng để gửi
//                user objectToSend = new user(1, "anh");
//
//                // Gửi đối tượng đến client
//                oos.writeObject(objectToSend);
//
//                oos.close();
//                socket.close();
//            } catch (IOException ex) {
//                Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
//            }
//        }).start();
//    }
//}
//
////package test;
////
////import com.mysql.cj.callback.UsernameCallback;
////import java.io.*;
////import java.net.*;
////
////public class Client {
////    private static final String SERVER_IP = "127.0.0.1";
////    private static final int SERVER_PORT = 12345;
////    private String Username = "anh";
////
////    public static void main(String[] args) {
////        try (Socket socket = new Socket(SERVER_IP, SERVER_PORT);
////             BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
////             PrintWriter writer = new PrintWriter(socket.getOutputStream(), true);
////             BufferedReader consoleReader = new BufferedReader(new InputStreamReader(System.in))) {
////
////            System.out.println("Connected to server.");
////
////            new Thread(() -> {
////                String message;
////                try {
////                    while ((message = reader.readLine()) != null) {
////                        System.out.println("Received: " + message);
////                    }
////                } catch (IOException e) {
////                    e.printStackTrace();
////                }
////            }).start();
////
////            String userInput;
////            while ((userInput = consoleReader.readLine()) != null) {
////                writer.println(userInput);
////            }
////        } catch (IOException e) {
////            e.printStackTrace();
////        }
////    }
////}
