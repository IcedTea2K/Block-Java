package model;

import except.MissingArgumentException;

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
    public String getJava(int idx) throws MissingArgumentException {
        checkCurrentInputs();
        int tempNumOne = retrieveNumber(numOne);
        int tempNumTwo = retrieveNumber(numTwo);

        String msg = "int result" +  idx + " = " + tempNumOne + " + " + tempNumTwo + ";";
        return msg;
    }

    @Override
    // EFFECTS: get the name of the command
    public String getHeader() {
        return "ADD Command";
    }
}
