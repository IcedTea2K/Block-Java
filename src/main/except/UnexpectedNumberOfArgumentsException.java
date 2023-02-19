package except;

// Exception for when there is an unexpected number of arguments
public class UnexpectedNumberOfArgumentsException extends InvalidArgumentException {
    // EFFECTS: create the exception with exception message
    public UnexpectedNumberOfArgumentsException(int numberOfInputs, int expectedInputs) {
        super("Expecting " + expectedInputs + " Received " + numberOfInputs);
    }
}
