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
        targetFile = "saved_progress.json";
        testSaver = new Saver(targetFile);
        helpingCommands = addCommands();
        helpingLoader = new Loader(targetFile);
    }

    @Test
    public void testConstructor() {
        assertEquals(targetFile, testSaver.getFileName());
        assertFalse("JOJI.json" == testSaver.getFileName());
    }

    @Test
    public void testWriteInvalidFile() {
        testSaver = new Saver("./data/Invalid\0File.txt");
        try {
            testSaver.write(helpingCommands, false);
            fail("FileNotFoundException should be raised");
        } catch(FileNotFoundException e) {
            // pass the test
        } catch (NotYetExecutedException e) {
            fail("FileNotFoundException should've been thrown instead");
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
        } catch (NotYetExecutedException e) {
            fail("No exception should be raised");
        } catch (WarningException e) {
            fail("No exception should be raised");
        }

        List<Command> loadedCommands;
        loadedCommands = helpingLoader.read();
        assertTrue(compareCommands(loadedCommands, emptyCommands));
        assertTrue(loadedCommands.size() == 0);
    }

    @Test
    public void testWriteWithoutExecuting() {
        try {
            testSaver.write(helpingCommands, false);
            fail("NotYetExecutedException should be raised");
        } catch (FileNotFoundException e) {
            fail("NotYetExecutedException should've been raised instead");
        } catch (NotYetExecutedException e) {
            // pass the test
        } catch (WarningException e) {
            fail("NotYetExecutedException should've been raised instead");
        }
    }

    @Test
    public void testWriteToEmptyFile() {
        executeCommands();
        try {
            testSaver.write(helpingCommands, true);
        } catch (FileNotFoundException e) {
            fail("No exception should be raised");
        } catch (NotYetExecutedException e) {
            fail("No exception should be raised");
        } catch (WarningException e) {
            fail("No exception should be raised");
        }

        List<Command> loadedCommands;
        loadedCommands = helpingLoader.read();
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
        } catch (NotYetExecutedException e) {
            fail("WarningException should've been raised");
        } catch (WarningException e) {
            assertTrue(e instanceof LoseProgressWarning);
        }
    }

    @Test
    public void testForcedWriteToNonEmptyFile() {
        executeCommands();

        helpingLoader = new Loader("test_data.json");
        List<Command> beforeLoadingCommands;
        beforeLoadingCommands = helpingLoader.read();
        assertFalse(compareCommands(helpingCommands, beforeLoadingCommands));

        try {
            testSaver.write(helpingCommands, true);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (NotYetExecutedException e) {
            throw new RuntimeException(e);
        } catch (WarningException e) {
            throw new RuntimeException(e);
        }

        helpingLoader = new Loader(targetFile);
        List<Command> afterLoadingCommands;
        afterLoadingCommands = helpingLoader.read();
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
}
