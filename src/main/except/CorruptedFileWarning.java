package except;

// Warns the user about the potential corrupted file
public class CorruptedFileWarning extends WarningException {
    public CorruptedFileWarning(String fileName) {
        super("Warning: The file " + fileName + " might be corrupted.");
    }
}
