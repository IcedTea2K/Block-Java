package model;

import except.InvalidArgumentException;
import except.InvalidReturnTypeException;
import except.MissingArgumentException;
import except.NotYetExecutedException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class ArithmeticTest {
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
            fail("InvalidArgumentException for too few arguments should have been raised");
        } catch (InvalidArgumentException e) {
            assertEquals("except.UnexpectedNumberOfArgumentsException:" +
                    " Expecting 2 Received 0", e.toString());
        }

        try {
            addCommand.input(posNum);
            fail("InvalidArgumentException for too few arguments should have been raised");
        } catch (InvalidArgumentException e) {
            assertEquals("except.UnexpectedNumberOfArgumentsException:" +
                    " Expecting 2 Received 1", e.toString());
        }
    }

    @Test
    public void testTooManyInputs() {
        try {
            addCommand.input(negNum, posNum, zero) ;
            fail("InvalidArgumentException for too many arguments should have been raised");
        } catch (InvalidArgumentException e) {
            assertEquals("except.UnexpectedNumberOfArgumentsException: " +
                    "Expecting 2 Received 3", e.toString());
        }
    }

    @Test
    public void testInputWrongType() {
        DataType tempBool = new DataType(true);
        DataType tempDS = new DataType(new ArrayList<DataType>());
        try {
            addCommand.input(tempBool, tempDS);
            fail("InvalidArgumentException should have been raised");
        } catch (InvalidArgumentException e) {
            assertEquals("except.WrongArgumentTypeException: " +
                    "Expecting number Received boolean", e.toString());
        }

        try {
            addCommand.input(tempDS, tempBool);
            fail("InvalidArgumentException should have been raised");
        } catch (InvalidArgumentException e) {
            assertEquals("except.WrongArgumentTypeException: " +
                    "Expecting number Received data stream", e.toString());
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
        assertEquals(negNum, givenInputs.get(1));
    }

    @Test
    public void testGetInputWithNoInputs() {
        try {
            addCommand.getInputs();
            fail("MissingArgumentException should have been raised");
        } catch (MissingArgumentException e) {
            assertEquals("except.MissingArgumentException: " +
                    "No argument has been given", e.toString());
        }
    }

    @Test
    public void testGetResultsWithoutExecution() {
        try {
            addCommand.getResult();
            fail("NotYetExecutedException should have been raised");
        } catch (NotYetExecutedException e) {
            assertEquals("except.NotYetExecutedException: " +
                    "Unable to obtain the result. The command has not been executed yet", e.toString());
        }
    }

    @Test
    public void testExecuteWithoutInputs() {
        try {
            addCommand.execute();
            fail("MissingArgumentException should have been raised");
        } catch (MissingArgumentException e) {
            assertEquals("except.MissingArgumentException: " +
                    "No argument has been given", e.toString());
        }
    }

    @Test
    public void testGetConstraints() {
        assertEquals("Operators only accept two inputs. They both need to be numbers.",
                addCommand.getConstraints());
    }

    @Test
    public void testGetReturnType() {
        assertEquals("Number", addCommand.getReturnType());
    }

    @Test
    public void testGetJavaWithNoInputs() {
        try {
            addCommand.getJava();
            fail("MissingArgumentException should have been raised");
        } catch (MissingArgumentException e) {
            assertEquals("except.MissingArgumentException: " +
                    "No argument has been given", e.toString());
        }
    }

    // EFFECTS: add two numbers together and compare to the expected result
    protected void checkBehaviour(DataType numOne, DataType numTwo, int expectedResult) {
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
