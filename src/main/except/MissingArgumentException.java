package except;

// Exception for when the command ties to perform certain operations without
// any input
public class MissingArgumentException extends Exception {
    // EFFECTS: construct the exception with the exception message
    public MissingArgumentException() {
        super("No argument has been given");
    }
}
