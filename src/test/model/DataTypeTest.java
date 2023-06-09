package model;

import except.InvalidReturnTypeException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class DataTypeTest {
    private DataType testIntDT;
    private DataType testTrueDT;
    private DataType testFalseDT;
    private DataType testStreamDT;

    @BeforeEach
    public void setup() {
        this.testIntDT = new DataType(11);
        this.testTrueDT = new DataType(true);
        this.testFalseDT = new DataType(false);
        ArrayList<DataType> tempStream = new ArrayList<DataType>();
        for(int i = 0; i < 10; i++) {
            if(i < 5) {
                tempStream.add(new DataType(i));
            } else{
                tempStream.add(new DataType(i % 2 == 0));
            }
        }
        this.testStreamDT = new DataType(tempStream);
    }

    // Testing Int DataType
    @Test
    public void testGetIntWithInt() {
        try {
            assertEquals(11, testIntDT.getNumber());
        } catch (InvalidReturnTypeException e) {
            fail("Exception should not be thrown");
        }
    }

    @Test
    public void testGetBooleanWithInt() {
        try {
            testIntDT.getBoolean();
            fail();
        } catch (InvalidReturnTypeException e) {
            // pass the test
        }
    }

    @Test
    public void testGetStreamWithInt() {
        try {
            testIntDT.getDataStream();
            fail();
        } catch (InvalidReturnTypeException e) {
            // pass the test
        }
    }

    // Testing boolean DataType
    @Test
    public void testGetBooleanWithBoolean() {
        try {
            assertTrue(testTrueDT.getBoolean());
        } catch (InvalidReturnTypeException e) {
            fail("Exception should not be thrown");
        }

        try {
            assertFalse(testFalseDT.getBoolean());
        } catch (InvalidReturnTypeException e) {
            fail("Exception should not be thrown");
        }
    }

    @Test
    public void testGetIntWithBoolean() {
        try {
            testTrueDT.getNumber();
            testFalseDT.getNumber();
            fail();
        } catch (InvalidReturnTypeException e) {
            // pass the test
        }
    }

    @Test
    public void testGetStreamWithBoolean() {
        try {
            testTrueDT.getDataStream();
            testFalseDT.getDataStream();
            fail();
        } catch (InvalidReturnTypeException e) {
            // pass the test
        }
    }

    // Testing DataStream DataType
    @Test
    public void testGetDataStreamWithDataStream() {
        List<DataType> temp = new ArrayList<>();
        try {
            temp = testStreamDT.getDataStream();
        } catch (InvalidReturnTypeException e) {
            fail("Exception should not be thrown");
        }

        assertEquals(10, temp.size());
        for(int i = 0; i < 10; i++){
            if(i < 5) {
                try {
                    assertEquals(i, temp.get(i).getNumber());
                } catch (InvalidReturnTypeException e) {
                    fail("Exception should not be thrown");
                }
            } else {
                try {
                    assertEquals(i % 2 == 0, temp.get(i).getBoolean());
                } catch (InvalidReturnTypeException e) {
                    fail("Exception should not be thrown");
                }
            }
        }
    }

    @Test
    public void testGetBooleanWithDataStream() {
        try {
            testStreamDT.getBoolean();
            fail();
        } catch (InvalidReturnTypeException e) {
            // pass the test
        }
    }

    @Test
    public void testGetIntWithDataStream() {
        try {
            testStreamDT.getNumber();
            fail();
        } catch (InvalidReturnTypeException e) {
            // pass the test
        }
    }

    @Test
    public void testToString() {
        assertEquals("11", testIntDT.toString());
        assertEquals("false", testFalseDT.toString());
        assertEquals("true", testTrueDT.toString());
        assertEquals("[0, 1, 2, 3, 4, false, true, false, true, false]", testStreamDT.toString());
    }

    @Test
    public void testEqual() {
        DataType extraIntDT = new DataType(11);
        DataType extraTrueDT = new DataType(true);
        DataType extraFalseDT = new DataType(false);
        ArrayList<DataType> temp = new ArrayList<DataType>();
        for(int i = 0; i < 10; i++) {
            if(i < 5) {
                temp.add(new DataType(i));
            } else{
                temp.add(new DataType(i % 2 == 0));
            }
        }
        DataType extraStreamDT = new DataType(temp);
        assertTrue(testIntDT.equals(extraIntDT));
        assertTrue(testTrueDT.equals(extraTrueDT));
        assertTrue(testFalseDT.equals(extraFalseDT));
        assertTrue(testStreamDT.equals(extraStreamDT));
    }

    @Test
    public void testUnequal() {
        DataType extraIntDT = new DataType(12);
        ArrayList<DataType> temp = new ArrayList<DataType>();
        for(int i = 0; i < 10; i++) {
            if(i < 5) {
                temp.add(new DataType(i % 2 == 0));
            } else{
                temp.add(new DataType(i));
            }
        }
        DataType extraStreamDT = new DataType(temp);
        assertFalse(testFalseDT.equals(null));
        assertFalse(testIntDT.equals(11));
        assertFalse(testIntDT.equals(extraIntDT));
        assertFalse(testTrueDT.equals(testFalseDT));
        assertFalse(testFalseDT.equals(testTrueDT));
        assertFalse(testStreamDT.equals(extraStreamDT));
    }

    @Test
    public void testHashCode() {
        assertEquals(4620, testIntDT.hashCode());
        assertEquals(419, testTrueDT.hashCode());
        assertEquals(-419, testFalseDT.hashCode());
        assertEquals(4201, testStreamDT.hashCode());
    }
}
