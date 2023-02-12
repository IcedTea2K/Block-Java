package model;

// Type of commands that does a comparison between two numbers and return a boolean
public abstract class Comparison extends Bool {
    @Override
    // EFFECTS: return the constraints specific to this command
    public String getConstraints() {
        return super.getConstraints() + "The two input must be numbers";
    }
}
