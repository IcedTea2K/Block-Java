package model;

// Type of commands that perform logical operations and return a boolean
public abstract class Logical extends Bool {
    @Override
    public String getConstraints() {
        return super.getConstraints() + "The two inputs must be of type boolean";
    }
}
