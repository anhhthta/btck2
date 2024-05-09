package event;

import model.ModelSendMessage;

/**
 *
 * @author anhth
 */
public interface EventToServer {
    public void send(ModelSendMessage data);
    
    public void receive();
}
