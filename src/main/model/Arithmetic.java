package model;

public abstract class Arithmetic extends Operator {
    @Override
    public String getReturnType() {
        return "Number";
    }
}
