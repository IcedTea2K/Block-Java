package model;

import except.InvalidArgumentException;
import except.InvalidReturnTypeException;
import except.MissingArgumentException;
import except.NotYetExecutedException;

import java.util.ArrayList;
import java.util.List;

// A command to add two numbers
public class Add extends Arithmetic {
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
    // EFFECTS: return some examples of this command
    public String getExamples() {
        String msg = "ADD 11 821\n"
                    + "ADD 21.4 10\n"
                    + "ADD -3 -10";;
        return msg;
    }

    @Override
    // EFFECTS: return the Java code representation of this command
    //          with the given inputs.
    public String getJava() throws MissingArgumentException {
        checkCurrentInputs();
        int tempNumOne = retrieveNumber(numOne);
        int tempNumTwo = retrieveNumber(numTwo);

        String msg = "int result = " + tempNumOne + " + " + tempNumTwo + ";";
        return msg;
    }
}
