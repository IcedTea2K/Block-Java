package model;

public abstract class Bool extends Operator {
    @Override
    // EFFECTS: return the return type of the command
    public String getReturnType() {
        return "Boolean";
    }
}
