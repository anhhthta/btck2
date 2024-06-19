package event;

import model.ModelSend;

public interface EventChat {
    public void sendMessage(ModelSend data);
    public void sendEMOJI(String i);
    
    public void ReceiveMessage(ModelSend data);
}
