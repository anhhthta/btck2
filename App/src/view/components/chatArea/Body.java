package view.components.chatArea;

import java.awt.Adjustable;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.AdjustmentEvent;
import java.awt.event.AdjustmentListener;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.Icon;
import javax.swing.JScrollBar;
import model.ModelSend;
import net.miginfocom.swing.MigLayout;
import swing.ScrollBar;
import utilites.TypeMessage;

/**
 *
 * @author anhth
 */
public class Body extends javax.swing.JPanel {

    private ActionListener event;

    public Body() {
        initComponents();
        init();
    }

    private void init() {
        body.setLayout(new MigLayout("fillx", "", "3[]3"));
        ScrollBar sb = new ScrollBar();
        sb.setSizeThumb(5, 5);
        sb.setFgColor(new Color(92,129,253));
        sb.setBackground(new Color(92,129,253));
        scroll.setVerticalScrollBar(sb);
    }

    public void addRightItem(ModelSend data, Icon... images) {
        for (Icon img : images) {
            ImageItem imageItem = new ImageItem();
            imageItem.addImage(img);
            body.add(imageItem, "wrap, al right, w ::75%");
        }
        if(data.getTypeMessage() == TypeMessage.TEXT) {
            ChatItem item = new ChatItem();
            item.setTextRight(data.getText(), data.getTime());
            body.add(item, "wrap, al right, w ::75%");
        } else {
            ChatItemEmoji item = new ChatItemEmoji();
            item.setTextRight(data.getText(), data.getTime());
            body.add(item, "wrap, al right, w ::75%");
        }

//        ::x is max 
        body.repaint();
        body.revalidate();
        scrollToBottom();
        refresh();
    }

    public void addGroupItem(ModelSend data, Icon... images) {
//        ChatItem user = new ChatItem();
//        user.setUserName(data.getUser().getUserName());

//        body.add(user, "wrap, w ::75%");
        for (Icon img : images) {
            ImageItem imageItem = new ImageItem();
            imageItem.addImage(img);
            body.add(imageItem, "wrap, w ::75%");
        }

        if(data.getTypeMessage() == TypeMessage.TEXT) {
            ItemChatLeft item = new ItemChatLeft();
            item.setData(data);

            body.add(item, "wrap, w ::75%");
        } else {
            ItemChatEmojiLeft item = new ItemChatEmojiLeft();
            item.setData(data);

            body.add(item, "wrap, w ::75%");
        }
        
//        ::x is max 
        body.repaint();
        body.revalidate();
        scrollToBottom();
        refresh();
    }

    public void addEvent(ActionListener event) {
        this.event = event;
    }

    public void refresh() {
        repaint();
        revalidate();
    }

    public void clearChat() {
        body.removeAll();
        repaint();
        revalidate();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        scroll = new javax.swing.JScrollPane();
        body = new javax.swing.JPanel();

        setBackground(new java.awt.Color(255, 255, 255));

        scroll.setBorder(null);
        scroll.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);

        body.setBackground(new java.awt.Color(215, 227, 252));

        javax.swing.GroupLayout bodyLayout = new javax.swing.GroupLayout(body);
        body.setLayout(bodyLayout);
        bodyLayout.setHorizontalGroup(
            bodyLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 598, Short.MAX_VALUE)
        );
        bodyLayout.setVerticalGroup(
            bodyLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 385, Short.MAX_VALUE)
        );

        scroll.setViewportView(body);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(scroll)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(scroll)
        );
    }// </editor-fold>//GEN-END:initComponents
    private void scrollToBottom() {
        JScrollBar verticalBar = scroll.getVerticalScrollBar();
        AdjustmentListener downScroller = new AdjustmentListener() {
            @Override
            public void adjustmentValueChanged(AdjustmentEvent e) {
                Adjustable adjustable = e.getAdjustable();
                new Thread(() -> {
                    try {
                        Thread.sleep(10);
                        adjustable.setValue(adjustable.getMaximum());
                        verticalBar.removeAdjustmentListener(this);
                        body.repaint();
                        body.revalidate();
                        refresh();

                    } catch (InterruptedException ex) {
                        Logger.getLogger(Body.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }).start();
            }
        };
        verticalBar.addAdjustmentListener(downScroller);
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel body;
    private javax.swing.JScrollPane scroll;
    // End of variables declaration//GEN-END:variables
}
