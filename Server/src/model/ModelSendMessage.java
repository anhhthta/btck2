package model;
import java.io.Serializable;
import java.time.LocalDateTime;
import utilites.UserAction;


public class ModelSendMessage  implements Serializable{

    private int id;
    private int from;
    private ModelUser user;
    private String text;
    private LocalDateTime time;
    private int to;
    private UserAction action;

    public ModelSendMessage(ModelUser user, String text, LocalDateTime time, int to,UserAction action) {
        this.user = user;
        this.text = text;
        this.time = time;
        this.to = to;
        this.action = action;
    }

    public ModelSendMessage(ModelUser user, UserAction action) {
        this.user = user;
        this.action = action;
    }

    public ModelSendMessage() {
    }

    public ModelUser getUser() {
        return user;
    }

    public void setUser(ModelUser user) {
        this.user = user;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public LocalDateTime getTime() {
        return time;
    }

    public void setTime(LocalDateTime time) {
        this.time = time;
    }

    public int getTo() {
        return to;
    }

    public void setTo(int to) {
        this.to = to;
    }

    public UserAction getAction() {
        return action;
    }

    public void setAction(UserAction action) {
        this.action = action;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getFrom() {
        return from;
    }

    public void setFrom(int from) {
        this.from = from;
    }
}
