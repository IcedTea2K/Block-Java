package model;

// Type of commands that perform logical operators and return
// a boolean result
public abstract class Bool extends Operator {
    @Override
    // EFFECTS: return the return type of the command
    public String getReturnType() {
        return "Boolean";
    }
}
