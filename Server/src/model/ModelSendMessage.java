package model;
import java.io.Serializable;
import java.time.LocalDateTime;


public class ModelSendMessage  implements Serializable{

    private ModelUser user;
    private String text;
    private LocalDateTime time;
    private int to;

    public ModelSendMessage(ModelUser user, String text, LocalDateTime time, int to) {
        this.user = user;
        this.text = text;
        this.time = time;
        this.to = to;
    }

    public ModelSendMessage(ModelUser user, String text, LocalDateTime time) {
        this.user = user;
        this.text = text;
        this.time = time;
    }
    
    public ModelSendMessage(ModelUser user, String text) {
        this.user = user;
        this.text = text;
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
    
    
}
