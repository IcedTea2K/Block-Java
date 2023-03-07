package model;

import except.InvalidArgumentException;
import except.LoseProgressWarning;
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

public class SaverTest {
    private Saver testSaver;

    private String targetFile;
    private List<Command> helpingCommands;
    private Loader helpingLoader;

    @BeforeEach
    public void setup() {
        targetFile = "./data/saved_progress.json";
        testSaver = new Saver(targetFile);
        helpingCommands = addCommands();
    }

    @Test
    public void testConstructor() {
        assertEquals(targetFile, testSaver.getFileName());
        assertFalse("./data/JOJI.json" == testSaver.getFileName());
    }

    @Test
    public void testWriteInvalidFile() {
        testSaver = new Saver("./data/Invalid\0File.txt");
        try {
            testSaver.write(helpingCommands, false);
            fail("FileNotFoundException should be raised");
        } catch(FileNotFoundException e) {
            // pass the test
        } catch (WarningException e) {
            fail("FileNotFoundException should've been thrown instead");
        }
    }

    @Test
    public void testWriteEmptyCommands() {
        List<Command> emptyCommands = new LinkedList<>();
        try {
            testSaver.write(emptyCommands, true);
        } catch (FileNotFoundException e) {
            fail("No exception should be raised");
        } catch (WarningException e) {
            fail("No exception should be raised");
        }

        List<Command> loadedCommands = loadCommands(targetFile);
        assertTrue(compareCommands(loadedCommands, emptyCommands));
        assertTrue(loadedCommands.size() == 0);
    }

    @Test
    public void testWriteToEmptyFile() {
        executeCommands();
        try {
            testSaver.write(helpingCommands, true);
        } catch (FileNotFoundException e) {
            fail("No exception should be raised");
        } catch (WarningException e) {
            fail("No exception should be raised");
        }

        List<Command> loadedCommands = loadCommands(targetFile);
        assertTrue(compareCommands(helpingCommands, loadedCommands));
    }

    @Test
    public void testSoftWriteToNonEmptyFile() {
        executeCommands();
        try {
            testSaver.write(helpingCommands, false);
            fail("WarningException should be raised");
        } catch (FileNotFoundException e) {
            fail("WarningException should've been raised");
        } catch (WarningException e) {
            assertTrue(e instanceof LoseProgressWarning);
        }
    }

    @Test
    public void testForcedWriteToNonEmptyFile() {
        executeCommands();

        List<Command> beforeLoadingCommands = loadCommands("./data/example_data_one.json");
        assertFalse(compareCommands(helpingCommands, beforeLoadingCommands));

        try {
            testSaver.write(helpingCommands, true);
        } catch (FileNotFoundException e) {
            fail("No exception should be raised");
        } catch (WarningException e) {
            fail("No exception should be raised");
        }

        List<Command> afterLoadingCommands = loadCommands(targetFile);
        assertTrue(compareCommands(helpingCommands, afterLoadingCommands));
    }

    private List<Command> addCommands() {
        Random numGenerator = new Random(1337);
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

    private void executeCommands() {
        for (Command command: helpingCommands) {
            command.execute();
        }
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

    private List<Command> loadCommands(String fileName) {
        helpingLoader = new Loader(fileName);
        List<Command> loadedCommands = null;
        try {
            loadedCommands = helpingLoader.read();
        } catch (FileNotFoundException e) {
            fail("No exception should be raised");
        }
        return loadedCommands;
    }
}
