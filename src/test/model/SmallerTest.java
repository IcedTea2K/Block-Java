package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class SmallerTest extends ComparorTest {
    @Override
    @BeforeEach
    public void setup() {
        super.setup();
        command = new Smaller();
    }

    @Test
    public void testExecuteCompareTrue() {
        checkBehaviour(negNum, posNum, true);
        checkBehaviour(negNum, zero, true);
        checkBehaviour(zero, posNum, true);
    }

    @Test
    public void testExecuteCompareFalse() {
        checkBehaviour(posNum, negNum, false);
        checkBehaviour(zero, negNum, false);
        checkBehaviour(posNum, zero, false);
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
        String msg = "SMALLER 3 4\n"
                + "SMALLER -23 -20\n"
                + "SMALLER 80 -48\n";
        assertEquals(msg, command.getExamples());
    }

    @Override
    @Test
    public void testGetJavaWithInputs() {
        checkBehaviour(negNum, zero, true);
        assertEquals("boolean result1 = -11 < 0;", command.getJava(1));
        checkBehaviour(posNum, negNum, false);
        assertEquals("boolean result3 = 34 < -11;", command.getJava(3));
    }

    @Override
    @Test
    public void testGetHeader() {
        assertEquals("SMALLER Command", command.getHeader());
    }
}
