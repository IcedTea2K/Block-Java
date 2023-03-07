package model;

import except.InvalidArgumentException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public abstract class OperatorTest {
    @BeforeEach
    public abstract void setup();

    @Test
    public void testToJson() {
        Command testCommand = new Add();
        DataType operandOne = new DataType(10);
        DataType operandTwo = new DataType(200);
        try {
            testCommand.input(operandOne, operandTwo);
        } catch (InvalidArgumentException e) {
            fail("No exception should be raised.");
        }

        String expectedJson = "{\n" +
                "\t\"0\" : [\n" +
                "\t\t\"command\" : \"ADD\",\n" +
                "\t\t\"operandOne\" : \"10\",\n" +
                "\t\t\"operandTwo\" : \"200\"\n" +
                "\t]\n" +
                "}";
        assertEquals(expectedJson, testCommand.toJson(0));
    }

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
