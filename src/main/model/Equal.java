package model;

import except.MissingArgumentException;

// Compare if two numbers are equal to each other
public class Equal extends Comparor {
    @Override
    // MODIFIES: this
    // EFFECTS: compare if the operandOne is equal to operandTwo
    //          If either input is missing, throw MissingArgumentException
    public void execute() throws MissingArgumentException {
        isMissingInputs();
        result = new DataType(operandOne.getNumber() == operandTwo.getNumber());
    }

    @Override
    // EFFECTS: return some examples of the usage of LARGER command
    public String getExamples() {
        String msg = "EQUAL -4 -4\n"
                + "EQUAL 39 120\n"
                + "EQUAL -59 20\n";
        return msg;
    }

    @Override
    // EFFECTS: return Java representation of the command
    //          If either of the input is missing, throw MissingArgumentException
    public String getJava(int idx) throws MissingArgumentException {
        isMissingInputs();
        int tempNumOne = operandOne.getNumber();
        int tempNumTwo = operandTwo.getNumber();
        String msg = "boolean result" + idx + " = " + tempNumOne + " == " + tempNumTwo + ";";
        return msg;
    }

    @Override
    public String getHeader() {
        return "EQUAL Command";
    }
}
