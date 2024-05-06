package swing;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.RenderingHints;
import java.awt.geom.RoundRectangle2D;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JLayeredPane;

public class PictureBox extends JLayeredPane{
    private Icon image;
    private double radius = 0;

    public Icon getImage() {
        return image;
    }

    public void setImage(Icon image) {
        this.image = image;
    } 
    
    @Override
    protected void paintComponent(Graphics g) {
        if (image != null) {
            Graphics2D g2 = (Graphics2D) g;
            Rectangle size = getAutoSize(image);
            g2.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
            if(radius > 0) {
                RoundRectangle2D roundedRectangle = new RoundRectangle2D.Double(size.getLocation().x, 0, size.getSize().width, getHeight(), radius, radius);
                g2.clip(roundedRectangle);
            }
            g2.drawImage(toImage(image), size.getLocation().x, 0, size.getSize().width, getHeight(), null);

        }
        super.paintComponent(g);
    }
    
    private Rectangle getAutoSize(Icon image) {
        int w = getWidth();
        int h = getHeight();
        int iw = image.getIconWidth();
        int ih = image.getIconHeight();
        double xScale = (double) w / iw;
        double yScale = (double) h / ih;
        double scale = Math.min(xScale, yScale);
        int width = (int) (scale * iw);
        int height = (int) (scale * ih);
        int x = (w - width) / 2;
        int y = (h - height) / 2;
        return new Rectangle(new Point(x, y), new Dimension(width, height));
    }
    
    private Image toImage(Icon icon) {
        return ((ImageIcon) icon).getImage();
    }
    
    public void setRadius(double radius) {
        this.radius = radius;
    }
}
