package persistence;

import except.LoseProgressWarning;
import except.NotYetExecutedException;
import except.WarningException;
import model.Command;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.*;
import java.util.List;

// An object to save the state of the program into a specified file
public class Saver {
    private String fileName;
    private PrintWriter writer;
    private static int TAB = 4;

    public Saver(String fileName) {
        this.fileName = fileName;
    }

    // EFFECTS: Save the commands into specified file in JSON format
    public void write(List<Command> commands, boolean isForcedWriting)
            throws FileNotFoundException, WarningException {
        open();
        JSONObject commandsJson = new JSONObject();
        for (int i = 0; i < commands.size(); i++) {
            commandsJson.put(Integer.toString(i), commands.get(i).toJson());
        }
        writer.print(commandsJson.toString(TAB));
        close();
    }

    // EFFECTS: return the target file stored in the Saver
    public String getFileName() {
        return this.fileName;
    }

    // MODIFIES: this
    // EFFECTS: initialize PrinterWriter and open the file.
    //          throw a LoseProgressWarning if the saved file is not empty
    //          throw IOException if the file path is invalid
    private void open() throws LoseProgressWarning, FileNotFoundException {
        if (!isFileEmpty()) {
            throw new LoseProgressWarning("saved", "save current program.");
        } else {
            writer = new PrintWriter(fileName);
        }
        isFileEmpty();
    }

    // MODIFIES: this
    // EFFECTS: close the file and remove writer
    private void close() {
        writer.close();
        writer = null;
    }

    // EFFECTS: check if the targeted file is empty
    private boolean isFileEmpty() {
        BufferedReader file;
        try {
            file = new BufferedReader(new FileReader(fileName));
            if (file.readLine() != null) {
                return false;
            }
        } catch (IOException e) {
            return true;
        }
        return true;
    }
}
