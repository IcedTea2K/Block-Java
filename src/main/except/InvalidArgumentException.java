package except;

// Exception for when user's input to a command is invalid
public class InvalidArgumentException extends Exception {
    // EFFECTS: construct exception due to the exceeding number of arguments
    public InvalidArgumentException(int numberOfInputs) {
        super("Exceeds the number of arguments:\n "
                + "Expecting 2 Received " + Integer.toString(numberOfInputs));
    }

    // EFFECTS: construct exception due to the invalid argument type
    public InvalidArgumentException(String givenType, String expectedType) {
        super("Invalid argument type: \n "
                + "Expecting " + expectedType + "received " + givenType);
    }
}
