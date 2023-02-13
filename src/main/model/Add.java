package model;

import except.InvalidArgumentException;
import except.InvalidReturnTypeException;
import except.MissingArgumentException;
import except.NotYetExecutedException;

import java.util.ArrayList;
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
        checkCurrentInputs();

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
    // EFFECTS: return some examples of this command
    public String getExamples() {
        String msg = "ADD 11 821\n"
                    + "ADD 21.4 10\n"
                    + "ADD -3 -10";;
        return msg;
    }

    @Override
    public String getJava() throws MissingArgumentException {
        checkCurrentInputs();

        String msg = "";
        return null;
    }

    @Override
    // EFFECTS: return the inputs of this command
    public List<DataType> getInputs() throws MissingArgumentException {
        checkCurrentInputs();

        List<DataType> tempInputs = new ArrayList<>();
        tempInputs.add(numOne);
        tempInputs.add(numTwo);
        return tempInputs;
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

    // EFFECTS: verify the current inputs
    private void checkCurrentInputs() throws MissingArgumentException {
        if (numOne == null || numTwo == null) {
            throw new MissingArgumentException();
        }
    }
}
