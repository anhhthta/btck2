
package swing;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Insets;
import java.awt.RenderingHints;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JTextField;
import javax.swing.Timer;

/**
 *
 * @author anhth
 */

public class TextField extends JTextField {
    private Icon prefixIcon;
    private Icon suffixDisabledIcon;
    private Icon suffixIcon;
    private String hint = "";
    private String suffixButton = "";
    private boolean isLoading = false;
    private boolean isHover = false;
    private double angle = 0;
    private final int sizeBtn = 50;
    private final Timer timer;
    private boolean border = false;
    private int radius = 5;
    private Color borderColor = new Color(255,0,0);

    public String getHint() {
        return hint;
    }

    public void setHint(String hint) {
        this.hint = hint;
    }

    public Icon getPrefixIcon() {
        return prefixIcon;
    }

    public void setPrefixIcon(Icon prefixIcon) {
        this.prefixIcon = prefixIcon;
        initBorder();
    }

    public Icon getSuffixDisabledIcon() {
        return suffixDisabledIcon;
    }

    public void setSuffixDisabledIcon(Icon suffixDisabledIcon) {
        this.suffixDisabledIcon = suffixDisabledIcon;
        initBorder();
    }
    
    public Icon getSufixIcon() {
        return suffixIcon;
    }

    public void setSuffixIcon(Icon suffixIcon) {
        this.suffixIcon = suffixIcon;
        initBorder();
    }

    public String getSuffixButton() {
        return suffixButton;
    }

    public void setSuffixButton(String suffixButton) {
        this.suffixButton = suffixButton;
        initBorder();
        hoverBtn();
    }

