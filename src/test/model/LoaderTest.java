package model;

import except.InvalidArgumentException;
import except.NotYetExecutedException;
import except.WarningException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import persistence.Loader;
import persistence.Saver;

import java.io.FileNotFoundException;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

import static org.junit.jupiter.api.Assertions.*;

public class LoaderTest {
    private String nonEmptyDataFile;
    private String emptyDataFile;
    private Loader testLoader;
    private Saver helpingSaver;
    private List<Command> exampleData;

    @BeforeEach
    public void setup() {
        nonEmptyDataFile = "./data/example_data_one.json";
        emptyDataFile = "./data/example_data_two.json";
        exampleData = createExampleData();
        writeExampleData(new LinkedList<Command>(), emptyDataFile);
    }

    @Test
    public void testConstructor() {
        testLoader = new Loader(nonEmptyDataFile);
        assertEquals(nonEmptyDataFile, testLoader.getFileName());
        assertFalse("JOJI.json" == testLoader.getFileName());
    }

    @Test
    public void testReadInvalidFile() {
        testLoader = new Loader("Invalid Path.json");
        List<Command> loadedCommands;
        try {
            loadedCommands = testLoader.read();
            fail("FileNotFoundException should be raised");
        } catch (FileNotFoundException e) {
            // pass the test
        }
    }

    @Test
    public void testReadEmptyFile() {
        testLoader = new Loader(emptyDataFile);
        List<Command> loadedCommands = null;
        try {
            testLoader.read();
        } catch (FileNotFoundException e) {
            fail("No exception should be raised");
        }
        assertEquals(0, loadedCommands.size());
    }

    @Test
    public void testReadFile() {
        testLoader = new Loader(nonEmptyDataFile);
        List<Command> loadedCommands = null;
        try {
            testLoader.read();
        } catch (FileNotFoundException e) {
            fail("No exception should be raised");
        }
        assertTrue(compareCommands(exampleData, loadedCommands));
    }

    // EFFECTS: load example data manually
    private List<Command> createExampleData() {
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
        writeExampleData(addedCommands, nonEmptyDataFile);
        return addedCommands;
    }

    private void writeExampleData(List<Command> commands, String targetFile) {
        helpingSaver = new Saver(targetFile);
        try {
            helpingSaver.write(commands, true);
        } catch (FileNotFoundException e) {
            fail("No exception should be raised");
        } catch (WarningException e) {
            fail("No exception should be raised");
        }
        helpingSaver = null;
    }

    private boolean compareCommands(List<Command> listOne, List<Command> listTwo) {
        if (listOne.size() != listTwo.size())
            return false;
        for (int i = 0; i < listOne.size(); i++) {
            if(!listOne.get(i).equals(listTwo.get(i)))
                return false;
        }
        return true;
    }
}
