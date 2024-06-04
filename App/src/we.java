
import controller.Encrypt;
import controller.FIlesHandle;
import event.PublicEvent;
import java.io.UnsupportedEncodingException;
import java.util.Base64;
import java.util.logging.Level;
import java.util.logging.Logger;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author anhth
 */
public class we {
    public static void main(String[] args) {
        PublicEvent.getInstance().addFileEvent(new FIlesHandle());
        try {
            PublicEvent.getInstance().addEventEncrypt(new Encrypt());

            String tim = "anh@gmail.com@BYT$-anh";
//            
            String encode = Base64.getEncoder().encodeToString(tim.getBytes("UTF-8"));
            PublicEvent.getInstance().getFileEvent().writeFile("README.txt", encode);
//            
//            System.out.println(encode);

            String i = PublicEvent.getInstance().getFileEvent().readFiles("README.txt");
            if(!i.equals("")){
                byte[] s = Base64.getDecoder().decode(i);
                String a = new String(s, "UTF-8");
                
                System.out.println(a);
                String data[] = a.split("@BYT\\$-");
                System.out.println(data.length);
                System.out.println(data[0] + " "+ data[1]);
            }
            
        } catch (UnsupportedEncodingException ex) {
            Logger.getLogger(we.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
