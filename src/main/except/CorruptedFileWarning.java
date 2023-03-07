package except;

// Warns the user about the potential corrupted file
public class CorruptedFileWarning extends WarningException {
    // EFFECTS: construct a warning when there's an internal reading exception (IOException)
    public CorruptedFileWarning(String fileName) {
        super("Warning: The file " + fileName + " can not be resolved.");
    }

    // EFFECTS: construct a message when there's an error parsing the file
    public CorruptedFileWarning(String fileName, int objectPosition) {
        super("Warning: Object at position " + objectPosition + " can not be resolved. The file "
                + fileName + " might be corrupted.");
    }
}
