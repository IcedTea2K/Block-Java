package except;

// Exception for when user's input to a command is invalid
public class InvalidArgumentException extends Exception {
    // EFFECTS: construct exception due to invalid number of inputs
    public InvalidArgumentException(int numberOfInputs, int expectedInputs) {
        super((numberOfInputs < expectedInputs ? "Too few arguments:\n" : "Exceeds the number of arguments:\n")
            + "Expecting " + expectedInputs + " Received " + numberOfInputs);
    }

    // EFFECTS: construct exception due to the invalid argument type
    public InvalidArgumentException(String givenType, String expectedType) {
        super("Invalid argument type:\n"
                + "Expecting " + expectedType + "received " + givenType);
    }
}
