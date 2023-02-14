package model;

import except.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class SubtractTest {
    private Subtract subtractCommand;
    private DataType posNum;
    private DataType negNum;
    private DataType zero;

    @BeforeEach
    public void setup() {
        subtractCommand = new Subtract();
        posNum = new DataType(123);
        negNum = new DataType(-11);
        zero = new DataType(0);
    }

    @Test
    public void testTooFewInputs() {
        try {
            subtractCommand.input();
            fail("InvalidArgumentException for too few arguments should have been raised");
        } catch (InvalidArgumentException e) {
            assertEquals("Too few arguments:\n"
                    + "Expecting 2 Received 0", e.getMessage());
        }

        try {
            subtractCommand.input(posNum);
            fail("InvalidArgumentException for too few arguments should have been raised");
        } catch (InvalidArgumentException e) {
            assertEquals("Too few arguments:\n"
                    + "Expecting 2 Received 1", e.getMessage());
        }
    }

    @Test
    public void testTooManyInputs() {
        try {
            subtractCommand.input(negNum, posNum, zero) ;
            fail("InvalidArgumentException for too many arguments should have been raised");
        } catch (InvalidArgumentException e) {
            assertEquals("Exceeds the number of arguments:\n"
                    + "Expecting 2 Received 3", e.getMessage());
        }
    }

    @Test
    public void testInputWrongType() {
        DataType tempBool = new DataType(true);
        DataType tempDS = new DataType(new ArrayList<DataType>());
        try {
            subtractCommand.input(tempBool, tempDS);
            fail("InvalidArgumentException should have been raised");
        } catch (InvalidArgumentException e) {
            assertEquals("Invalid argument type:\nExpecting number Received boolean", e.getMessage());
        }

        try {
            subtractCommand.input(tempDS, tempBool);
            fail("InvalidArgumentException should have been raised");
        } catch (InvalidArgumentException e) {
            assertEquals("Invalid argument type:\nExpecting number Received data stream", e.getMessage());
        }
    }

    @Test
    public void testInputTwoNumbers() {
        List<DataType> givenInputs = new ArrayList<>();
        try {
            subtractCommand.input(posNum, negNum);
            givenInputs = subtractCommand.getInputs();
        } catch (InvalidArgumentException | MissingArgumentException e) {
            fail("no exception should be raised");
        }

        assertEquals(2, givenInputs.size());
        assertEquals(posNum, givenInputs.get(0));
        assertEquals(negNum, givenInputs.get(1));
    }

    @Test
    public void testGetInputWithNoInputs() {
        try {
            subtractCommand.getInputs();
            fail("MissingArgumentException should have been raised");
        } catch (MissingArgumentException e) {
            // pass the test
        }
    }

    @Test
    public void testGetResultsWithoutExecution() {
        try {
            subtractCommand.getResult();
            fail("NotYetExecutedException should have been raised");
        } catch (NotYetExecutedException e) {
            // pass the test
        }
    }

    @Test
    public void testExecuteWithoutInputs() {
        try {
            subtractCommand.execute();
            fail("MissingArgumentException should have been raised");
        } catch (MissingArgumentException e) {
            // The test passes
        }
    }

    @Test
    public void testExecutePosPos() {
        checkBehaviour(posNum, posNum, 123 - 123);
    }

    @Test
    public void testExecutePosNeg() {
        checkBehaviour(posNum, negNum, 123 + 11);
    }

    @Test
    public void testExecuteNegPos() {
        checkBehaviour(negNum, posNum, -11 - 123);
    }

    @Test
    public void testExecuteNegNeg() {
        checkBehaviour(negNum, negNum, -11 + 11);
    }

    @Test
    public void testExecutePosZero() {
        checkBehaviour(posNum, zero, 123);
    }

    @Test
    public void testExecuteZeroNeg() {
        checkBehaviour(zero, negNum, 11);
    }

    @Test
    public void testExecuteNegZero() {
        checkBehaviour(negNum, zero, -11);
    }

    @Test
    public void testExecuteZeroZero() {
        checkBehaviour(zero, zero, 0);
    }

    @Test
    public void testGetConstraints() {
        assertEquals("Operators only accept two inputs. They both need to be numbers.",
                subtractCommand.getConstraints());
    }

    @Test
    public void testGetReturnType() {
        assertEquals("Number", subtractCommand.getReturnType());
    }

    @Test
    public void testGetExamples() {
        String msg = "SUB 1337 89\n" +
                "SUB 28.1 39\n" +
                "SUB 38 -10";
        assertEquals(msg, subtractCommand.getExamples());
    }

    @Test
    public void testGetJavaWithNoInputs() {
        try {
            subtractCommand.getJava();
            fail("MissingArgumentException should have been raised");
        } catch (MissingArgumentException e) {
            // pass the test
        }
    }

    @Test
    public void testGetJavaWithInputs() {
        checkBehaviour(negNum, posNum, -11 - 123);
        String msg = "int result = -11 - 123;";
        try {
            assertEquals(msg, subtractCommand.getJava());
        } catch (MissingArgumentException e) {
            fail("No exception should be raised");
        }
    }

    @Test
    // EFFECTS: add two numbers together and compare to the expected result
    private void checkBehaviour(DataType numOne, DataType numTwo, int expectedResult) {
        try {
            subtractCommand.input(numOne, numTwo);
            subtractCommand.execute();
            DataType result = subtractCommand.getResult();
            assertEquals(expectedResult, result.getNumber());
        } catch (InvalidArgumentException | MissingArgumentException | NotYetExecutedException |
                 InvalidReturnTypeException e) {
            fail("no exception should be raised");
        }
    }
}
