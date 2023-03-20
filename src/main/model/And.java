package model;

import except.MissingArgumentException;

// Command that compares two booleans using and operator
public class And extends Logical {
    @Override
    // MODIFIES: this
    // EFFECTS: compare two different booleans with and operator
    //          If either input is missing, throw MissingArgumentException
    public void execute() throws MissingArgumentException {
        isMissingInputs();
        result = new DataType(operandOne.getBoolean() && operandTwo.getBoolean());
    }

    @Override
    // EFFECTS: return some examples of the usage of AND command
    public String getExamples() {
        String msg = "AND TRUE FALSE\n"
                + "AND FALSE FALSE\n"
                + "AND TRUE TRUE";
        return msg;
    }

    @Override
    // EFFECTS: return Java representation of the command
    //          If either of the input is missing, throw MissingArgumentException
    public String getJava(int idx) throws MissingArgumentException {
        isMissingInputs();
        boolean tempBoolOne = operandOne.getBoolean();
        boolean tempBoolTwo = operandTwo.getBoolean();
        String msg = "boolean result" + idx + " = " + tempBoolOne + " && " + tempBoolTwo + ";";
        return msg;
    }

    @Override
    public String getHeader() {
        return "AND Command";
    }
}
