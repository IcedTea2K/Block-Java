package model;

import except.MissingArgumentException;

// Type of command that compares two different boolean with OR operator
public class Or extends Logical {
    @Override
    public void execute() throws MissingArgumentException {
        isMissingInputs();
        result = new DataType(operandOne.getBoolean() || operandTwo.getBoolean());
    }

    @Override
    public String getExamples() {
        String msg = "OR TRUE FALSE\n"
                + "OR FALSE FALSE\n"
                + "OR TRUE TRUE\n";
        return msg;
    }

    @Override
    public String getJava(int idx) throws MissingArgumentException {
        isMissingInputs();
        boolean tempBoolOne = operandOne.getBoolean();
        boolean tempBoolTwo = operandTwo.getBoolean();
        String msg = "boolean result" + idx + " = " + tempBoolOne + " || " + tempBoolTwo + ";";
        return msg;
    }

    @Override
    public String getHeader() {
        return "OR Command";
    }
}