    public TextField() {
        setBorder(javax.swing.BorderFactory.createEmptyBorder(10, 10, 10, 10));
        setBackground(new Color(0, 0, 0, 0));
        setForeground(Color.decode("#7A8C8D"));
        setFont(new java.awt.Font("sansserif", 0, 13));
        setSelectionColor(new Color(75, 175, 152));
        
        timer = new Timer(50, (ActionEvent e) -> {
            angle += Math.PI/5;
            repaint();
        });
    }

    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D) g;
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2.setColor(new Color(230, 245, 241));
        g2.fillRoundRect(0, 0, getWidth(), getHeight(), radius, radius);
        paintIcon(g);
        paintSuffixText(g2);
        
        if(border){
            g2.setColor(borderColor);
            g2.drawRoundRect(0, 0, getWidth() - 1, getHeight() - 1, radius , radius);
        }
        super.paintComponent(g);
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        if (getText().length() == 0) {
            int h = getHeight();
            ((Graphics2D) g).setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
            Insets ins = getInsets();
            FontMetrics fm = g.getFontMetrics();
            g.setColor(new Color(200, 200, 200));
            g.drawString(hint, ins.left, h / 2 + fm.getAscent() / 2 - 2);
        }
    }

    private void paintIcon(Graphics g) {
        Graphics2D g2 = (Graphics2D) g;
        if (prefixIcon != null) {
            Image prefix = ((ImageIcon) prefixIcon).getImage();
            int y = (getHeight() - prefixIcon.getIconHeight()) / 2;
            g2.drawImage(prefix, 10, y, this);
        }
        
        if (suffixDisabledIcon != null) {
            if(suffixButton.isEmpty()){
                Image suffix = ((ImageIcon) suffixDisabledIcon).getImage();
                int y = (getHeight() - suffixDisabledIcon.getIconHeight()) / 2;
                g2.drawImage(suffix, getWidth() - suffixDisabledIcon.getIconWidth() - 10, y, this);
            } else if(isLoading){
                FontMetrics fm = g2.getFontMetrics();
                int textWidth = fm.stringWidth(suffixButton);
                int xSeparator;
                if(textWidth >= sizeBtn - 20){
                    xSeparator = getWidth() - textWidth - 20;
                } else {
                    xSeparator = getWidth() - sizeBtn;
                }
                int ySeparator1 = 0;
                int ySeparator2 = getHeight();
                int ximg = getWidth() - textWidth/2 - 10 - suffixDisabledIcon.getIconWidth()/2;
                int yimg = (getHeight() - suffixDisabledIcon.getIconHeight()) / 2;
                
                g2.setColor(new Color(0, 0, 0, 0.1f));
                g2.fillRect(xSeparator, 0, fm.stringWidth(suffixButton) + 20, getHeight());
                
                g2.setColor(Color.GRAY);
                g2.drawLine(xSeparator, ySeparator1, xSeparator, ySeparator2);
                
                int centerX = ximg + suffixDisabledIcon.getIconWidth() / 2;
                int centerY = yimg + suffixDisabledIcon.getIconHeight() / 2;

                g2.rotate(angle, centerX, centerY); 
                
                Image suffix = ((ImageIcon) suffixDisabledIcon).getImage();
                g2.drawImage(suffix, ximg, yimg, this);

                repaint();
                g2.rotate(-angle, centerX, centerY);

            }  
        } else if(suffixIcon != null){
            Image suffix = ((ImageIcon) suffixIcon).getImage();
            int y = (getHeight() - suffixIcon.getIconHeight()) / 2;
            g2.drawImage(suffix, getWidth() - suffixIcon.getIconWidth() - 10, y, this);
        }
    }  
    
    private void paintSuffixText(Graphics2D g2){
        if(!suffixButton.isEmpty() && !isLoading){
            FontMetrics fm = g2.getFontMetrics();
            int textWidth = fm.stringWidth(suffixButton);
            int x = getWidth() - textWidth - 10;
            int y = (getHeight() + fm.getAscent()) / 2 - 2;
            
            int xSeparator;
            if(textWidth >= sizeBtn - 20){
                xSeparator = getWidth() - textWidth - 20;
            } else {
                xSeparator = getWidth() - sizeBtn;
            }
            int ySeparator1 = 0;
            int ySeparator2 = getHeight();
            
            int fill = getWidth() - xSeparator;
                        
            if(isHover){
                g2.setColor(new Color(0, 0, 0, 0.3f));
                g2.fillRect(xSeparator, 0, fill, getHeight());
                repaint();
            } else {
                g2.setColor(new Color(0, 0, 0, 0.1f));
                g2.fillRect(xSeparator, 0, fill, getHeight());
                repaint();
            }
            
            g2.setColor(Color.BLACK);
            g2.drawString(suffixButton, x, y);
            
            g2.setColor(Color.GRAY);
            g2.drawLine(xSeparator, ySeparator1, xSeparator, ySeparator2);
            repaint();
        }
    }

    private void initBorder() {
        int left = 15;
        int right = 15;
        //  5 is default
        if (prefixIcon != null) {
            //  prefix icon is left
            left = prefixIcon.getIconWidth() + 15;
        }
        if (!suffixButton.isEmpty()) {
            //  suffix button is right
            Graphics2D g2 = (Graphics2D) new BufferedImage(1, 1, BufferedImage.TYPE_INT_ARGB).getGraphics();
            FontMetrics fm = g2.getFontMetrics();
            int textWidth = fm.stringWidth(suffixButton);
            
            if(textWidth >= sizeBtn - 20){
                right = textWidth + 25;
            } else {
                right = sizeBtn + 5; 
            }

            g2.dispose();
        } else if (suffixDisabledIcon != null){
            //  suffix icon is right
            right = suffixDisabledIcon.getIconWidth() + 15;
        } else if(suffixIcon != null) {
            right = suffixIcon.getIconWidth() + 15;
        }
       
        setBorder(javax.swing.BorderFactory.createEmptyBorder(10, left, 10, right));
    }
       
    public boolean isLoading() {
        return this.isLoading;
    }
    
    public void setLoading(boolean l){
        if(isLoading && timer.isRunning()){
            timer.stop();
        }
        this.isLoading = l;
    }
     
    public void Loading(MouseListener event) {
        if(suffixDisabledIcon != null){

            addMouseListener(new MouseAdapter() {
                @Override
                public void mouseReleased(MouseEvent e) {
                    int x = e.getX();
                    Graphics2D g2 = (Graphics2D) new BufferedImage(1, 1, BufferedImage.TYPE_INT_ARGB).getGraphics();
                    FontMetrics fm = g2.getFontMetrics();
                    int textWidth = fm.stringWidth(suffixButton);
                    int xbtn;
                    if(textWidth >= sizeBtn - 20){
                        xbtn = getWidth() - textWidth - 20;
                    } else {
                        xbtn = getWidth() - sizeBtn;
                    }
                    
                    g2.dispose();
                    if(x >= xbtn && x <= getWidth() && !isLoading){
                        if(!timer.isRunning()){
                            timer.start();
                            isLoading = !isLoading;
                            if(event != null){
                                event.mouseClicked(e);
                            }
                        } else {
                            timer.stop();
                        }
                    }
                }
            });
        }
    }

    public void addEvent(MouseListener event) {
        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseReleased(MouseEvent e) {
                int x = e.getX();
                Graphics2D g2 = (Graphics2D) new BufferedImage(1, 1, BufferedImage.TYPE_INT_ARGB).getGraphics();
                FontMetrics fm = g2.getFontMetrics();
                int textWidth = fm.stringWidth(suffixButton);
                int xbtn;
                if(textWidth >= sizeBtn - 20){
                    xbtn = getWidth() - textWidth - 20;
                } else {
                    xbtn = getWidth() - sizeBtn;
                }
                
                g2.dispose();
                if(x >= xbtn && x <= getWidth()){
                    event.mouseClicked(e);
                }
            }
        });
    }

    
    private void hoverBtn () {
        if(!suffixButton.isEmpty()){
            addMouseMotionListener(new MouseAdapter() {
                @Override
                public void mouseMoved(MouseEvent e) {
                    int x = e.getX();
                    Graphics2D g2 = (Graphics2D) new BufferedImage(1, 1, BufferedImage.TYPE_INT_ARGB).getGraphics();
                    FontMetrics fm = g2.getFontMetrics();
                    int textWidth = fm.stringWidth(suffixButton);
                    int xbtn;
                    if(textWidth >= sizeBtn - 20){
                        xbtn = getWidth() - textWidth - 20;
                    } else {
                        xbtn = getWidth() - sizeBtn;
                    }
                    
                    if(x >= xbtn && x < getWidth()){
                        setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
                        isHover = true;
                    }
                    else {
                        setCursor(Cursor.getPredefinedCursor(Cursor.TEXT_CURSOR));
                        isHover = false;
                    }
                }
            });
        
            addMouseListener(new MouseAdapter() {
                @Override
                public void mouseExited(MouseEvent e) {
                    setCursor(Cursor.getPredefinedCursor(Cursor.TEXT_CURSOR));
                    isHover = false;
                }
            });
        }
    }

    public void setBorder(boolean border) {
        this.border = border;
    }

    public void setBorderColor(Color borderColor) {
        this.borderColor = borderColor;
    }

    public void setRadius(int radius) {
        this.radius = radius;
    }
}
