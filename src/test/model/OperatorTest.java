package model;

import except.InvalidArgumentException;
import except.MissingArgumentException;
import except.NotYetExecutedException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public abstract class OperatorTest {
    protected Operator command;

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
        DataType similarOne = new DataType(10);
        try {
            testCommandOne.input(operandOne, operandTwo);
            testCommandTwo.input(operandOne, operandTwo);
            testCommandThree.input(similarOne, operandTwo);
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
        assertEquals(215161, testCommandOne.hashCode());
        assertEquals(215161, testCommandTwo.hashCode());
        assertEquals(547823341, testCommandThree.hashCode());
    }

    @Test
    public void testTooFewInputs() {
        try {
            command.input();
            fail("InvalidArgumentException for too few arguments should have been raised");
        } catch (InvalidArgumentException e) {
            assertEquals("except.UnexpectedNumberOfArgumentsException:" +
                    " Expecting 2 Received 0", e.toString());
        }

        try {
            command.input(new DataType(2));
            fail("InvalidArgumentException for too few arguments should have been raised");
        } catch (InvalidArgumentException e) {
            assertEquals("except.UnexpectedNumberOfArgumentsException:" +
                    " Expecting 2 Received 1", e.toString());
        }
    }

    @Test
    public void testTooManyInputs() {
        try {
            command.input(new DataType(true), new DataType(10), new DataType(false)) ;
            fail("InvalidArgumentException for too many arguments should have been raised");
        } catch (InvalidArgumentException e) {
            assertEquals("except.UnexpectedNumberOfArgumentsException: " +
                    "Expecting 2 Received 3", e.toString());
        }
    }

    @Test
    public abstract void testInputWrongType();

    @Test
    public abstract void testInputTwoOperands();

    @Test
    public void testGetInputWithNoInputs() {
        try {
            command.getInputs();
            fail("MissingArgumentException should have been raised");
        } catch (MissingArgumentException e) {
            assertEquals("except.MissingArgumentException: " +
                    "No argument has been given", e.toString());
        }
    }

    @Test
    public void testGetResultsWithoutExecution() {
        try {
            command.getResult();
            fail("NotYetExecutedException should have been raised");
        } catch (NotYetExecutedException e) {
            assertEquals("except.NotYetExecutedException: " +
                    "Unable to obtain the result. The command has not been executed yet", e.toString());
        }
    }

    @Test
    public void testExecuteWithoutInputs() {
        try {
            command.execute();
            fail("MissingArgumentException should have been raised");
        } catch (MissingArgumentException e) {
            assertEquals("except.MissingArgumentException: " +
                    "No argument has been given", e.toString());
        }
    }

    @Test
    public abstract void testGetConstraints();

    @Test
    public abstract void testGetReturnType();

    @Test
    public void testGetJavaWithNoInputs() {
        try {
            command.getJava(1);
            fail("MissingArgumentException should have been raised");
        } catch (MissingArgumentException e) {
            assertEquals("except.MissingArgumentException: " +
                    "No argument has been given", e.toString());
        }
    }

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
