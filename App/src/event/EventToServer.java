package event;

import model.ModelSend;

/**
 *
 * @author anhth
 */
public interface EventToServer {
    public void send(ModelSend data);
    
    public void receive();
}
