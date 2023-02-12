package model;

import except.InvalidReturnTypeException;

import java.util.ArrayList;
import java.util.List;

// The data that commands will be working with.
// It acts as a wrapper class for int, boolean, and their list<>
public class DataType {
    private int number;
    private boolean bool;
    private List<DataType> dataStream;

    // EFFECTS: store a stream of data
    public DataType(List<DataType> dataStream) {
        this.dataStream = new ArrayList<>(dataStream);
    }

    // EFFECTS: store a number as data
    public DataType(int number) {
        this.number = number;
    }

    // EFFECTS: store a boolean as data
    public DataType(boolean bool) {
        this.bool = bool;
    }

    // EFFECTS: return data if it's represented as number
    public int getNumber() throws InvalidReturnTypeException {
        return this.number;
    }

    // EFFECTS: return data if it's represented as boolean
    public boolean getBoolean() throws InvalidReturnTypeException {
        return this.bool;
    }

    // EFFECTS: return data if it's a represented as a stream of data
    public List<DataType> getDataStream() throws InvalidReturnTypeException {
        return new ArrayList<>(this.dataStream);
    }
}
