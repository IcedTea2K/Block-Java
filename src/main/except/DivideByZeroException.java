package except;

public class DivideByZeroException extends InvalidArgumentException {
    public DivideByZeroException() {
        super("The denominator cannot be zero");
    }
}
