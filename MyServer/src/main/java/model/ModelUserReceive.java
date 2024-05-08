package model;

import java.io.ObjectOutputStream;

/**
 *
 * @author anhth
 */
public class ModelUserReceive {
    private ObjectOutputStream ob;
    private int userID;

    public ModelUserReceive(ObjectOutputStream ob, int userID) {
        this.ob = ob;
        this.userID = userID;
    }

    public ObjectOutputStream getOb() {
        return ob;
    }

    public void setOb(ObjectOutputStream ob) {
        this.ob = ob;
    }

    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }
}
