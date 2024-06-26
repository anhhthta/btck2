/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package event;

import java.awt.Image;

/**
 *
 * @author anhth
 */
public interface EventEncrypt {
    public Image decodeImage(byte[] image);
    
    public String encodeImage(Image image, String extension);
    
    public String decode(String code);
    
    public String encode(String text);
}
