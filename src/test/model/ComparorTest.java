package model;

import except.InvalidArgumentException;
import except.InvalidReturnTypeException;
import except.MissingArgumentException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public abstract class ComparorTest extends BoolTest {
    protected DataType negNum;
    protected DataType posNum;
    protected DataType zero;

    @Override
    @BeforeEach
    public void setup() {
        super.setup();
        this.negNum = new DataType(-11);
        this.posNum = new DataType(34);
        this.zero = new DataType(0);
        command = new Larger();
    }

    @Override
    @Test
    public void testInputWrongType() {
        DataType tempBool = new DataType(true);
        DataType tempDS = new DataType(new ArrayList<DataType>());
        DataType tempNum = new DataType(10);
        try {
            command.input(tempBool, tempDS);
            fail("InvalidArgumentException should have been raised");
        } catch (InvalidArgumentException e) {
            assertEquals("except.WrongArgumentTypeException: " +
                    "Expecting number Received boolean", e.toString());
        }

        try {
            command.input(tempDS, tempNum);
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
            command.input(negNum, posNum);
            givenInputs = command.getInputs();
        } catch (InvalidArgumentException | MissingArgumentException e) {
            fail("no exception should be raised");
        }

        assertEquals(2, givenInputs.size());
        assertEquals(negNum, givenInputs.get(0));
        assertEquals(posNum, givenInputs.get(1));
    }

    @Override
    @Test
    public void testGetConstraints() {
        assertEquals("Operators only accept two inputs. They both need to be numbers.",
                command.getConstraints());
    }

    @Override
    @Test
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
