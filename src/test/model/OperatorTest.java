package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public abstract class OperatorTest {
    @BeforeEach
    public abstract void setup();

    @Test
    public abstract void testTooFewInputs();

    @Test
    public abstract void testTooManyInputs();

    @Test
    public abstract void testInputWrongType();

    @Test
    public abstract void testInputTwoOperands();

    @Test
    public abstract void testGetInputWithNoInputs();

    @Test
    public abstract void testGetResultsWithoutExecution();

    @Test
    public abstract void testExecuteWithoutInputs();

    @Test
    public abstract void testGetConstraints();

    @Test
    public abstract void testGetReturnType();

    @Test
    public abstract void testGetJavaWithNoInputs();

    @Test
    public abstract void testToString();

    @Test
    public abstract void testGetExamples();

    @Test
    public abstract void testGetJavaWithInputs();

    @Test
    public abstract void testGetHeader();

    // EFFECTS: add two numbers together and compare to the expected result
    protected abstract void checkBehaviour(DataType operandOne, DataType operandTwo, int expectedResult);
}
