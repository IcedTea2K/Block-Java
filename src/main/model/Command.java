package model;

import except.InvalidArgumentException;
import except.MissingArgumentException;
import except.NotYetExecutedException;

// Interface for Block Java Commands
public interface Command {
    // MODIFIES: this
    // EFFECTS: take in inputs
    //          If the inputs don't match the constraints of the command (e.g, number of inputs
    //          or type of inputs), InvalidArgumentException will be raised
    public void input(DataType... inputs) throws InvalidArgumentException;

    // REQUIRES: valid input have already been stored (with input() that returns true)
    // MODIFIES: this
    // EFFECTS: execute the command with the current
    public void execute() throws MissingArgumentException;

    // REQUIRES: only after the command has executed at least once
    // EFFECTS: return the result of the command
    public DataType getResult() throws NotYetExecutedException;

    // EFFECTS: return the return type of the command
    public String getReturnType();

    // EFFECTS: return the constraints specific to this command
    public String getConstraints();

    // EFFECTS: provide examples on how to use the command
    public String getExamples();

    // EFFECTS: return the Java code representation of this command
    public String getJava(int idx) throws MissingArgumentException;

    public String getHeader();
}
