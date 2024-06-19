package model;

import java.io.Serializable;
import java.util.List;

public class History implements Serializable{
    private List<ModelSend> history;

    public History(List<ModelSend> history) {
        this.history = history;
    }

    public List<ModelSend> getHistory() {
        return history;
    }

    public void setHistory(List<ModelSend> history) {
        this.history = history;
    }
    
    
}
