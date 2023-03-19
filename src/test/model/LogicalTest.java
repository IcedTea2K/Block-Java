package model;

import except.InvalidArgumentException;
import except.InvalidReturnTypeException;
import except.MissingArgumentException;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public abstract class LogicalTest extends BoolTest {
    DataType posNum;
    DataType negNum;
    DataType zero;
    @Override
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
    public void testGetConstraints() {
        assertEquals("Operators only accept two inputs. They both need to be numbers.",
                command.getConstraints());
    }

    @Override
    public void testToString() {
        try {
            command.input(negNum, zero);
            assertEquals(command.getHeader().split(" ")[0] + " "
                    + negNum.getNumber() + " " + zero.getNumber(), command.toString());
        } catch (InvalidArgumentException | InvalidReturnTypeException e) {
            fail("No exception should be raised");
        }
    }
}
