package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class LargerTest extends ComparorTest {
    @Override
    @BeforeEach
    public void setup() {
        super.setup();
        command = new Larger();
    }

    @Test
    public void testExecuteCompareTrue() {
        checkBehaviour(posNum, negNum, true);
        checkBehaviour(zero, negNum, true);
        checkBehaviour(posNum, zero, true);
    }

    @Test
    public void testExecuteCompareFalse() {
        checkBehaviour(negNum, posNum, false);
        checkBehaviour(negNum, zero, false);
        checkBehaviour(zero, posNum, false);
    }

    @Test
    public void testExcuteTwoEqualNumbers() {
        checkBehaviour(posNum, posNum, false);
        checkBehaviour(negNum, negNum, false);
        checkBehaviour(zero, zero, false);
    }

    @Override
    @Test
    public void testGetExamples() {
        String msg = "LARGER 30 -2\n"
                + "LARGER 2 3\n"
                + "LARGER -3 -54\n";
        assertEquals(msg, command.getExamples());
    }

    @Override
    @Test
    public void testGetJavaWithInputs() {
        checkBehaviour(negNum, zero, false);
        assertEquals("boolean result1 = -11 > 0;", command.getJava(1));
        checkBehaviour(posNum, negNum, true);
        assertEquals("boolean result3 = 34 > -11;", command.getJava(3));
    }

    @Override
    @Test
    public void testGetHeader() {
        assertEquals("LARGER Command", command.getHeader());
    }
}
