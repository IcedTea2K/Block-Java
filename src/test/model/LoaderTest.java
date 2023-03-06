package model;

import except.InvalidArgumentException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import persistence.Loader;

import java.util.LinkedList;
import java.util.List;
import java.util.Random;

import static org.junit.jupiter.api.Assertions.*;

public class LoaderTest {
    private String targetFile;
    private Loader testLoader;
    private List<Command> exampleData;

    @BeforeEach
    public void setup() {
        targetFile = "example_data.json";
        testLoader = new Loader(targetFile);
        exampleData = loadExampleData();
    }

    @Test
    public void testConstructor() {
        assertEquals(targetFile, testLoader.getFileName());
        assertFalse("JOJI.json" == testLoader.getFileName());
    }

    @Test
    public void testReadInvalidFile() {

    }

    @Test
    public void testReadEmptyFile() {

    }

    @Test
    public void testReadFile() {
        
    }

    private List<Command> loadExampleData() {
        Random numGenerator = new Random(9801);
        List<Command> addedCommands = new LinkedList<>();
        for (int i = 0; i < 20; i++) {
            DataType numOne = new DataType(numGenerator.nextInt());
            DataType numTwo = new DataType(numGenerator.nextInt());
            Command command = null;

            switch (i % 4) {
                case 0:
                    command = new Add();
                    break;
                case 1:
                    command = new Subtract();
                    break;
                case 2:
                    command = new Multiply();
                    break;
                case 3:
                    command = new Divide();
                    break;
            }

            try {
                command.input(numOne, numTwo);
            } catch (InvalidArgumentException e) {
                fail("No exception should be thrown");
            }

            addedCommands.add(command);
        }
        return addedCommands;
    }
}
