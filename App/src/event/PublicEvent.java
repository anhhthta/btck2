package event;

public class PublicEvent {

    private static PublicEvent instance;

    private EventContent eventItemPeople;
    private EventChat eventChat;
    private EventMenuLeft eventMenuLeft;

    public static PublicEvent getInstance() {
        if (instance == null) {
            instance = new PublicEvent();
        }
        return instance;
    }

    private PublicEvent() {
    }

    public void addEventContent(EventContent event) {
        this.eventItemPeople = event;
    }

    public void addEventChat(EventChat event) {
        System.out.println("1");
        this.eventChat = event;
    }
    
    public void addEventMenuLef(EventMenuLeft event) {
        this.eventMenuLeft = event;
    }

    public EventContent getEventContent() {
        return this.eventItemPeople;
    }

    public EventChat getEventChat() {
        return this.eventChat;
    }
    
        
    public EventMenuLeft getEventMenuLeft() {
        return this.eventMenuLeft;
    }
}
