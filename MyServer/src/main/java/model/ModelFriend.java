package model;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name = "friends")
public class ModelFriend implements Serializable{
    @Id
    @Column(name = "id")
    private int userID;
    @Id
    private int friendId;
    @Transient
    private String friendName;
    @Transient
    private String friendImage;
    private String status;

    public ModelFriend(int userID, int friendId, String friendName, String friendImage, String status) {
        this.userID = userID;
        this.friendId = friendId;
        this.friendName = friendName;
        this.friendImage = friendImage;
        this.status = status;
    }

    public ModelFriend(int userID, int friendId, String status) {
        this.userID = userID;
        this.friendId = friendId;
        this.status = status;
    }

    public int getID() {
        return userID;
    }

    public void setID(int userID) {
        this.userID = userID;
    }

    public int getFriendId() {
        return friendId;
    }

    public void setFriendId(int friendId) {
        this.friendId = friendId;
    }

    public String getFriendName() {
        return friendName;
    }

    public void setFriendName(String friendName) {
        this.friendName = friendName;
    }

    public String getFriendImage() {
        return friendImage;
    }

    public void setFriendImage(String friendImage) {
        this.friendImage = friendImage;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
    
    
}
