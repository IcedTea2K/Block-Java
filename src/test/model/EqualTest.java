package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class EqualTest extends ComparorTest {
    @Override
    @BeforeEach
    public void setup() {
        super.setup();
        command = new Equal();
    }

    @Test
    public void testExecuteEqual() {
        checkBehaviour(posNum, new DataType(34), true);
        checkBehaviour(new DataType(34), posNum, true);
        checkBehaviour(zero, new DataType(0), true);
        checkBehaviour(negNum, negNum, true);
    }

    @Test
    public void testExecuteUnequal() {
        checkBehaviour(posNum, new DataType(33), false);
        checkBehaviour(zero, negNum, false);
        checkBehaviour(negNum, new DataType(0), false);
    }

    @Override
    @Test
    public void testGetExamples() {
        String msg = "EQUAL -4 -4\n"
                + "EQUAL 39 120\n"
                + "EQUAL -59 20\n";
        assertEquals(msg, command.getExamples());
    }

    @Override
    @Test
    public void testGetJavaWithInputs() {
        checkBehaviour(zero, zero, true);
        assertEquals("boolean result1 = 0 == 0;", command.getJava(1));
        checkBehaviour(negNum, posNum, false);
        assertEquals("boolean result3 = -11 == 34;", command.getJava(3));
    }

    @Override
    @Test
    public void testGetHeader() {
        assertEquals("EQUAL Command", command.getHeader());
    }
}
