package view.components.chatArea;

import controller.ControllerChat;
import java.awt.Dimension;
import java.awt.event.ActionListener;
import javax.swing.Icon;
import net.miginfocom.swing.MigLayout;
import swing.PictureBox;

public class ImageItem extends javax.swing.JPanel {
    private ActionListener event;
    
    public ImageItem() {
        initComponents();
        setLayout(new MigLayout("fillx, filly", "0[]0", "0[]0"));
        setOpaque(false);
    }

    public void addImage(Icon img) {
        PictureBox pic = new PictureBox();
        pic.setRadius(10.45);
        pic.setPreferredSize(getAutoSize(img, 250, 250));
        
        pic.setImage(img);
        add(pic, "wrap");
        
    }
    
    private Dimension getAutoSize(Icon image, int w, int h) {
        event = new ControllerChat(this, image);
        
        int iw = image.getIconWidth();
        int ih = image.getIconHeight();
        double xScale = (double) w / iw;
        double yScale = (double) h / ih;
        double scale = Math.min(xScale, yScale);
        int width = (int) (scale * iw);
        int height = (int) (scale * ih);
        int x = (w - width) / 2;
        int y = (h - height) / 2;
        return new Dimension(width, height);
    }
    
    public void addEvent(ActionListener al) {
        
    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        setBackground(new java.awt.Color(255, 255, 204));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
