package event;

import model.ModelFriend;

public interface EventContent {
    public void selectedUser(ModelFriend user);
    
    public void updateUser(ModelFriend user);
    
    public void changeContent(ModelFriend user);
}
