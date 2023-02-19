package model;

import except.*;

import java.util.ArrayList;
import java.util.List;

// Type of commands that perform on numbers and return a number
public abstract class Arithmetic extends Operator {
    protected DataType numOne;
    protected DataType numTwo;
    protected DataType result;

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
    // MODIFIES: this
    // EFFECTS: take in inputs
    //          If the number of inputs is more than 2 or the type of input is not number,
    //          InvalidArgumentException will be raised.
    public void input(DataType... inputs) throws InvalidArgumentException {
        checkInput(inputs);
        numOne = inputs[0];
        numTwo = inputs[1];
    }

    @Override
    // EFFECTS: return the result of the addition
    //          If the command has yet to executed, throw NotYetExecutedException
    public DataType getResult() throws NotYetExecutedException {
        if (result == null) {
            throw new NotYetExecutedException();
        }
        return result;
    }

    // EFFECTS: return the inputs of this command
    public List<DataType> getInputs() throws MissingArgumentException {
        checkCurrentInputs();

        List<DataType> tempInputs = new ArrayList<>();
        tempInputs.add(numOne);
        tempInputs.add(numTwo);
        return tempInputs;
    }

    // EFFECTS: If either of the inputs is not a number, throw WrongArgumentTypeException
    //          If the number of inputs don't match the constraints, throw
    //          UnexpectedNumberOfArgumentsException.
    protected void checkInput(DataType[] inputs) throws InvalidArgumentException {
        if (inputs.length != 2) {
            throw new UnexpectedNumberOfArgumentsException(inputs.length, 2);
        }

        try {
            int tempNumOne = inputs[0].getNumber();
            int tempNumTwo = inputs[1].getNumber();
        } catch (InvalidReturnTypeException e) {
            throw new WrongArgumentTypeException(e.getMessage(), "number");
        }
    }

    // EFFECTS: return the number the DataType is representing
    protected int retrieveNumber(DataType num) {
        int tempNum = 0;
        try {
            tempNum = num.getNumber();
        } catch (InvalidReturnTypeException ignore) {
            // do nothing
        }
        return tempNum;
    }

    // EFFECTS: verify the current inputs
    protected void checkCurrentInputs() throws MissingArgumentException {
        if (numOne == null || numTwo == null) {
            throw new MissingArgumentException();
        }
    }
}
