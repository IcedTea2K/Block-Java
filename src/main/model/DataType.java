package model;

import java.util.List;

// The data that commands will be working with.
// It acts as a wrapper class for int, boolean, and their list<>
public class DataType {
    private int number;
    private boolean bool;

    public DataType(int number) {
        this.number = number;
    }

    public DataType(boolean bool) {
        this.bool = bool;
    }

    public int getNumber() {
        return this.number;
    }

    public boolean getBoolean() {
        return this.bool;
    }
}
