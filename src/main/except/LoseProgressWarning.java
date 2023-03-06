package except;

// Exception to warn that the progress of program might get lost
public class LoseProgressWarning extends WarningException {
    public LoseProgressWarning(String msg) {
        super("Warning: Saved progress might get lost with this operation");
    }
}
