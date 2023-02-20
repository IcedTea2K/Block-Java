package model;

import java.util.LinkedList;
import java.util.List;

public class Translator {
    private List<Command> stream;

    // EFFECTS: create a translator with empty (command) stream
    public Translator() {
        stream = new LinkedList<>();
    }

    // MODIFIES: this
    // EFFECTS: add the command to the translator
    public void addCommand(Command command) {

    }

    // MODIFIES: this
    // EFFECTS: execute the (command) stream
    public void executeStream() {

    }

    // EFFECTS: provide help about a specific command
    public String getHelp(String strCommand) {
        return null;
    }

    // EFFECTS: provide the general help about the program
    public String getHelp() {
        return null;
    }

    // EFFECTS: get the java representation of the current command stream
    public String translateToJava() {
        return null;
    }
    
    // EFFECTS: get the string result from executing the (command) stream
    public String getResults() {
        return null;
    }

    // EFFECTS: return the commands in the translator
    public String getStream() {
        return null;
    }

    // EFFECTS: get the (string) command at index
    public String getStringCommandAtIndex(int idx) {
        return null;
    }

    // MODIFIES: this
    // EFFECTS: delete the command at index and return the new string of command stream
    public String deleteCommandAtIndex(int idx) {
        return null;
    }
}
