package model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class AndTest extends LogicalTest {
    @Override
    public void setup() {
        command = new And();
        trueVal = new DataType(true);
        falseVal = new DataType(false);
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
    public void testGetExamples() {
        String msg = "AND TRUE FALSE\n"
                + "AND FALSE FALSE\n"
                + "AND TRUE TRUE\n";
        assertEquals(msg, command.getExamples());
    }

    @Override
    public void testGetJavaWithInputs() {
        checkBehaviour(trueVal, falseVal, false);
        assertEquals("boolean result1 = true && false", command.getJava(1));
        checkBehaviour(trueVal, trueVal, true);
        assertEquals("boolean result1 = true && true", command.getJava(2));
    }

    @Override
    public void testGetHeader() {
        assertEquals("AND Command", command.getHeader());
    }
}
