package except;

// Exception for when user tries to access an unavailable command
public class CommandNotFoundException extends Exception {
    public CommandNotFoundException(int idx) {
        super("No command is found at index #" + idx);
    }
}
