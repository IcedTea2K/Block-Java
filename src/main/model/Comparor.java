package model;

import except.InvalidArgumentException;
import except.InvalidReturnTypeException;
import except.WrongArgumentTypeException;

// Type of command that compares two numbers and produce a boolean
public abstract class Comparor extends Bool {
    @Override
    // EFFECTS: return the constraints of this command
    public String getConstraints() {
        return super.getConstraints() + " They both need to be numbers.";
    }

    @Override
    // EFFECTS: turn the command into a string
    public String toString() {
        String msg = getHeader().split(" ")[0] + " "
                + operandOne.getNumber() + " " + operandTwo.getNumber();
        return msg;
    }

    @Override
    // EFFECTS: If either of the inputs is not a number, throw WrongArgumentTypeException
    //          If the number of inputs don't match the constraints, throw
    //          UnexpectedNumberOfArgumentsException.
    protected void checkInput(DataType[] inputs) throws InvalidArgumentException {
        super.checkInput(inputs);

        try {
            inputs[0].getNumber();
            inputs[1].getNumber();
        } catch (InvalidReturnTypeException e) {
            throw new WrongArgumentTypeException(e.getMessage(), "number");
        }
    }
}
