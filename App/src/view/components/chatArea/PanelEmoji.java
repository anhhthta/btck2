package view.components.chatArea;

import Emoji.Emoji;
import Emoji.ModelEmoji;
import event.PublicEvent;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;
import net.miginfocom.swing.MigLayout;
import swing.ScrollBar;
import swing.card.PanelCard;
import swing.layout.WrapLayout;

public class PanelEmoji extends javax.swing.JPanel {
    private JPanel container;
    public PanelEmoji() {
        initComponents();
        init();
    }
    
    private void init() {
        setLayout(new MigLayout("fillx"));
        container = new PanelCard();
        container.setLayout(new WrapLayout(WrapLayout.LEFT, 0, 0));
        JScrollPane ch = new JScrollPane(container);
        ch.setBorder(null);
        ch.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        ScrollBar sb = new ScrollBar();
        sb.setSizeThumb(5,5);
        sb.setFgColor(new Color(92,129,253));
        sb.setBackground(new Color(92,129,253));
        container.setBackground(new Color(182,204,254));
//        container.setOpaque(false);
        ch.setVerticalScrollBar(sb);
        addEmoji();
        add(ch,"w 100%, h 100%");
    }
    
    private void addEmoji() {
        for(ModelEmoji d : Emoji.getInstance().getStyle()) {
            
            container.add(getButton(d));
        }
        container.repaint();
        container.revalidate();
    }
   
    private JButton getButton(ModelEmoji d) {
        JButton c = new JButton(Emoji.getInstance().getEmoji(d.getId()).toSize(28, 28).getIcon());
        c.setName(d.getId() +"");
        c.setBorder(new EmptyBorder(3,3,3,3));
        c.setCursor(new Cursor(Cursor.HAND_CURSOR));
        c.setContentAreaFilled(false);
        
        c.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                PublicEvent.getInstance().getEventChat().sendEMOJI(c.getName());
            }
            
        });
        return c;
    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        setOpaque(false);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 69, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
