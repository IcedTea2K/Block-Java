package model;

import except.MissingArgumentException;

// A command to multiply two numbers
public class Multiply extends Arithmetic {
    @Override
    // MODIFIES: this
    // EFFECTS: multiply the two numbers
    //          If either input is missing, throw MissingArgumentException
    public void execute() throws MissingArgumentException {
        isMissingInputs();

        int tempNumOne = retrieveData(operandOne);
        int tempNumTwo = retrieveData(operandTwo);
        result = new DataType(tempNumOne * tempNumTwo);
    }

    @Override
    // EFFECTS: return some examples of this command
    public String getExamples() {
        String msg = "MUL 12 29\n"
                + "MUL 83.1 -12\n"
                + "MUL -31 -78";
        return msg;
    }

    @Override
    // EFFECTS: return the Java code representation of this command
    //          with the given inputs.
    public String getJava(int idx) throws MissingArgumentException {
        isMissingInputs();
        int tempNumOne = retrieveData(operandOne);
        int tempNumTwo = retrieveData(operandTwo);

        String msg = "int result" + idx + " = " + tempNumOne + " * " + tempNumTwo + ";";
        return msg;
    }

    @Override
    // EFFECTS: get the name of the command
    public String getHeader() {
        return "MUL Command";
    }
}
