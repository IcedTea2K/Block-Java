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

public class MultiplyTest extends ArithmeticTest {
    @Override
    @BeforeEach
    public void setup() {
        command = new Multiply();
        posNum = new DataType(123);
        negNum = new DataType(-11);
        zero = new DataType(0);
    }

    @Test
    public void testExecutePosPos() {
        checkBehaviour(posNum, posNum, 123 * 123);
    }

    @Test
    public void testExecutePosNeg() {
        checkBehaviour(posNum, negNum, 123 * -11);
    }

    @Test
    public void testExecuteNegNeg() {
        checkBehaviour(negNum, negNum, -11 * -11);
    }

    @Test
    public void testExecutePosZero() {
        checkBehaviour(posNum, zero, 0);
    }

    @Test
    public void testExecuteZeroNeg() {
        checkBehaviour(zero, negNum, 0);
    }

    @Test
    public void testExecuteZeroZero() {
        checkBehaviour(zero, zero, 0);
    }

    @Override
    @Test
    public void testGetExamples() {
        String msg = "MUL 12 29\n" +
                "MUL 83.1 -12\n" +
                "MUL -31 -78";
        assertEquals(msg, command.getExamples());
    }

    @Override
    @Test
    public void testGetJavaWithInputs() {
        checkBehaviour(negNum, posNum, -11 * 123);
        String msg = "int result1 = -11 * 123;";
        try {
            assertEquals(msg, command.getJava(1));
        } catch (MissingArgumentException e) {
            fail("No exception should be raised");
        }
    }

    @Override
    @Test
    public void testGetHeader() {
        assertEquals("MUL Command", command.getHeader());
    }
}
