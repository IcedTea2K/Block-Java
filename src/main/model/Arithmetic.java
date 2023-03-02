package model;

// Type of commands that perform on numbers and return a number
public abstract class Arithmetic extends Operator {

    @Override
    // EFFECTS: return the return type of the command
    public String getReturnType() {
        return "Number";
    }

    @Override
    // EFFECTS: return the constraints of this command
    public String getConstraints() {
        return super.getConstraints() +  " They both need to be numbers.";
    }

    @Override
    // EFFECTS: turn the command into a string
    public String toString() {
        String msg = getHeader().split(" ")[0] + " "
                + retrieveData(operandOne) + " " + retrieveData(operandTwo);
        return msg;
    }

}
