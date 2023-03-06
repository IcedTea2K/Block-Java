package except;

// Exception for warning raised by the program
public abstract class WarningException extends Exception {
    public WarningException(String msg) {
        super(msg);
    }
}
