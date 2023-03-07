package model;

import except.InvalidArgumentException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

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
                "    \"operandOne\": \"10\",\n" +
                "    \"operandTwo\": \"200\",\n" +
                "    \"command\": \"ADD\"\n" +
                "}";
        assertEquals(expectedJson, testCommand.toJson().toString(4));
    }

    @Test
    public void testUnequal() {
        Command testCommandOne = new Add();
        Command testCommandTwo = new Subtract();
        Command testCommandThree = new Add();
        DataType operandOne = new DataType(10);
        DataType operandTwo = new DataType(200);
        DataType extraOperand = new DataType(42069);
        try {
            testCommandOne.input(operandOne, operandTwo);
            testCommandTwo.input(operandOne, operandTwo);
            testCommandThree.input(operandOne, extraOperand);
        } catch (InvalidArgumentException e) {
            fail("No exception should be raised.");
        }

        assertFalse(testCommandOne.equals(null));
        assertFalse(testCommandOne.equals(testCommandTwo));
        assertFalse(testCommandTwo.equals(testCommandThree));
        assertFalse(testCommandOne.equals(testCommandThree));
    }

    @Test void testEqual() {
        Command testCommandOne = new Add();
        Command testCommandTwo = new Add();
        Command testCommandThree = new Add();
        DataType operandOne = new DataType(10);
        DataType operandTwo = new DataType(200);
        try {
            testCommandOne.input(operandOne, operandTwo);
            testCommandTwo.input(operandOne, operandTwo);
            testCommandThree.input(operandOne, operandTwo);
        } catch (InvalidArgumentException e) {
            fail("No exception should be raised.");
        }

        assertTrue(testCommandOne.equals(testCommandOne)); // reflexive
        assertTrue(testCommandOne.equals(testCommandTwo)); // symmetric
        assertTrue(testCommandTwo.equals(testCommandOne));
        assertTrue(testCommandTwo.equals(testCommandOne)); // Consistency
        assertTrue(testCommandTwo.equals(testCommandThree)); // transitive
        assertTrue(testCommandOne.equals(testCommandThree));
    }

    @Test void testHash() {
        Command testCommandOne = new Add();
        Command testCommandTwo = new Subtract();
        Command testCommandThree = new Add();
        DataType operandOne = new DataType(10);
        DataType operandTwo = new DataType(200);
        DataType extraOperand = new DataType(42069);
        try {
            testCommandOne.input(operandOne, operandTwo);
            testCommandTwo.input(operandOne, operandTwo);
            testCommandThree.input(extraOperand, operandTwo);
        } catch (InvalidArgumentException e) {
            fail("No exception should be raised.");
        }
        assertEquals(3306370, testCommandOne.hashCode());
        assertEquals(5122140, testCommandTwo.hashCode());
        assertEquals(3348429, testCommandThree.hashCode());
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
