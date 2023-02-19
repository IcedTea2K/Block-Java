package model;

import except.*;

public class Divide extends Arithmetic {
    @Override
    public void input(DataType... inputs) throws InvalidArgumentException {

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

    @Override
    // EFFECTS: If either of the inputs is not a number, throw WrongArgumentTypeException
    //          If the number of inputs don't match the constraints, throw
    //          UnexpectedNumberOfArgumentsException.
    //          If the second number (denominator), throw DivideByZeroException
    //          Otherwise, do nothing
    protected void checkInput(DataType[] inputs) throws InvalidArgumentException {
        super.checkInput(inputs);

        try {
            int tempDenominator = inputs[1].getNumber();
            if (tempDenominator == 0) {
                throw new DivideByZeroException();
            }
        } catch (InvalidReturnTypeException e) {
            assert true; // no exception should be raised
        }
    }
}
