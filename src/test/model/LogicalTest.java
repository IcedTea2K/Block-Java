package model;

import except.InvalidArgumentException;
import except.InvalidReturnTypeException;
import except.MissingArgumentException;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public abstract class LogicalTest extends BoolTest {
    @Override
    @Test
    public void testInputWrongType() {
        DataType tempBool = new DataType(true);
        DataType tempDS = new DataType(new ArrayList<DataType>());
        DataType tempNum = new DataType(10);
        try {
            command.input(tempNum, tempDS);
            fail("InvalidArgumentException should have been raised");
        } catch (InvalidArgumentException e) {
            assertEquals("except.WrongArgumentTypeException: " +
                    "Expecting boolean Received number", e.toString());
        }

        try {
            command.input(tempDS, tempBool);
            fail("InvalidArgumentException should have been raised");
        } catch (InvalidArgumentException e) {
            assertEquals("except.WrongArgumentTypeException: " +
                    "Expecting boolean Received data stream", e.toString());
        }
    }

    @Override
    @Test
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
    @Test
    public void testGetConstraints() {
        assertEquals("Operators only accept two inputs. They both need to be booleans.",
                command.getConstraints());
    }

    @Override
    @Test
    public void testToString() {
        try {
            command.input(falseVal, trueVal);
            assertEquals(command.getHeader().split(" ")[0] + " "
                    + falseVal.getBoolean() + " " + trueVal.getBoolean(), command.toString());
        } catch (InvalidArgumentException | InvalidReturnTypeException e) {
            fail("No exception should be raised");
        }
    }
}
