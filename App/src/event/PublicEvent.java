package event;

public class PublicEvent {

    private static PublicEvent instance;
    
    private EventToServer eventToServer;
    private EventContent eventItemPeople;
    private EventChat eventChat;
    private EventMenu eventMenu;
    public EventEncrypt eventEncrypt;
    public EventUpdate eventUpdate;

    public static PublicEvent getInstance() {
        if (instance == null) {
            instance = new PublicEvent();
        }
        return instance;
    }

    private PublicEvent() {
    }

    public void addEventToServer(EventToServer event) {
        this.eventToServer = event;
    }
    
    public void addEventContent(EventContent event) {
        this.eventItemPeople = event;
    }

    public void addEventChat(EventChat event) {
        System.out.println("1");
        this.eventChat = event;
    }
    
    public void addEventMenu(EventMenu event) {
        this.eventMenu = event;
    }
    
    public void addEventEncrypt(EventEncrypt event) {
        this.eventEncrypt = event;
    }
    
    public void addEventUpdate(EventUpdate event) {
        this.eventUpdate = event;
    }

    public EventToServer getEventToServer() {
        return eventToServer;
    }

    public EventContent getEventItemPeople() {
        return eventItemPeople;
    }
    
    

    public EventContent getEventContent() {
        return this.eventItemPeople;
    }

    public EventChat getEventChat() {
        return this.eventChat;
    }
    
        
    public EventMenu getEventMenu() {
        return this.eventMenu;
    }

    public EventEncrypt getEventEncrypt() {
        return eventEncrypt;
    }

    public EventUpdate getEventUpdate() {
        return eventUpdate;
    }
    
    
}
