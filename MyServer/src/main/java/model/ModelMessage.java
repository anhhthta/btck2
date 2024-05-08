
package model;

import java.io.Serializable;

/**
 *
 * @author anhth
 */
public class ModelMessage implements Serializable{
    private boolean success;
    private String message;
    private Object data;

    public ModelMessage() {
    }

    public ModelMessage(boolean success, String msssage) {
        this.success = success;
        this.message = msssage;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
    
    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}
