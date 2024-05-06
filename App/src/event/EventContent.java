package event;

import model.ModelUser;

public interface EventContent {
    public void selectedUser(ModelUser user);
    
    public void updateUser(ModelUser user);
}
