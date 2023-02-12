package model;

import except.InvalidArgumentException;
import except.MissingArgumentException;

import java.util.List;

// Add two numbers together
public class Add extends Arithmetic {
    @Override
    public void input(DataType... inputs) throws InvalidArgumentException {

    }

    @Override
    public void execute() throws MissingArgumentException {

    }

    @Override
    public DataType getResult() {
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
