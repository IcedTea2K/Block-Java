package except;

// Exception for when a DataType tries to access the wrong data
// that it represents
public class InvalidReturnTypeException extends Exception {
    public InvalidReturnTypeException(String expectedType) {
        super(expectedType);
    }
}
