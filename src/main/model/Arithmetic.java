package model;

import except.InvalidArgumentException;
import except.MissingArgumentException;

import java.util.List;

// Type of commands that perform on numbers and return a number
public abstract class Arithmetic extends Operator {
    @Override
    // EFFECTS: return the return type of the command
    public String getReturnType() {
        return "Number";
    }

    @Override
    // EFFECTS: return the constraints of this command
    public String getConstraints() {
        return super.getConstraints() +  " They both need to be numbers.";
    }

    // EFFECTS: return the two inputs that were given to the command
    public abstract List<DataType> getInputs() throws MissingArgumentException;
}
