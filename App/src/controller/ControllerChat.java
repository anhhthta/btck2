package controller;

import event.EventChat;
import event.PublicEvent;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDateTime;
import java.util.List;
import model.ModelSendMessage;
import service.Client;
import swing.JIMSendTextPane;
import utilites.UserAction;
import view.components.chatArea.Body;
import view.components.chatArea.Bottom;
import view.components.chatArea.Header;

public class ControllerChat implements ActionListener, EventChat {

    private Header header;
    private Body body;
    private Bottom bottom;

    public ControllerChat(Header header, Body body, Bottom bottom) {
        this.header = header;
        this.body = body;
        this.bottom = bottom;
    }

    public void setHistory() {
        if (header.getUser().getUserID() == -1) {
            List<ModelSendMessage> list = Client.getInstance().getHistory();
            int id = Client.getInstance().getUser().getUserID();
            for (ModelSendMessage l : list) {
                if (l.getTo() == -1) {
                    if (l.getUser().getUserID() == id) {
                        body.addRightItem(l);
                    } else {
                        body.addGroupItem(l);
                    }
                }

            }
        } else {
            List<ModelSendMessage> list = Client.getInstance().getHistory();
            int id = Client.getInstance().getUser().getUserID();

            for (ModelSendMessage l : list) {
                if ((header.getUser().getUserID() == l.getTo() && l.getUser().getUserID() == id)
                        || (id == l.getTo() && header.getUser().getUserID() == l.getUser().getUserID())) {

                    if (l.getUser().getUserID() == id) {
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
        if (command.equals("send")) {
            sendMessage(null);
        }
    }

    @Override
    public void sendMessage(ModelSendMessage ata) {
        JIMSendTextPane boxtxt = bottom.getTextBox();
        String text = boxtxt.getText();
        if (!text.trim().equals("")) {
            ModelSendMessage data = new ModelSendMessage(Client.getInstance().getUser(), text, LocalDateTime.now(), header.getUser().getUserID(), UserAction.SEND_RECEIVE);
            PublicEvent.getInstance().getEventToServer().send(data);
            Client.getInstance().getHistory().add(data);
            PublicEvent.getInstance().getEventLastTime().setLastTime(data);
            body.addRightItem(data);
            boxtxt.setText("");
            boxtxt.grabFocus();
        } else {
            boxtxt.grabFocus();
        }

    }

    @Override
    public void ReceiveMessage(ModelSendMessage data) {
        if (header.getUser() != null) {
            if (header.getUser().getUserID() == -1) {
                if (data.getTo() == -1) {
                    body.addGroupItem(data);
                    System.out.println("r1");
                }
            } else {
                if (data.getUser().getUserID() == header.getUser().getUserID() && data.getTo() != -1) {
                    System.out.println("r2");
                    body.addGroupItem(data);
                }
            }
        }

    }
}
