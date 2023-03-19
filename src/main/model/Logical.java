package model;

// Type of commands that take in two booleans and produce a boolean
public abstract class Logical extends Bool {
    @Override
    // EFFECTS: return the constraints of this command
    public String getConstraints() {
        return super.getConstraints() + " They both need to be numbers.";
    }

    @Override
    // EFFECTS: turn the command into a string
    public String toString() {
        String msg = getHeader().split(" ")[0] + " "
                + operandOne.getNumber() + " " + operandTwo.getNumber();
        return msg;
    }
}
