package except;

// Exception for when user tries to access an unavailable command
public class CommandNotFoundException extends Exception {
    public CommandNotFoundException(String msg) {
        super(msg);
    }
}
