package event;

import model.ModelSendMessage;

public interface EventChat {
    public void sendMessage(ModelSendMessage data);
    
    public void ReceiveMessage(ModelSendMessage data);
}
