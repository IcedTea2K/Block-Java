package model;

import except.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class SubtractTest extends ArithmeticTest {
    @Override
    @BeforeEach
    public void setup() {
        command = new Subtract();
        posNum = new DataType(123);
        negNum = new DataType(-11);
        zero = new DataType(0);
    }

    @Test
    public void testExecutePosPos() {
        checkBehaviour(posNum, posNum, 123 - 123);
    }

    @Test
    public void testExecutePosNeg() {
        checkBehaviour(posNum, negNum, 123 + 11);
    }

    @Test
    public void testExecuteNegPos() {
        checkBehaviour(negNum, posNum, -11 - 123);
    }

    @Test
    public void testExecuteNegNeg() {
        checkBehaviour(negNum, negNum, -11 + 11);
    }

    @Test
    public void testExecutePosZero() {
        checkBehaviour(posNum, zero, 123);
    }

    @Test
    public void testExecuteZeroNeg() {
        checkBehaviour(zero, negNum, 11);
    }

    @Test
    public void testExecuteNegZero() {
        checkBehaviour(negNum, zero, -11);
    }

    @Test
    public void testExecuteZeroZero() {
        checkBehaviour(zero, zero, 0);
    }

    @Override
    @Test
    public void testGetExamples() {
        String msg = "SUB 1337 89\n" +
                "SUB 28.1 39\n" +
                "SUB 38 -10";
        assertEquals(msg, command.getExamples());
    }

    @Override
    @Test
    public void testGetJavaWithInputs() {
        checkBehaviour(negNum, posNum, -11 - 123);
        String msg = "int result = -11 - 123;";
        try {
            assertEquals(msg, command.getJava());
        } catch (MissingArgumentException e) {
            fail("No exception should be raised");
        }
    }
}
