package model;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;
import utilites.UserAction;


public class ModelSendMessage  implements Serializable{

    private int id;
    private int fromId;
    private ModelUser user;
    private String text;
    private LocalDateTime time;
    private int toId;
    private UserAction action;
    private List<RequestFriend> requests;
    private List<ModelFriend> friends;

    public ModelSendMessage(ModelUser user, String text, LocalDateTime time, int to,UserAction action) {
        this.user = user;
        this.text = text;
        this.time = time;
        this.toId = to;
        this.action = action;
    }

    public ModelSendMessage(ModelUser user, UserAction action) {
        this.user = user;
        this.action = action;
    }
    
    public ModelSendMessage(List<RequestFriend> requests, List<ModelFriend> friends) {
        this.requests = requests;
        this.friends = friends;
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
        return toId;
    }

    public void setTo(int to) {
        this.toId = to;
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
        return fromId;
    }

    public void setFrom(int from) {
        this.fromId = from;
    }

    public List<RequestFriend> getRequests() {
        return requests;
    }

    public void setRequests(List<RequestFriend> requests) {
        this.requests = requests;
    }

    public List<ModelFriend> getFriends() {
        return friends;
    }

    public void setFriends(List<ModelFriend> friends) {
        this.friends = friends;
    }
}
