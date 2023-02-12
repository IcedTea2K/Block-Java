package except;

// Exception for when a command tries to obtain the result without executing the
// command at least once
public class NotYetExecutedException extends Exception {
    // EFFECTS: construct the exception with the exception message
    public NotYetExecutedException() {
        super("Unable to obtain the result. The command has not been executed yet");
    }
}
