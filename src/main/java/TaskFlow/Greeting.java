package TaskFlow;

public class Greeting {
    private String message;
    private int code;

    public Greeting(String message, int code) {
        this.message = message;
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public int getCode() {
        return code;
    }
}
