package event;

import view.components.Notification;

/**
 *
 * @author anhth
 */
public interface EventNotificate {
    public void showMessage(Notification.MessageType messageType, String message);
}
