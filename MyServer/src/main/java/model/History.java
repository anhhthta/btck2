package model;

import java.io.Serializable;
import java.util.List;

public class History implements Serializable{
    private List<ModelSendMessage> history;

    public History(List<ModelSendMessage> history) {
        this.history = history;
    }

    public List<ModelSendMessage> getHistory() {
        return history;
    }

    public void setHistory(List<ModelSendMessage> history) {
        this.history = history;
    }
    
    
}
