package except;

// Exception for when user's input to a command is invalid
public class InvalidArgumentException extends Exception {
    // EFFECTS: Create the exception with given message
    public InvalidArgumentException(String msg) {
        super(msg);
    }
}
