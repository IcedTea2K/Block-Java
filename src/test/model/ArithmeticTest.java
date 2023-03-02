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

public abstract class ArithmeticTest extends OperatorTest {
    protected Arithmetic command;
    protected DataType posNum;
    protected DataType negNum;
    protected DataType zero;

    @Override
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
            command.input(posNum);
            fail("InvalidArgumentException for too few arguments should have been raised");
        } catch (InvalidArgumentException e) {
            assertEquals("except.UnexpectedNumberOfArgumentsException:" +
                    " Expecting 2 Received 1", e.toString());
        }
    }

    @Override
    @Test
    public void testTooManyInputs() {
        try {
            command.input(negNum, posNum, zero) ;
            fail("InvalidArgumentException for too many arguments should have been raised");
        } catch (InvalidArgumentException e) {
            assertEquals("except.UnexpectedNumberOfArgumentsException: " +
                    "Expecting 2 Received 3", e.toString());
        }
    }

    @Override
    @Test
    public void testInputWrongType() {
        DataType tempBool = new DataType(true);
        DataType tempDS = new DataType(new ArrayList<DataType>());
        try {
            command.input(tempBool, tempDS);
            fail("InvalidArgumentException should have been raised");
        } catch (InvalidArgumentException e) {
            assertEquals("except.WrongArgumentTypeException: " +
                    "Expecting number Received boolean", e.toString());
        }

        try {
            command.input(tempDS, tempBool);
            fail("InvalidArgumentException should have been raised");
        } catch (InvalidArgumentException e) {
            assertEquals("except.WrongArgumentTypeException: " +
                    "Expecting number Received data stream", e.toString());
        }
    }

    @Override
    @Test
    public void testInputTwoOperands() {
        List<DataType> givenInputs = new ArrayList<>();
        try {
            command.input(posNum, negNum);
            givenInputs = command.getInputs();
        } catch (InvalidArgumentException | MissingArgumentException e) {
            fail("no exception should be raised");
        }

        assertEquals(2, givenInputs.size());
        assertEquals(posNum, givenInputs.get(0));
        assertEquals(negNum, givenInputs.get(1));
    }

    @Override
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

    @Override
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

    @Override
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

    @Override
    @Test
    public void testGetConstraints() {
        assertEquals("Operators only accept two inputs. They both need to be numbers.",
                command.getConstraints());
    }

    @Override
    @Test
    public void testGetReturnType() {
        assertEquals("Number", command.getReturnType());
    }

    @Override
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

    @Override
    @Test
    public void testToString() {
        try {
            command.input(negNum, posNum);
            assertEquals(command.getHeader().split(" ")[0] + " "
                    + negNum.getNumber() + " " + posNum.getNumber(), command.toString());
        } catch (InvalidArgumentException | InvalidReturnTypeException e) {
            fail("No exception should be raised");
        }
    }

    // EFFECTS: add two numbers together and compare to the expected result
    @Override
    protected void checkBehaviour(DataType operandOne, DataType operandTwo, int expectedResult) {
        try {
            command.input(operandOne, operandTwo);
            command.execute();
            DataType result = command.getResult();
            assertEquals(expectedResult, result.getNumber());
        } catch (InvalidArgumentException | MissingArgumentException | NotYetExecutedException |
                 InvalidReturnTypeException e) {
            fail("no exception should be raised");
        }
    }
}
