package swing;

import java.awt.Color;
import java.awt.Dimension;
import javax.swing.JScrollBar;

/**
 *
 * @author anhth
 */

public class ScrollBar extends JScrollBar {
    public ScrollBar() {
        setUI(new ScrollBarUI());
        setPreferredSize(new Dimension(3, 5));
        setForeground(new Color(153,153,153, 50));
        setUnitIncrement(20);
        setOpaque(false);
    }
    
    public void setFgColor(Color color) {
        setForeground(color);
    }
    
    public void setSizeThumb(int x, int y){
        setPreferredSize(new Dimension(x, y));
    }
}