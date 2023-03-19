package model;

// Type of commands that return a boolean
public abstract class Bool extends Operator {
    @Override
    public String getReturnType() {
        return "Boolean";
    }
}
