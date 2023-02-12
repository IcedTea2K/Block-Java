package model;

import except.InvalidArgumentException;

// Add two numbers together
public class Add extends Arithmetic {
    @Override
    public boolean input(DataType... inputs) throws InvalidArgumentException {
        return false;
    }

    @Override
    public void execute() {

    }

    @Override
    public DataType getResult() {
        return null;
    }

    @Override
    public String getExamples() {
        return null;
    }
}
