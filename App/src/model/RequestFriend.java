package model;

import java.io.Serializable;

public class RequestFriend implements Serializable{
    private int requester;
    private ModelFriend friend;

    public RequestFriend(int requester, ModelFriend friend) {
        this.requester = requester;
        this.friend = friend;
    }

    public int getRequester() {
        return requester;
    }

    public void setRequester(int requester) {
        this.requester = requester;
    }

    public ModelFriend getFriend() {
        return friend;
    }

    public void setFriend(ModelFriend friend) {
        this.friend = friend;
    }
}
