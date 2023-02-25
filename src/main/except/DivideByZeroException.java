package except;

// Exception for when the user tries to divide by 0
public class DivideByZeroException extends InvalidArgumentException {
    public DivideByZeroException() {
        super("The denominator cannot be zero");
    }
}
