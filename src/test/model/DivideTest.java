package model;

import except.InvalidArgumentException;
import except.InvalidReturnTypeException;
import except.MissingArgumentException;
import except.NotYetExecutedException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class DivideTest {
    private Divide divideCommand;
    private DataType posNum;
    private DataType negNum;
    private DataType zero;

    @BeforeEach
    public void setup() {
        divideCommand = new Divide();
        posNum = new DataType(123);
        negNum = new DataType(-11);
        zero = new DataType(0);
    }

    @Test
    public void testTooFewInputs() {
        try {
            divideCommand.input();
            fail("InvalidArgumentException for too few arguments should have been raised");
        } catch (InvalidArgumentException e) {
            assertEquals("Too few arguments:\n"
                    + "Expecting 2 Received 0", e.getMessage());
        }

        try {
            divideCommand.input(posNum);
            fail("InvalidArgumentException for too few arguments should have been raised");
        } catch (InvalidArgumentException e) {
            assertEquals("Too few arguments:\n"
                    + "Expecting 2 Received 1", e.getMessage());
        }
    }

    @Test
    public void testTooManyInputs() {
        try {
            divideCommand.input(negNum, posNum, zero) ;
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
            divideCommand.input(tempBool, tempDS);
            fail("InvalidArgumentException should have been raised");
        } catch (InvalidArgumentException e) {
            assertEquals("Invalid argument type:\nExpecting number Received boolean", e.getMessage());
        }

        try {
            divideCommand.input(tempDS, tempBool);
            fail("InvalidArgumentException should have been raised");
        } catch (InvalidArgumentException e) {
            assertEquals("Invalid argument type:\nExpecting number Received data stream", e.getMessage());
        }
    }

    @Test
    public void testInputTwoNumbers() {
        List<DataType> givenInputs = new ArrayList<>();
        try {
            divideCommand.input(posNum, negNum);
            givenInputs = divideCommand.getInputs();
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
            divideCommand.getInputs();
            fail("MissingArgumentException should have been raised");
        } catch (MissingArgumentException e) {
            // pass the test
        }
    }

    @Test
    public void testGetResultsWithoutExecution() {
        try {
            divideCommand.getResult();
            fail("NotYetExecutedException should have been raised");
        } catch (NotYetExecutedException e) {
            // pass the test
        }
    }

    @Test
    public void testExecuteWithoutInputs() {
        try {
            divideCommand.execute();
            fail("MissingArgumentException should have been raised");
        } catch (MissingArgumentException e) {
            // The test passes
        }
    }

    @Test
    public void testExecutePosPos() {
        checkBehaviour(posNum, posNum, 123 + 123);
    }

    @Test
    public void testExecutePosNeg() {
        checkBehaviour(posNum, negNum, 123 -11);
    }

    @Test
    public void testExecuteNegNeg() {
        checkBehaviour(negNum, negNum, -11 -11);
    }

    @Test
    public void testExecutePosZero() {
        checkBehaviour(posNum, zero, 123);
    }

    @Test
    public void testExecuteZeroNeg() {
        checkBehaviour(zero, negNum, -11);
    }

    @Test
    public void testExecuteZeroZero() {
        checkBehaviour(zero, zero, 0);
    }

    @Test
    public void testGetConstraints() {
        assertEquals("Operators only accept two inputs. They both need to be numbers.",
                divideCommand.getConstraints());
    }

    @Test
    public void testGetReturnType() {
        assertEquals("Number", divideCommand.getReturnType());
    }

    @Test
    public void testGetExamples() {
        String msg = "ADD 11 821\n" +
                "ADD 21.4 10\n" +
                "ADD -3 -10";
        assertEquals(msg, divideCommand.getExamples());
    }

    @Test
    public void testGetJavaWithNoInputs() {
        try {
            divideCommand.getJava();
            fail("MissingArgumentException should have been raised");
        } catch (MissingArgumentException e) {
            // pass the test
        }
    }

    @Test
    public void testGetJavaWithInputs() {
        checkBehaviour(negNum, posNum, -11 + 123);
        String msg = "int result = -11 + 123;";
        try {
            assertEquals(msg, divideCommand.getJava());
        } catch (MissingArgumentException e) {
            fail("No exception should be raised");
        }
    }

    @Test
    // EFFECTS: add two numbers together and comapre to the expected result
    private void checkBehaviour(DataType numOne, DataType numTwo, int expectedResult) {
        try {
            divideCommand.input(numOne, numTwo);
            divideCommand.execute();
            DataType result = divideCommand.getResult();
            assertEquals(expectedResult, result.getNumber());
        } catch (InvalidArgumentException | MissingArgumentException | NotYetExecutedException |
                 InvalidReturnTypeException e) {
            fail("no exception should be raised");
        }
    }
}
