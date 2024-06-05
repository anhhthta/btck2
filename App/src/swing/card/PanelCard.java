
package swing.card;

import java.awt.Color;
import javax.swing.JPanel;
import swing.layout.WrapLayout;

public class PanelCard extends JPanel{
    private WrapLayout layout;
    
    public PanelCard() {
        setBackground(Color.WHITE);
        layout = new WrapLayout(WrapLayout.LEFT, 4, 4);
        setLayout(layout);
    }
    
    public void setAlign(int align) {
        layout.setAlignment(align);
    } 
}
