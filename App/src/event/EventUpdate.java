
package event;

import model.ModelFriend;
import model.RequestFriend;

/**
 *
 * @author anhth
 */
public interface EventUpdate {
    public void updateHeader();
    public void setMenu();
    public void updateMenuItem(ModelFriend nf);
    public void addMenuItem(ModelFriend nf);
    public void removeMenuItem(int id);
    public void clearMenu();
    public void setMenuAll();
    public void updateMenuAllItem(RequestFriend rq);
    public void addMenuAllItem(RequestFriend rq);
    public void removeMenuAllItem(int id);
    public void clearMenuAll();
}
