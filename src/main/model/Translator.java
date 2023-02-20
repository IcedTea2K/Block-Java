package model;

import except.CommandNotFoundException;
import except.MissingArgumentException;
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

    // REQUIRES: inputs are already stored in the commands
    // MODIFIES: this
    // EFFECTS: add the command to the translator
    public void addCommand(Command command) {
        stream.add(command);
    }

    // MODIFIES: this
    // EFFECTS: execute the (command) stream
    public void executeStream() throws MissingCommandsException {
        checkCurrentStream();
        for (Command c : stream) {
            c.execute();
        }
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
    public String getResults() throws MissingCommandsException, NotYetExecutedException {
        checkCurrentStream();

        String msg = "";
        for (int i = 0; i < stream.size(); i++) {
            msg += "#" + (i + 1) + "| " + stream.get(i).getResult().getNumber() + "\n";
        }
        return msg;
    }

    // EFFECTS: return the commands in the translator.
    //          If there are no command currently in the stream, throw MissingCommandsException
    public String getStream() throws MissingCommandsException {
        checkCurrentStream();
        String msg = "";
        for (int i = 0; i < stream.size(); i++) {
            msg += "#" + (i + 1) + "| " + stream.get(i).toString() + "\n";
        }

        return msg;
    }

    // EFFECTS: get the (string) command at index
    public String getStringCommandAtIndex(int idx) throws CommandNotFoundException {
        return null;
    }

    // MODIFIES: this
    // EFFECTS: delete the command at index and return the new string of command stream
    public String deleteCommandAtIndex(int idx) throws CommandNotFoundException, MissingCommandsException {
        return null;
    }

    // EFFECTS: if there's no command in the stream, throw MissingCommandsException.
    //          Otherwise, do nothing.
    private void checkCurrentStream() throws MissingCommandsException {
        if (stream.size() == 0) {
            throw new MissingCommandsException();
        }
    }
}
