package model;

import except.InvalidArgumentException;

import java.util.List;

// Type of commands that perform on numbers and return a number
public abstract class Arithmetic extends Operator {
    @Override
    // EFFECTS: return the return type of the command
    public String getReturnType() {
        return "Number";
    }

    // EFFECTS: return the two inputs that were given to the command
    public abstract List<DataType> getInputs() throws InvalidArgumentException;
}
