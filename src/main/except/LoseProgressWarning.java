package except;

// Exception to warn that the progress of program might get lost
public class LoseProgressWarning extends WarningException {
    public LoseProgressWarning(String keyWord, String stringOperation) {
        super("Warning: " + keyWord + " progress might get lost if you " + stringOperation);
    }
}
