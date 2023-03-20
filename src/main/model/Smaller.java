package model;

import except.MissingArgumentException;

// Compare if the first number is less than the second number
public class Smaller extends Comparor {
    @Override
    // MODIFIES: this
    // EFFECTS: compare if the operandOne is smaller than operandTwo
    //          If either input is missing, throw MissingArgumentException
    public void execute() throws MissingArgumentException {
        isMissingInputs();
        result = new DataType(operandOne.getNumber() < operandTwo.getNumber());
    }

    @Override
    // EFFECTS: return some examples of the usage of SMALLER command
    public String getExamples() {
        String msg = "SMALLER 3 4\n"
                + "SMALLER -23 -20\n"
                + "SMALLER 80 -48";
        return msg;
    }

    @Override
    // EFFECTS: return Java representation of the command
    //          If either of the input is missing, throw MissingArgumentException
    public String getJava(int idx) throws MissingArgumentException {
        isMissingInputs();
        int tempNumOne = operandOne.getNumber();
        int tempNumTwo = operandTwo.getNumber();
        String msg = "boolean result" + idx + " = " + tempNumOne + " < " + tempNumTwo + ";";
        return msg;
    }

    @Override
    public String getHeader() {
        return "SMALLER Command";
    }
}
