
import controller.Encrypt;
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
        try {
            PublicEvent.getInstance().addEventEncrypt(new Encrypt());

            String tim = "anh";

            String encode = Base64.getEncoder().encodeToString(tim.getBytes("UTF-8"));
            System.out.println(encode);

            byte[] s = Base64.getDecoder().decode("bmdhbm5lMQ==");
            System.out.println(new String(s, "UTF-8"));

        } catch (UnsupportedEncodingException ex) {
            Logger.getLogger(we.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
