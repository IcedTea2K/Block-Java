package model;

import except.MissingArgumentException;

// Compare if first number is larger than the second number
public class Larger extends Comparor {
    @Override
    // MODIFIES: this
    // EFFECTS: compare if the operandOne is larger than operandTwo
    //          If either input is missing, throw MissingArgumentException
    public void execute() throws MissingArgumentException {
        isMissingInputs();
        result = new DataType(operandOne.getNumber() > operandTwo.getNumber());
    }

    @Override
    // EFFECTS: return some examples of the usage of LARGER command
    public String getExamples() {
        String msg = "LARGER 30 -2\n"
                + "LARGER 2 3\n"
                + "LARGER -3 -54";
        return msg;
    }

    @Override
    // EFFECTS: return Java representation of the command
    //          If either of the input is missing, throw MissingArgumentException
    public String getJava(int idx) throws MissingArgumentException {
        isMissingInputs();
        int tempNumOne = operandOne.getNumber();
        int tempNumTwo = operandTwo.getNumber();
        String msg = "boolean result" + idx + " = " + tempNumOne + " > " + tempNumTwo + ";";
        return msg;
    }

    @Override
    public String getHeader() {
        return "LARGER Command";
    }
}
