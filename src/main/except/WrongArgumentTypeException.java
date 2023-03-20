package except;

// Exception for when the user's input the wrong argument type
public class WrongArgumentTypeException extends InvalidArgumentException {
    // EFFECTS: create the exception with a specific type of message
    public WrongArgumentTypeException(String givenType, String expectedType) {
        super("Expecting " + expectedType + " Received " + givenType);
    }

    public WrongArgumentTypeException(String msg) {
        super(msg);
    }
}
