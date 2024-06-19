package Emoji;

import java.util.ArrayList;
import java.util.List;
import javax.swing.ImageIcon;

public class Emoji {
    private static Emoji instance;
    
    public static Emoji getInstance() {
        if(instance == null) {
            instance = new Emoji();
        }
        return instance;
    }
    
    private Emoji(){
    }
    
    public List<ModelEmoji> getStyle() {
        List<ModelEmoji> list = new ArrayList<>();
        
        for(int i = 1; i <= 20; i++) {
            list.add(new ModelEmoji(i, new ImageIcon(getClass().getResource("/icon/emoji/icon_"+i+".png"))));
        }
        
        return list;
    }
    
    
    public ModelEmoji getEmoji(int id) {
        return new ModelEmoji(id,  new ImageIcon(getClass().getResource("/icon/emoji/icon_"+id+".png")));
    }
}
