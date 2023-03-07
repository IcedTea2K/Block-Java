package model;

import except.InvalidReturnTypeException;

import java.util.ArrayList;
import java.util.List;

// The data that commands will be working with.
// It acts as a wrapper class for int, boolean, and their list<>
public class DataType {
    private Integer number;
    private Boolean bool;
    private List<DataType> dataStream;
    private String type;

    // EFFECTS: store a stream of data
    public DataType(List<DataType> dataStream) {
        this.dataStream = new ArrayList<>(dataStream);
        this.type = "data stream";
    }

    // EFFECTS: store a number as data
    public DataType(int number) {
        this.number = number;
        this.type = "number";
    }

    // EFFECTS: store a boolean as data
    public DataType(boolean bool) {
        this.bool = bool;
        this.type = "boolean";
    }

    @Override
    public String toString() {
        if (number != null) {
            return number.toString();
        } else if (bool != null) {
            return bool.toString();
        }

        return dataStream.toString();
    }

    @Override
    // EFFECTS: check if current DataType is equal to the target DataType
    //          Only return true if they are of the same type
    //          and they have the same value.
    public boolean equals(Object obj) {
        if (obj == null || this.getClass() != obj.getClass()) {
            return false;
        }
        final DataType target = (DataType) obj;
        boolean result = false;
        try {
            if (number != null) {
                result = number.equals(target.getNumber());
            } else if (bool != null) {
                result = bool.equals(target.getBoolean());
            } else if (dataStream != null) {
                result = dataStream.equals(target.getDataStream());
            }
        } catch (InvalidReturnTypeException e) {
            return false;
        }

        return result;
    }

    @Override
    // EFFECTS: produce a hash for this DataType
    public int hashCode() {
        int hash = 420;
        if (number != null) {
            hash *= number;
        } else if (bool != null) {
            hash = (bool) ? hash - 1 : -hash + 1;
        } else if (dataStream != null) {
            for (DataType data: dataStream) {
                hash += data.hashCode();
            }
        }
        return hash;
    }

    // EFFECTS: return data if it's represented as number
    public int getNumber() throws InvalidReturnTypeException {
        if (this.number == null) {
            throw new InvalidReturnTypeException(this.type);
        }
        return this.number;
    }

    // EFFECTS: return data if it's represented as boolean
    public boolean getBoolean() throws InvalidReturnTypeException {
        if (this.bool == null) {
            throw new InvalidReturnTypeException(this.type);
        }
        return this.bool;
    }

    // EFFECTS: return data if it's a represented as a stream of data
    public List<DataType> getDataStream() throws InvalidReturnTypeException {
        if (this.dataStream == null) {
            throw new InvalidReturnTypeException(this.type);
        }
        return new ArrayList<>(this.dataStream); // clone the data
    }
}
