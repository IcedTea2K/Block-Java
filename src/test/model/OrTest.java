package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class OrTest extends LogicalTest {
    @Override
    @BeforeEach
    public void setup() {
        this.command = new Or();
        super.setup();
    }

    @Test
    public void testExecuteTrueFalse() {
        checkBehaviour(trueVal, falseVal, true);
        checkBehaviour(falseVal, trueVal, true);
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
        String msg = "OR TRUE FALSE\n"
                + "OR FALSE FALSE\n"
                + "OR TRUE TRUE";
        assertEquals(msg, command.getExamples());
    }

    @Override
    @Test
    public void testGetJavaWithInputs() {
        checkBehaviour(trueVal, falseVal, true);
        assertEquals("boolean result1 = true || false;", command.getJava(1));
        checkBehaviour(falseVal, falseVal, false);
        assertEquals("boolean result2 = false || false;", command.getJava(2));
    }

    @Override
    @Test
    public void testGetHeader() {
        assertEquals("OR Command", command.getHeader());
    }
}
