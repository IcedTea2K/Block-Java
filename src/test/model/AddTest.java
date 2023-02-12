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

public class AddTest {
    private Add addCommand;
    private DataType posNum;
    private DataType negNum;
    private DataType zero;

    @BeforeEach
    public void setup() {
       addCommand = new Add();
       posNum = new DataType(123);
       negNum = new DataType(-11);
       zero = new DataType(0);
    }

    @Test
    public void testTooFewInputs() {
        try {
            addCommand.input();
            fail();
        } catch (InvalidArgumentException e) {
            assertEquals("Too few arguments:\n"
                    + "Expecting 2 received 0", e.getMessage());
        }

        try {
            addCommand.input(posNum);
            fail();
        } catch (InvalidArgumentException e) {
            assertEquals("Too few arguments:\n"
                    + "Expecting 2 received 1", e.getMessage());
        }
    }

    @Test
    public void testTooManyInputs() {
        try {
            for (int i = 0; i < 10; i++) {
                addCommand.input(new DataType(i));
            }
            fail();
        } catch (InvalidArgumentException e) {
            assertEquals("Exceeds the number of arguments:\n"
                    + "Expecting 2 received 10", e.getMessage());
        }
    }

    @Test
    public void testInputTwoNumbers() {
        List<DataType> givenInputs = new ArrayList<>();
        try {
            addCommand.input(posNum, negNum);
            givenInputs = addCommand.getInputs();
        } catch (InvalidArgumentException | MissingArgumentException e) {
            fail("no exception should be raised");
        }

        assertEquals(2, givenInputs.size());
        assertEquals(posNum, givenInputs.get(0));
        assertEquals(posNum, givenInputs.get(1));
    }

    @Test
    public void testGetInputWithNoInputs() {
        try {
            addCommand.getInputs();
            fail("MissingArgumentException should have been raised");
        } catch (MissingArgumentException e) {
            // pass the test
        }
    }

    @Test
    public void testGetResultsWithoutExecution() {
        try {
            addCommand.getResult();
            fail("NotYetExecutedException should have been raised");
        } catch (NotYetExecutedException e) {
            // pass the test
        }
    }

    @Test
    public void testExecuteWithoutInputs() {
        try {
            addCommand.execute();
            fail();
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
        assertEquals("Operators only accept two inputs. They both need to be numbers",
                addCommand.getConstraints());
    }

    @Test
    public void testGetReturnType() {
        assertEquals("Number", addCommand.getReturnType());
    }

    @Test
    public void testGetExamples() {
        String msg = "ADD 11 821\n" +
                "ADD 21.4 10" +
                "ADD -3 -10";
        assertEquals(msg, addCommand.getExamples());
    }

    @Test
    public void testGetJava() {

    }

    @Test
    // EFFECTS: add two numbers together and comapre to the expected result
    private void checkBehaviour(DataType numOne, DataType numTwo, int expectedResult) {
        try {
            addCommand.input(numOne, numTwo);
            addCommand.execute();
            DataType result = addCommand.getResult();
            assertEquals(expectedResult, result.getNumber());
        } catch (InvalidArgumentException | MissingArgumentException | NotYetExecutedException |
                 InvalidReturnTypeException e) {
            fail("no exception should be raised");
        }
    }
}
