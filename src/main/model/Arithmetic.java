package model;

import except.InvalidArgumentException;
import except.InvalidReturnTypeException;
import except.WrongArgumentTypeException;

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

    @Override
    // EFFECTS: turn the command into a string
    public String toString() {
        String msg = getHeader().split(" ")[0] + " "
                + retrieveData(operandOne) + " " + retrieveData(operandTwo);
        return msg;
    }

    @Override
    // EFFECTS: If either of the inputs is not a number, throw WrongArgumentTypeException
    //          If the number of inputs don't match the constraints, throw
    //          UnexpectedNumberOfArgumentsException.
    protected void checkInput(DataType[] inputs) throws InvalidArgumentException {
        super.checkInput(inputs);

        try {
            int tempNumOne = inputs[0].getNumber();
            int tempNumTwo = inputs[1].getNumber();
        } catch (InvalidReturnTypeException e) {
            throw new WrongArgumentTypeException(e.getMessage(), "number");
        }
    }

    // EFFECTS: return the number the DataType is representing
    protected int retrieveData(DataType num) {
        int tempNum = 0;
        tempNum = num.getNumber();
        return tempNum;
    }

}
