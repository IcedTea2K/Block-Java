package model;

// Type of commands that perform on numbers and return a number
public abstract class Arithmetic extends Operator {
    @Override
    // EFFECTS: return the return type of the command
    public String getReturnType() {
        return "Number";
    }
}
