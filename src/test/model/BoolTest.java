package model;

import except.InvalidArgumentException;
import except.InvalidReturnTypeException;
import except.MissingArgumentException;
import except.NotYetExecutedException;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public abstract class BoolTest extends OperatorTest {
    protected Bool command;
    protected DataType trueVal;
    protected DataType falseVal;

    @Override
    public void testInputWrongType() {
        DataType tempNum = new DataType(12);
        DataType tempStream = new DataType(new ArrayList<DataType>());

        try {
            command.input(tempNum, tempStream);
            fail("InvalidArgumentException should have been raised");
        } catch (InvalidArgumentException e) {
            assertEquals("except.WrongArgumentTypeException: " +
                    "Expecting boolean Received number", e.toString());
        }

        try {
            command.input(tempStream, tempNum);
            fail("InvalidArgumentException should have been raised");
        } catch (InvalidArgumentException e) {
            assertEquals("except.WrongArgumentTypeException: " +
                    "Expecting boolean Received data stream", e.toString());
        }
    }

    @Override
    public void testInputTwoOperands() {
        List<DataType> givenInputs = new ArrayList<>();
        try {
            command.input(trueVal, falseVal);
            givenInputs = command.getInputs();
        } catch (InvalidArgumentException | MissingArgumentException e) {
            fail("no exception should be raised");
        }

        assertEquals(2, givenInputs.size());
        assertEquals(trueVal, givenInputs.get(0));
        assertEquals(falseVal, givenInputs.get(1));
    }

    @Override
    public void testGetConstraints() {
        assertEquals("Operators only accept two inputs. They both need to be boolean.",
                command.getConstraints());
    }

    @Override
    public void testGetReturnType() {
        assertEquals("Boolean", command.getReturnType());
    }

    @Override
    public void testToString() {
        try {
            command.input(falseVal, trueVal);
            assertEquals(command.getHeader().split(" ")[0] + " "
                    + falseVal.getBoolean() + " " + trueVal.getBoolean(), command.toString());
        } catch (InvalidArgumentException e) {
            fail("No exception should be raised");
        }
    }

    protected void checkBehaviour(DataType operandOne, DataType operandTwo, boolean expectedResult) {
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
