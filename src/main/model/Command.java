package model;

import except.InvalidArgumentException;

// Interface for Block Java Commands
public interface Command {
    // MODIFIES: this
    // EFFECTS: if the data type fits the constraints specified for
    //          this particular command, store inputs for the command and return true
    //          Otherwise, return false and do nothing
    public void input(DataType... inputs) throws InvalidArgumentException;

    // REQUIRES: valid input have already been stored (with input() that returns true)
    // MODIFIES: this
    // EFFECTS: execute the command with the current
    public void execute();

    // REQUIRES: only after the command has executed at least once
    // EFFECTS: return the result of the command
    public DataType getResult();

    // EFFECTS: return the return type of the command
    public String getReturnType();

    // EFFECTS: return the constraints specific to this command
    public String getConstraints();

    // EFFECTS: provide examples on how to use the command
    public String getExamples();
}
