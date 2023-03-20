package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class AndTest extends LogicalTest {
    @Override
    @BeforeEach
    public void setup() {
        this.command = new And();
        super.setup();
    }

    @Test
    public void testExecuteTrueFalse() {
        checkBehaviour(trueVal, falseVal, false);
        checkBehaviour(falseVal, trueVal, false);
    }

    @Test
    public void testExecuteTrueTrue() {
        checkBehaviour(trueVal, trueVal, true);
    }

    @Test
    public void testExecuteFalseFalse() {
        checkBehaviour(falseVal, falseVal, false);
    }

    @Override
    @Test
    public void testGetExamples() {
        String msg = "AND TRUE FALSE\n"
                + "AND FALSE FALSE\n"
                + "AND TRUE TRUE\n";
        assertEquals(msg, command.getExamples());
    }

    @Override
    @Test
    public void testGetJavaWithInputs() {
        checkBehaviour(trueVal, falseVal, false);
        assertEquals("boolean result1 = true && false;", command.getJava(1));
        checkBehaviour(trueVal, trueVal, true);
        assertEquals("boolean result2 = true && true;", command.getJava(2));
    }

    @Override
    @Test
    public void testGetHeader() {
        assertEquals("AND Command", command.getHeader());
    }
}
