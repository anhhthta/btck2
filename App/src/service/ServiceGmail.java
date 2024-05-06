
package service;

import java.util.Properties;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import model.ModelMessage;

/**
 *
 * @author anhth
 */
public class ServiceGmail {
    static final String from = "anhht23ite@gmail.com";
    static final String pass = "sbptvzjlduwumevr";
    
    public ModelMessage send(String toEmail, String code, String title){
        ModelMessage ms = new ModelMessage(false, "");
        
        Properties props = new Properties();
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        
        Session session = Session.getInstance(props, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(from, pass);
            }
        });
        
        Message msg = new MimeMessage(session);
        try {
            msg.addHeader("Content-type","text/HTML; charset=UTF-8");
            msg.setFrom(new InternetAddress(from));
            msg.setRecipients(Message.RecipientType.TO,InternetAddress.parse(toEmail,false));
            msg.setSubject(title);
            msg.setContent(code,"text/HTML; charset=UTF-8");

            Transport.send(msg);
            ms.setSuccess(true);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            if(e.getMessage().equals("Invalid Addresses")){
                ms.setMessage("Invalid email");
            } else {
                ms.setMessage("Error");
            }
        }
        return ms;
    }
}
