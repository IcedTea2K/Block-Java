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
    public void execute() throws MissingArgumentException {

    }

    @Override
    public DataType getResult() throws NotYetExecutedException {
        return null;
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
}
