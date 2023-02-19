package model;

import except.DivideByZeroException;
import except.InvalidArgumentException;
import except.MissingArgumentException;

public class Divide extends Arithmetic {
    @Override
    public void input(DataType... inputs) throws InvalidArgumentException {
//        throw new InvalidArgumentException(2, 2);
    }

    @Override
    public void execute() throws MissingArgumentException {

    }

    @Override
    public String getExamples() {
        return null;
    }

    @Override
    public String getJava() throws MissingArgumentException {
        return null;
    }
}
