package event;

/**
 *
 * @author anhth
 */
public class PublicEvent {
    
    private static PublicEvent instance;

    private Event1 event1;
    private Eventt eventt;
    
    public static PublicEvent getInstance() {
        if (instance == null) {
            instance = new PublicEvent();
        }
        return instance;
    }
    
    public PublicEvent() {
    }
    
    public void addEvent1(Event1 event1) {
        this.event1 = event1;
    }
    
    public void addEventt(Eventt eventt){
        this.eventt = eventt;
    }

    public Event1 getEvent1() {
        return this.event1;
    }
    
    public Eventt getEventt() {
        return this.eventt;
    }
    
}
