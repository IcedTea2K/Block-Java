package model;

import except.InvalidArgumentException;
import except.InvalidReturnTypeException;
import except.WrongArgumentTypeException;

// Type of commands that take in two booleans and produce a boolean
public abstract class Logical extends Bool {
    @Override
    // EFFECTS: return the constraints of this command
    public String getConstraints() {
        return super.getConstraints() + " They both need to be booleans.";
    }

    @Override
    // EFFECTS: turn the command into a string
    public String toString() {
        String msg = getHeader().split(" ")[0] + " "
                + operandOne.getBoolean() + " " + operandTwo.getBoolean();
        return msg;
    }

    @Override
    // EFFECTS: If either of the inputs is not a number, throw WrongArgumentTypeException
    //          If the number of inputs don't match the constraints, throw
    //          UnexpectedNumberOfArgumentsException.
    protected void checkInput(DataType[] inputs) throws InvalidArgumentException {
        super.checkInput(inputs);

        try {
            boolean tempBoolOne = inputs[0].getBoolean();
            boolean tempBoolTwo = inputs[1].getBoolean();
        } catch (InvalidReturnTypeException e) {
            throw new WrongArgumentTypeException(e.getMessage(), "boolean");
        }
    }
}
