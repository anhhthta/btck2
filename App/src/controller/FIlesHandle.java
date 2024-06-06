package controller;

import event.FileEvent;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author anhth
 */
public class FIlesHandle implements FileEvent{

    @Override
    public String readFiles(String fileName) {
        String data = "";
        try {
            File file = new File(fileName);
            
            if(!file.exists()) {
                file.createNewFile();
            }
            
            FileInputStream fileInputStream = new FileInputStream(fileName);
            int d;
            while((d = fileInputStream.read()) != -1) {
                data += (char) d;
            }
            
            fileInputStream.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        return data;
    }

    @Override
    public void writeFile(String fileName, String data) {
        try {
            File file = new File(fileName);
            
            if(!file.exists()) {
                file.createNewFile();
            }
            
            
            FileOutputStream fileOutputStream = fileOutputStream = new FileOutputStream(fileName);
            
            fileOutputStream.write(data.getBytes());
            
            fileOutputStream.flush();
            fileOutputStream.close();
            
        } catch (FileNotFoundException ex) {
            Logger.getLogger(FIlesHandle.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(FIlesHandle.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}