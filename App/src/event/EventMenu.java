
package event;

import java.util.List;
import model.ModelUser;

/**
 *
 * @author anhth
 */
public interface EventMenuLeft {
    public void newUser(List<ModelUser> users);
    
    public void userConnect(int userId);
    
    public void userDisconnect(int userId);
}
