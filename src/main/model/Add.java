package model;

import except.InvalidArgumentException;
import except.InvalidReturnTypeException;
import except.MissingArgumentException;
import except.NotYetExecutedException;

import java.util.List;

// Add two numbers together
public class Add extends Arithmetic {
    private DataType numOne;
    private DataType numTwo;
    private DataType result;

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
    // MODIFIES: this
    // EFFECTS: add the two given numbers
    //          If either input is missing, throw MissingArgumentException
    public void execute() throws MissingArgumentException {
        if (numOne == null | numTwo == null) {
            throw new MissingArgumentException();
        }

        int tempNumOne = retrieveNumber(numOne);
        int tempNumTwo = retrieveNumber(numTwo);
        result = new DataType(tempNumOne + tempNumTwo);
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

    @Override
    public String getExamples() {
        return null;
    }

    @Override
    public String getJava() throws MissingArgumentException {
        return null;
    }

    @Override
    public List<DataType> getInputs() throws MissingArgumentException {
        return null;
    }

    // EFFECTS: if there's only two inputs, and they are both numbers, do nothing.
    //          Otherwise, throw InvalidArgumentException
    private void checkInput(DataType[] inputs) throws InvalidArgumentException {
        if (inputs.length != 2) {
            throw new InvalidArgumentException(inputs.length, 2);
        }

        try {
            int tempNumOne = inputs[0].getNumber();
            int tempNumTwo = inputs[1].getNumber();
        } catch (InvalidReturnTypeException e) {
            throw new InvalidArgumentException(e.getMessage(), "number");
        }
    }

    // EFFECTS: return the number the DataType is representing
    private int retrieveNumber(DataType num) {
        int tempNum = 0;
        try {
            tempNum = num.getNumber();
        } catch (InvalidReturnTypeException ignore) {
            // do nothing
        }
        return tempNum;
    }
}
