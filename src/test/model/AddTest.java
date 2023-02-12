package model;

import except.InvalidArgumentException;
import except.InvalidReturnTypeException;
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
        } catch (InvalidArgumentException e) {
            fail("no exception should be raised");
        }

        assertEquals(2, givenInputs.size());
        assertEquals(posNum, givenInputs.get(0));
        assertEquals(posNum, givenInputs.get(1));
    }

    @Test
    public void testExecutePosPos() {
        try {
            addCommand.input(posNum, posNum);
        } catch (InvalidArgumentException e) {
            fail("no exception should be raised");
        }

        addCommand.execute();
        DataType result = addCommand.getResult();
        try {
            assertEquals(123 + 123, result.getNumber());
        } catch (InvalidReturnTypeException e) {
            fail("no exception should be raised");
        }
    }

    @Test
    public void testExecutePosNeg() {
        try {
            addCommand.input(posNum, negNum);
        } catch (InvalidArgumentException e) {
            fail("no exception should be raised");
        }

        addCommand.execute();
        DataType result = addCommand.getResult();
        try {
            assertEquals(123 - 11, result.getNumber());
        } catch (InvalidReturnTypeException e) {
            fail("no exception should be raised");
        }
    }

    @Test
    public void testExecuteNegNeg() {
        try {
            addCommand.input(negNum, negNum);
        } catch (InvalidArgumentException e) {
            fail("no exception should be raised");
        }

        addCommand.execute();
        DataType result = addCommand.getResult();
        try {
            assertEquals(-11 - 11, result.getNumber());
        } catch (InvalidReturnTypeException e) {
            fail("no exception should be raised");
        }
    }

    @Test
    public void testExecutePosZero() {
        try {
            addCommand.input(posNum, zero);
        } catch (InvalidArgumentException e) {
            fail("no exception should be raised");
        }

        addCommand.execute();
        DataType result = addCommand.getResult();
        try {
            assertEquals(123, result.getNumber());
        } catch (InvalidReturnTypeException e) {
            fail("no exception should be raised");
        }
    }

    @Test
    public void testExecuteZeroNeg() {
        try {
            addCommand.input(zero, negNum);
        } catch (InvalidArgumentException e) {
            fail("no exception should be raised");
        }

        addCommand.execute();
        DataType result = addCommand.getResult();
        try {
            assertEquals(-11, result.getNumber());
        } catch (InvalidReturnTypeException e) {
            fail("no exception should be raised");
        }
    }

    @Test
    public void testExecuteZeroZero() {
        try {
            addCommand.input(zero, zero);
        } catch (InvalidArgumentException e) {
            fail("no exception should be raised");
        }

        addCommand.execute();
        DataType result = addCommand.getResult();
        try {
            assertEquals(0, result.getNumber());
        } catch (InvalidReturnTypeException e) {
            fail("no exception should be raised");
        }
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
}
