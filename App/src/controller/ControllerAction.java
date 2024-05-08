package controller;

import event.EventChat;
import event.PublicEvent;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.time.LocalDateTime;
import java.util.List;

import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JPanel;
import model.ModelSendMessage;
import swing.JIMSendTextPane;
import service.Client;
import view.components.chatArea.Body;
import view.components.chatArea.Bottom;
import view.components.chatArea.Header;
import view.components.dialog.ImageDialog;

public class ControllerChat implements ActionListener, EventChat{
    private JPanel boxImage;
    private Icon image;
    
    private Header header;
    private Body body;
    private Bottom bottom;
    
    private Socket socket;
    private ObjectInputStream readerOj;
    private ObjectOutputStream writerOj;
    
    public ControllerChat(JPanel boxImage, Icon image) {
        this.boxImage = boxImage;
        this.image = image;
        socket = Client.getInstance().getSocket();
        try {
            readerOj = new ObjectInputStream(socket.getInputStream());
        } catch (IOException ex) {
            Logger.getLogger(ControllerChat.class.getName()).log(Level.SEVERE, null, ex);
        }
        writerOj = Client.getInstance().getWriterOj();

        
        boxImage.addMouseListener(new MouseAdapter(){
            @Override
            public void mouseClicked(MouseEvent e) {
                showImage();
            }
        });
    }
        
    private void showImage() {
        ImageDialog dialog = new ImageDialog(null, image);
        dialog.showDialog();
    }
    
    public ControllerChat(Header header, Body body, Bottom bottom) {
        this.header = header;
        this.body = body;
        this.bottom = bottom;
               
        try {
            if((this.socket = Client.getInstance().getSocket()) != null) {
                this.socket = Client.getInstance().getSocket();
                this.writerOj = new ObjectOutputStream(socket.getOutputStream());
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        receiveMessag();
                    }
                }).start();
            }
            
            
        } catch (IOException ex) {
            Logger.getLogger(ControllerLogin.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void initHistory() {
//        body
        if(header.getUser().getUserID()== -1){
            List<ModelSendMessage> list = Client.getInstance().getHistory();
            int id = Client.getInstance().getUser().getUserID();
            for(ModelSendMessage l : list) {
                if(l.getTo() == -1) {
                    if(l.getUser().getUserID() == id) {
                        body.addRightItem(l);
                    } else {
                        body.addGroupItem(l);
                    }
                }
                
            }
        } else {
            List<ModelSendMessage> list = Client.getInstance().getHistory();
            int id = Client.getInstance().getUser().getUserID();

            for(ModelSendMessage l : list) {
                if((header.getUser().getUserID() == l.getTo() && l.getUser().getUserID() == id) ||
                        (id == l.getTo() && header.getUser().getUserID() == l.getUser().getUserID())) {
                    
                    if(l.getUser().getUserID() == id) {
                        body.addRightItem(l);
                    } else {
                        body.addGroupItem(l);
                    }
                }
            }
        }
        
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        String command = e.getActionCommand();
        if(command.equals("send")) {
            sendMessag();
        }
    }

    private void sendMessag() {
        JIMSendTextPane boxtxt = bottom.getTextBox();
        String text = boxtxt.getText();
        if(!text.trim().equals("")) {
                ModelSendMessage message = new ModelSendMessage(Client.getInstance().getUser(), text, LocalDateTime.now(), header.getUser().getUserID());
                send(message);
                Client.getInstance().getHistory().add(message);
                PublicEvent.getInstance().getEventChat().sendMessage(message);
                boxtxt.setText("");
                boxtxt.grabFocus();
        } else {
            boxtxt.grabFocus();
        }
    }
    
    private void send(ModelSendMessage data){
        try {
            writerOj.writeObject(data);
        } catch (Exception e) {
        }
        
    }
    
    public void receiveMessag(){
        ModelSendMessage message;
        try {
            if(readerOj == null) {
                readerOj = new ObjectInputStream(socket.getInputStream());
            }
            while ((message = (ModelSendMessage) readerOj.readObject()) != null) {
                if(header.getUser().getUserID() == -1) {
                    if(message.getTo() == -1) {
                        body.addGroupItem(message);
                        System.out.println("r1");
                    }
                } else {
                    if(message.getUser().getUserID() == header.getUser().getUserID() && message.getTo() !=-1) {
                        System.out.println("r2");

                        body.addGroupItem(message);
                    }
                }
                
                Client.getInstance().getHistory().add(message);
            }
        } catch (IOException ex) {
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ControllerChat.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void sendMessage(ModelSendMessage text) {
        body.addRightItem(text);
    }

}
