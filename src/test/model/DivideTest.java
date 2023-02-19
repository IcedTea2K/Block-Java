package model;

import except.DivideByZeroException;
import except.InvalidArgumentException;
import except.MissingArgumentException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class DivideTest extends ArithmeticTest {
    @Override
    @BeforeEach
    public void setup() {
        command = new Divide();
        posNum = new DataType(123);
        negNum = new DataType(-11);
        zero = new DataType(0);
    }

    @Test
    public void testExecutePosPos() {
        checkBehaviour(posNum, posNum, 123 / 123);
    }

    @Test
    public void testExecutePosNeg() {
        checkBehaviour(posNum, negNum, 123 / -11);
    }

    @Test
    public void testExecuteNegPos() {
        checkBehaviour(negNum, posNum, -11 / 123);
    }

    @Test
    public void testExecuteNegNeg() {
        checkBehaviour(negNum, negNum, -11 / -11);
    }

    @Test
    public void testExecuteZeroNeg() {
        checkBehaviour(zero, negNum, 0);
    }

    @Test
    public void testInputPosZero() {
        divideByZero(posNum);
    }

    @Test
    public void testExecuteNegZero() {
        divideByZero(negNum);
    }

    @Test
    public void testExecuteZeroZero() {
        divideByZero(zero);
    }

    @Override
    @Test
    public void testGetExamples() {
        String msg = "DIV 40 20\n" +
                "DIV -3 80\n" +
                "DIV 238 12";
        assertEquals(msg, command.getExamples());
    }

    @Override
    @Test
    public void testGetJavaWithInputs() {
        checkBehaviour(negNum, posNum, -11 / 123);
        String msg = "int result = -11 / 123;";
        try {
            assertEquals(msg, command.getJava());
        } catch (MissingArgumentException e) {
            fail("No exception should be raised");
        }
    }

    @Override
    @Test
    public void testGetConstraints() {
        assertEquals("Operators only accept two inputs. They both need to be numbers." +
                        " The second number (denominator) needs to be other than 0.",
                command.getConstraints());
    }

    // EFFECTS: try to divide the number by zero and check the raised exception
    private void divideByZero(DataType num) {
        try {
            command.input(num, zero);
            fail("DivideByZeroException should have been raised");
        } catch (DivideByZeroException e) {
            assertEquals("except.DivideByZeroException: " +
                    "The denominator cannot be zero", e.toString());
        } catch (InvalidArgumentException e) {
            fail("DivideByZeroException should have SPECIFICALLY been raised");
        }
    }
}
