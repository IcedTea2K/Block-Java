package model;

import except.*;

public class Divide extends Arithmetic {
    @Override
    // MODIFIES: this
    // EFFECTS: take in inputs
    //          If the number of inputs is more than 2 or the type of input is not number or the
    //          denominator is 0, InvalidArgumentException will be raised.
    public void input(DataType... inputs) throws InvalidArgumentException {
        checkInput(inputs);
        numOne = inputs[0];
        numTwo = inputs[1];
    }

    @Override
    public void execute() throws MissingArgumentException {
        checkCurrentInputs();

        int tempNumOne = retrieveNumber(numOne);
        int tempNumTwo = retrieveNumber(numTwo);
        result = new DataType(tempNumOne / tempNumTwo);
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
