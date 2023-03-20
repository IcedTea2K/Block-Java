package model;

import except.InvalidArgumentException;
import except.InvalidReturnTypeException;
import except.MissingArgumentException;
import except.NotYetExecutedException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public abstract class BoolTest extends OperatorTest {
    DataType trueVal;
    DataType falseVal;

    @Override
    public void setup() {
        trueVal = new DataType(true);
        falseVal = new DataType(false);
    }

    @Override
    @Test
    public void testGetReturnType() {
        assertEquals("Boolean", command.getReturnType());
    }

    protected void checkBehaviour(DataType operandOne, DataType operandTwo, boolean expectedResult) {
        try {
            command.input(operandOne, operandTwo);
            command.execute();
            DataType result = command.getResult();
            assertEquals(expectedResult, result.getBoolean());
        } catch (InvalidArgumentException | MissingArgumentException | NotYetExecutedException |
                 InvalidReturnTypeException e) {
            fail("no exception should be raised");
        }
    }
}
