package model;

import except.MissingArgumentException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class AddTest extends ArithmeticTest {
    @Override
    @BeforeEach
    public void setup() {
        command = new Add();
        posNum = new DataType(123);
        negNum = new DataType(-11);
        zero = new DataType(0);
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

    @Override
    @Test
    public void testGetExamples() {
        String msg = "ADD 11 821\n" +
                "ADD 21.4 10\n" +
                "ADD -3 -10";
        assertEquals(msg, command.getExamples());
    }

    @Override
    @Test
    public void testGetJavaWithInputs() {
        checkBehaviour(negNum, posNum, -11 + 123);
        String msg = "int result = -11 + 123;";
        try {
            assertEquals(msg, command.getJava());
        } catch (MissingArgumentException e) {
            fail("No exception should be raised");
        }
    }
}
