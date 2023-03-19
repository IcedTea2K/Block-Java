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
    public void testGetConstraints() {
        assertEquals("Operators only accept two inputs. They both need to be boolean.",
                command.getConstraints());
    }

    @Override
    public void testGetReturnType() {
        assertEquals("Boolean", command.getReturnType());
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
