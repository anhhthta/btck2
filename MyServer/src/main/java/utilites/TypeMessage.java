package utilites;

public enum TypeMessage {
    TEXT(0), EMOJI(1);
    private final int value;
    
    public int getValue() {
        return value;
    }
    
    private TypeMessage(int value) {
        this.value = value;
    }
    
    public static TypeMessage toTypeMessage(int value) {
        if(value == 0) {
            return TEXT;
        } else {
            return EMOJI;
        }
    }
}
