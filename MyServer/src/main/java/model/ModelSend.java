package model;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;
import utilites.TypeMessage;
import utilites.UserAction;

@Entity
@Table(name = "message")
public class ModelSend  implements Serializable{
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "fromUser")
    private int fromId;
    @Transient
    private ModelUser user;
    private String text;
    private LocalDateTime time;
    @Column(name = "toUser")
    private int toId;
    @Transient
    private UserAction action;
    private TypeMessage TypeMessage;
    @Transient
    private List<RequestFriend> requests;
    @Transient
    private List<ModelFriend> friends;


    public ModelSend(ModelUser user, String text, LocalDateTime time, int to,TypeMessage typeMessage, UserAction action) {
        this.user = user;
        this.text = text;
        this.time = time;
        this.toId = to;
        this.TypeMessage = typeMessage;
        this.action = action;
    }

    public ModelSend(ModelUser user, UserAction action) {
        this.user = user;
        this.action = action;
    }

    public ModelSend(List<RequestFriend> requests, List<ModelFriend> friends) {
        this.requests = requests;
        this.friends = friends;
    }

    public ModelSend() {
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
    
    public TypeMessage getTypeMessage() {
        return TypeMessage;
    }

    public void setTypeMessage(TypeMessage TypeMessage) {
        this.TypeMessage = TypeMessage;
    }
}
