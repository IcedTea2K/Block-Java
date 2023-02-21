package model;

import except.MissingArgumentException;

// A command to subtract the second number from the first number
public class Subtract extends Arithmetic {
    @Override
    // MODIFIES: this
    // EFFECTS: subtract the second number from the first number
    //          If either input is missing, throw MissingArgumentException
    public void execute() throws MissingArgumentException {
        checkCurrentInputs();

        int tempNumOne = retrieveData(numOne);
        int tempNumTwo = retrieveData(numTwo);
        result = new DataType(tempNumOne - tempNumTwo);
    }

    @Override
    // EFFECTS: return some examples of this command
    public String getExamples() {
        String msg = "SUB 1337 89\n"
                + "SUB 28.1 39\n"
                + "SUB 38 -10";
        return msg;
    }

    @Override
    // EFFECTS: return the Java code representation of this command
    //          with the given inputs.
    public String getJava(int idx) throws MissingArgumentException {
        checkCurrentInputs();
        int tempNumOne = retrieveData(numOne);
        int tempNumTwo = retrieveData(numTwo);

        String msg = "int result" + idx + " = " + tempNumOne + " - " + tempNumTwo + ";";
        return msg;
    }

    @Override
    // EFFECTS: get the name of the command
    public String getHeader() {
        return "SUB Command";
    }
}
