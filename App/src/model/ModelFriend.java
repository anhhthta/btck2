package model;


import java.awt.Image;
import java.io.Serializable;

public class ModelFriend implements Serializable{
    private int userID;
    private int friendId;
    private String friendName;
    private String friendImage;
    private Image friendImg;
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

    public String getFriendImageString() {
        return friendImage;
    }

    public void setFriendImageString(String friendImage) {
        this.friendImage = friendImage;
    }

    public Image getFriendImage() {
        return friendImg;
    }

    public void setFriendImage(Image friendImg) {
        this.friendImg = friendImg;
    }
    
    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
    
    
}
