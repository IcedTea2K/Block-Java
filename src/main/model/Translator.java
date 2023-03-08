package model;

import except.CommandNotFoundException;
import except.MissingCommandsException;
import except.NotYetExecutedException;

import java.util.LinkedList;
import java.util.List;

// Translate commands to Java as well as execute them
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
    public static String getHelp(Command command) {
        String msg = command.getHeader() + "\n"
                + "Constraints   " + command.getConstraints() + "\n"
                + "Return Type   " + command.getReturnType() + "\n"
                + "Examples:\n";

        String[] examples = command.getExamples().split("\n");
        for (String example : examples) {
            msg += "  " + example + "\n";
        }
        return msg;
    }

    // EFFECTS: provide the general help about the program
    @SuppressWarnings("methodlength")
    public static String getHelp() {
        String msg = "Built-in Commands:\n"
                + "ADD numOne numTwo    Add numOne and numTwo together\n"
                + "SUB numOne numTwo    Subtract numTwo from numOne\n"
                + "MUL numOne numTwo    Multiply numOne and numTwo together\n"
                + "DIV numOne numTwo    Divide numTwo from numOne\n\n"
                + "Exceptions:\n"
                + "InvalidArgumentException   Input for command is invalid\n"
                + " | WrongArgumentTypeException            Input has the wrong type\n"
                + " | UnexpectedNumberOfArgumentsException  The number of inputs don't match\n"
                + " | DivideByZeroException                 Trying to divide by zero\n"
                + "MissingCommandsException   No commands have been given to the translator\n"
                + "NotYetExecutedException    The commands have not been executed yet\n";
        return msg;
    }

    // EFFECTS: get the java representation of the current command stream
    public String translateToJava() throws MissingCommandsException {
        checkCurrentStream();
        String msg = "";
        for (int i = 0; i < stream.size(); i++) {
            msg += "#" + (i + 1) + "| " + stream.get(i).getJava(i + 1) + "\n";
        }
        return msg;
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
    public String getStringStream() throws MissingCommandsException {
        checkCurrentStream();
        String msg = "";
        for (int i = 0; i < stream.size(); i++) {
            msg += "#" + (i + 1) + "| " + stream.get(i).toString() + "\n";
        }

        return msg;
    }

    // EFFECTS: get the (string) command at index
    public String getStringCommandAtIndex(int idx) throws CommandNotFoundException {
        checkIndex(idx);
        String msg = "#" + idx + "| " + stream.get(idx - 1).toString();
        return msg;
    }

    // MODIFIES: this
    // EFFECTS: delete the command at index and return the new string of command stream
    public String deleteCommandAtIndex(int idx) throws CommandNotFoundException, MissingCommandsException {
        checkIndex(idx);
        stream.remove(idx - 1);
        return getStringStream();
    }

    // EFFECTS: return a copy of the current commands in the program
    public List<Command> getStream() {
        return new LinkedList<>(stream);
    }

    // EFFECTS: return true if the translator doesn't hold any command
    public boolean isStreamEmpty() {
        return this.stream.size() == 0;
    }

    // EFFECTS: if there's no command in the stream, throw MissingCommandsException.
    //          Otherwise, do nothing.
    private void checkCurrentStream() throws MissingCommandsException {
        if (stream.size() == 0) {
            throw new MissingCommandsException();
        }
    }

    // Effects: if the given index is OutOfBound in stream, throw CommandNotFoundException
    //          Otherwise, do nothing.
    private void checkIndex(int idx) throws CommandNotFoundException {
        try {
            stream.get(idx - 1);
        } catch (IndexOutOfBoundsException e) {
            throw new CommandNotFoundException(idx);
        }
    }
}
