package event;

public class PublicEvent {

    private static PublicEvent instance;
    
    private EventToServer eventToServer;
    private EventContent eventItemPeople;
    private EventChat eventChat;
    private EventEncrypt eventEncrypt;
    private EventUpdate eventUpdate;
    private EventNotificate eventNotificate;
    private EventLastTime eventLastTime;

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
    
    public void addEventEncrypt(EventEncrypt event) {
        this.eventEncrypt = event;
    }
    
    public void addEventUpdate(EventUpdate event) {
        this.eventUpdate = event;
    }
    
    public void addEventNotificate(EventNotificate event) {
        this.eventNotificate = event;
    }
    
    public void addEventLastTime(EventLastTime event) {
        this.eventLastTime = event;
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

    public EventEncrypt getEventEncrypt() {
        return eventEncrypt;
    }

    public EventUpdate getEventUpdate() {
        return eventUpdate;
    }

    public EventNotificate getEventNotificate() {
        return eventNotificate;
    }

    public EventLastTime getEventLastTime() {
        return eventLastTime;
    }
}
