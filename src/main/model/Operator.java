package model;

// Type of commands that do computation
public abstract class Operator implements Command {
    @Override
    // EFFECTS: return the constraints specific to this command
    public String getConstraints() {
        return "Operators only accept two inputs.";
    }
}
