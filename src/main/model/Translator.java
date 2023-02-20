package model;

import except.CommandNotFoundException;
import except.MissingCommandsException;
import except.NotYetExecutedException;

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
    public void executeStream() throws MissingCommandsException {

    }

    // EFFECTS: provide help about a specific command
    public String getHelp(Command command) {
        return null;
    }

    // EFFECTS: provide the general help about the program
    public String getHelp() {
        return null;
    }

    // EFFECTS: get the java representation of the current command stream
    public String translateToJava() throws MissingCommandsException, NotYetExecutedException {
        return null;
    }
    
    // EFFECTS: get the results from executing the (command) stream
    public List<DataType> getResults() throws MissingCommandsException, NotYetExecutedException {
        return null;
    }

    // EFFECTS: return the commands in the translator
    public String getStream() throws MissingCommandsException {
        return null;
    }

    // EFFECTS: get the (string) command at index
    public String getStringCommandAtIndex(int idx) throws CommandNotFoundException {
        return null;
    }

    // MODIFIES: this
    // EFFECTS: delete the command at index and return the new string of command stream
    public String deleteCommandAtIndex(int idx) throws CommandNotFoundException {
        return null;
    }
}
