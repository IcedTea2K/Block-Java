package model;

public abstract class Arithmetic extends Operator {
    @Override
    // EFFECTS: return the return type of the command
    public String getReturnType() {
        return "Number";
    }
}
