package except;

// Exception for translator is trying to perform operations without any commands
public class MissingCommandsException extends Exception {
    public MissingCommandsException() {
        super("No commands have been provided to the translator");
    }
}
