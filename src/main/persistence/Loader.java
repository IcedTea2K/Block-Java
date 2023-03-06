package persistence;

import model.Command;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.util.List;

// An Object to parse specified JSON file to load the commands back into the program
public class Loader {
    private String fileName;
    private BufferedReader reader;

    // EFFECTS: create a Loader
    public Loader(String fileName) {
        this.fileName = fileName;
    }

    // EFFECTS: read from the file and return a list of saved commands
    public List<Command> read() throws FileNotFoundException {
        return null;
    }

    // EFFECTS: return the name of the targeted file
    public String getFileName() {
        return this.fileName;
    }

    // EFFECTS: parse the JSON entry and turn it into a Command
    private Command parse(JSONObject command) {
        return null;
    }
}
