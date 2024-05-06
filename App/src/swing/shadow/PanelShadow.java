package swing.shadow;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import javax.swing.JPanel;

public class PanelShadow extends JPanel{
    private ShadowType shadowType = ShadowType.BOT;
    private int shadowSize = 3;
    private float shadowOpacity = 0.3f;
    private Color shadowColor = Color.BLACK;
    private Color bgColor = Color.WHITE;
    private int radius = 0;
    
    public Color getBgColor() {
        return bgColor;
    }

    public void setBgColor(Color bgColor) {
        this.bgColor = bgColor;
    }
    
    public int getRadius() {
        return radius;
    }

    public void setRadius(int radius) {
        this.radius = radius;
    }
    
    public PanelShadow() {
        setOpaque(false);
    }

    public ShadowType getShadowType() {
        return shadowType;
    }

    public void setShadowType(ShadowType shadowType) {
        this.shadowType = shadowType;
    }

    public int getShadowSize() {
        return shadowSize;
    }

    public void setShadowSize(int shadowSize) {
        this.shadowSize = shadowSize;
    }

    public float getShadowOpacity() {
        return shadowOpacity;
    }

    public void setShadowOpacity(float shadowOpacity) {
        this.shadowOpacity = shadowOpacity;
    }

    public Color getShadowColor() {
        return shadowColor;
    }

    public void setShadowColor(Color shadowColor) {
        this.shadowColor = shadowColor;
    }
    
    @Override
    protected void paintComponent(Graphics g) {
        createShadow(g);
        super.paintComponent(g);
    }
    
    private void createShadow(Graphics g){
        Graphics2D g2 = (Graphics2D) g;
        int size = shadowSize*2;
        int x = 0;
        int y = 0;
        int width = getWidth() - size;
        int height = getHeight()- size;
        if(shadowType == ShadowType.TOP) {
            x = shadowSize;
            y = size;
        } else if(shadowType == ShadowType.BOT) {
            x = shadowSize;
            y = 0;
        } else if(shadowType == ShadowType.TOP_LEFT) {
            x = size;
            y = size;
        } else if(shadowType == ShadowType.TOP_RIGHT) {
            x = 0;
            y =size;
        } else if(shadowType == ShadowType.BOT_LEFT) {
            x = size;
            y = 0;            
        } else if(shadowType == ShadowType.BOT_RIGHT) {
            x = 0;
            y = 0;
        } else {
            x = shadowSize;
            y = shadowSize;
        }
        BufferedImage img = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2d = img.createGraphics();
        g2d.setBackground(getBackground());
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2d.setColor(bgColor);
        g2d.fillRoundRect(0, 0, width, height, radius-1, radius-1);
       
        ShadowRenderer render = new ShadowRenderer(shadowSize, shadowOpacity, shadowColor);
        g2.drawImage(render.createShadow(img), 0, 0, null);
        g2.drawImage(img, x, y, null);
    }

    

    
}
