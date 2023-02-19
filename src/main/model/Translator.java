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
    // EFFECTS: handle user inputs and return the results from the inputs
    public String input(String msg) {
        return null;
    }

    // MODIFIES: this
    // EFFECTS: execute the (command) stream
    private void executeStream() {

    }

    // EFFECTS: provide help about a specific command
    private String help(String strCommand) {
        return null;
    }

    // EFFECTS: provide the general help about the program
    private String help() {
        return null;
    }

    // EFFECTS: get the java representation of the current command stream
    private String getJava() {
        return null;
    }
    
    // EFFECTS: get the string result from executing the (command) stream
    private String getResults() {
        return null;
    }

    // EFFECTS: return the commands of the
    private String getStream() {
        return null;
    }

    // EFFECTS: get the (string) command at index
    private String getStringCommandAtIndex(int idx) {
        return null;
    }

    // MODIFIES: this
    // EFFECTS: delete the command at index and return the new string of command stream
    private String deleteCommandAtIndex(int idx) {
        return null;
    }
}
