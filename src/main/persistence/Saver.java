package persistence;

import except.NotYetExecutedException;
import except.WarningException;
import model.Command;

import java.io.FileNotFoundException;
import java.util.List;
import java.io.PrintWriter;

// An object to save the state of the program into a specified file
public class Saver {
    private String fileName;
    private PrintWriter writer;

    public Saver(String fileName) {
        this.fileName = fileName;
    }

    // EFFECTS: Save the commands into specified file in JSON format
    public void write(List<Command> commands, boolean isForcedWriting)
            throws FileNotFoundException, NotYetExecutedException, WarningException {

    }

    // EFFECTS: return the target file stored in the Saver
    public String getFileName() {
        return this.fileName;
    }

    // MODIFIES: this
    // EFFECTS: initialize PrinterWriter and open the file
    private void open() {

    }

    // MODIFIES: this
    // EFFECTS: close the file and remove writer
    private void close() {

    }
}
