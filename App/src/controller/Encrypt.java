
package controller;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import javax.imageio.ImageIO;
import event.EventEncrypt;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Base64;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Encrypt implements EventEncrypt{

    @Override
    public Image decodeImage(byte[] image) {
        try {
            ByteArrayInputStream inputStream = new ByteArrayInputStream(image);
            BufferedImage bufferedImage = ImageIO.read(inputStream);
            return bufferedImage;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public String encodeImage(Image image, String extension) {
        try {
            
            
            BufferedImage bufferedImage = new BufferedImage(image.getWidth(null), image.getHeight(null), BufferedImage.TYPE_INT_ARGB);
            bufferedImage.getGraphics().drawImage(image, 0, 0, null);
            ByteArrayOutputStream baos = new ByteArrayOutputStream(10000);
            ImageIO.write(bufferedImage, extension, baos);
            byte[] imageInByte = baos.toByteArray();
            return Base64.getEncoder().encodeToString(imageInByte);
        } catch (IOException ex) {
            Logger.getLogger(Encrypt.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
        
    }
    
}
