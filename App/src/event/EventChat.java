package event;

import model.ModelSendMessage;

public interface EventChat {
    public void sendMessage(ModelSendMessage text);
}
