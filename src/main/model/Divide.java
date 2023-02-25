package model;

import except.*;

// Divide the first input by the second input
public class Divide extends Arithmetic {
    @Override
    // MODIFIES: this
    // EFFECTS: take in inputs
    //          If the number of inputs is more than 2 or the type of input is not number or the
    //          denominator is 0, InvalidArgumentException will be raised.
    public void input(DataType... inputs) throws InvalidArgumentException {
        checkInput(inputs);
        operandOne = inputs[0];
        operandTwo = inputs[1];
    }

    @Override
    // MODIFIES: this
    // EFFECTS: divide the first number by the second number
    //          If either input is missing, throw MissingArgumentException
    public void execute() throws MissingArgumentException {
        checkCurrentInputs();

        int tempNumOne = retrieveData(operandOne);
        int tempNumTwo = retrieveData(operandTwo);
        result = new DataType(tempNumOne / tempNumTwo);
    }

    @Override
    // EFFECTS: return some examples of this command
    public String getExamples() {
        String msg = "DIV 40 20\n"
                + "DIV -3 80\n"
                + "DIV 238 12";
        return msg;
    }

    @Override
    // EFFECTS: return the Java code representation of this command
    //          with the given inputs.
    public String getJava(int idx) throws MissingArgumentException {
        checkCurrentInputs();
        int tempNumOne = retrieveData(operandOne);
        int tempNumTwo = retrieveData(operandTwo);

        String msg = "int result" + idx + " = " + tempNumOne + " / " + tempNumTwo + ";";
        return msg;
    }

    @Override
    // EFFECTS: get the name of the command
    public String getHeader() {
        return "DIV Command";
    }

    @Override
    public String getConstraints() {
        return super.getConstraints() + " The second number (denominator) needs to be other than 0.";
    }

    @Override
    // EFFECTS: If either of the inputs is not a number, throw WrongArgumentTypeException
    //          If the number of inputs don't match the constraints, throw
    //          UnexpectedNumberOfArgumentsException.
    //          If the second number (denominator), throw DivideByZeroException
    //          Otherwise, do nothing
    protected void checkInput(DataType[] inputs) throws InvalidArgumentException {
        super.checkInput(inputs);

        int tempDenominator = inputs[1].getNumber();
        if (tempDenominator == 0) {
            throw new DivideByZeroException();
        }
    }
}
