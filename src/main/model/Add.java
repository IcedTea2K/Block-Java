package model;

import except.InvalidArgumentException;
import except.MissingArgumentException;
import except.NotYetExecutedException;

import java.util.List;

// Add two numbers together
public class Add extends Arithmetic {
    private DataType numOne;
    private DataType numTwo;
    private DataType result;

    @Override
    public void input(DataType... inputs) throws InvalidArgumentException {
        if (inputs.length != 2) {
            throw new InvalidArgumentException(inputs.length, 2);
        }

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
}
