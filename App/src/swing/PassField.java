
package swing;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Insets;
import java.awt.RenderingHints;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JPasswordField;


public class PassField extends JPasswordField {
    
    private Icon prefixIcon;
    private Icon suffixIconHidden;
    private Icon suffixIconShow;
    private String hint = "";
    private boolean c = true;

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

    public Icon suffixIconHidden() {
        return suffixIconHidden;
    }

    public void setSuffixIcon(Icon suffixIconHidden) {
        this.suffixIconHidden = suffixIconHidden;
        this.suffixIconShow = suffixIconHidden;        
        initBorder();
        check();
    }
    
    public void setSuffixIcon(Icon suffixIconHidden, Icon suffixIconShow) {
        this.suffixIconHidden = suffixIconHidden;
        this.suffixIconShow = suffixIconShow;
        initBorder();
        check();
    }
    
    public void change(Icon icon){
        this.suffixIconHidden = suffixIconShow;
        this.suffixIconShow = icon;
    }

    public PassField() {
        setBorder(javax.swing.BorderFactory.createEmptyBorder(10, 10, 10, 10));
        setBackground(new Color(0, 0, 0, 0));
        setForeground(Color.decode("#7A8C8D"));
        setFont(new java.awt.Font("sansserif", 0, 13));
        setSelectionColor(new Color(75, 175, 152));
    }

    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D) g;
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2.setColor(new Color(230, 245, 241));
        g2.fillRoundRect(0, 0, getWidth(), getHeight(), 5, 5);
        paintIcon(g);
        super.paintComponent(g);
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        if (getPassword().length == 0) {
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
        
        if (suffixIconHidden != null) {
            Image suffix = ((ImageIcon) suffixIconHidden).getImage();
            int x = getWidth() - suffixIconHidden.getIconWidth() - 10;
            int y = (getHeight() - suffixIconHidden.getIconHeight()) / 2;
            g2.drawImage(suffix, x, y, this);
        }
    }

    private void initBorder() {
        int left = 15;
        int right = 15;
        //  5 is default
        if (prefixIcon != null) {
            //  prefix is left
            left = prefixIcon.getIconWidth() + 15;
        }
        if (suffixIconHidden != null) {
            //  suffix is right
            right = suffixIconHidden.getIconWidth() + 15;
        }
        setBorder(javax.swing.BorderFactory.createEmptyBorder(10, left, 10, right));
    }
    
    public void check() {
        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseReleased(MouseEvent e) {
                int x1 = e.getX();
                int x2 = getWidth() - suffixIconHidden.getIconWidth()-10;

                if(x1 >= x2 && x1 <= x2+16){
                    change(suffixIconHidden);
                    if(c){
                        setEchoChar((char)0);
                        c = !c;
                    } else {
                        setEchoChar('*');
                        c = !c;
                    }
                }
            }
        });
        
        addMouseMotionListener(new MouseAdapter() {
            @Override
            public void mouseMoved(MouseEvent e) {
                int x1 = e.getX();
                int x2 = getWidth() - suffixIconHidden.getIconWidth()-10;
                if(x1 >= x2 && x1 < x2+16){
                    setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
                } else {
                    setCursor(Cursor.getPredefinedCursor(Cursor.TEXT_CURSOR));
                }
            }
        });
    }
}
