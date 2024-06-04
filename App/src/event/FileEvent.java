package event;

/**
 *
 * @author anhth
 */
public interface FileEvent {
    public String readFiles(String fileName);
    
    public void writeFile(String fileName, String data);
}
