package model;

import except.CorruptedFileWarning;
import except.InvalidArgumentException;
import except.LoseProgressWarning;
import except.WarningException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import persistence.Loader;
import persistence.Saver;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

import static org.junit.jupiter.api.Assertions.*;

public class SaverTest {
    private Saver testSaver;

    private String targetFile;
    private String junkFile;
    private List<Command> helpingCommands;
    private Loader helpingLoader;

    @BeforeEach
    public void setup() {
        targetFile = "./data/test_data.json";
        junkFile = "./data/example_data_one.json";
        clearTargetFile();
        testSaver = new Saver(targetFile);
        helpingCommands = addCommands(1337);
    }

    @Test
    public void testConstructor() {
        assertEquals(targetFile, testSaver.getFileName());
        assertFalse("./data/JOJI.json" == testSaver.getFileName());
    }

    @Test
    public void testWriteInvalidFile() {
        String invalidName = "./data/Invalid\0File.txt";
        testSaver = new Saver(invalidName);
        try {
            testSaver.write(helpingCommands, false);
            fail("CorruptedFileWarning should be raised");
        } catch(CorruptedFileWarning e) {
            assertEquals("Warning: The file " + invalidName + " can not be resolved.",
                    e.getMessage());
        } catch (LoseProgressWarning e) {
            fail("CorruptedFileWarning should've been thrown instead");
        }
    }

    @Test
    public void testWriteEmptyCommands() {
        List<Command> emptyCommands = new LinkedList<>();
        try {
            testSaver.write(emptyCommands, true);
        } catch (WarningException e) {
            fail("No exception should be raised");
        }

        List<Command> loadedCommands = loadCommands(targetFile);
        assertTrue(compareCommands(loadedCommands, emptyCommands));
        assertTrue(loadedCommands.size() == 0);
    }

    @Test
    public void testWriteToEmptyFile() {
        try {
            testSaver.write(helpingCommands, true);
        } catch (WarningException e) {
            fail("No exception should be raised");
        }

        List<Command> loadedCommands = loadCommands(targetFile);
        assertTrue(compareCommands(helpingCommands, loadedCommands));
    }

    @Test
    public void testSoftWriteToNonEmptyFile() {
        writeJunk(targetFile, addCommands(780));
        try {
            testSaver.write(helpingCommands, false);
            fail("WarningException should be raised");
        } catch (CorruptedFileWarning e) {
            fail("WarningException should've been raised");
        } catch (LoseProgressWarning e) {
            // pass the test
        }
    }

    @Test
    public void testForcedWriteToNonEmptyFile() {
        List<Command> beforeLoadingCommands = addCommands(890);
        assertFalse(compareCommands(helpingCommands, beforeLoadingCommands));
        writeJunk(junkFile, beforeLoadingCommands);

        try {
            testSaver.write(helpingCommands, true);
        } catch (WarningException e) {
            fail("No exception should be raised");
        }

        List<Command> afterLoadingCommands = loadCommands(targetFile);
        assertTrue(compareCommands(helpingCommands, afterLoadingCommands));
    }

    @Test
    public void testCheckEmptyFile() {
        assertTrue(testSaver.isFileEmpty());
    }

    @Test
    public void testCheckNonEmptyFile() {
        writeJunk(targetFile, helpingCommands);
        assertFalse(testSaver.isFileEmpty());
    }

    private List<Command> addCommands(int seed) {
        Random numGenerator = new Random(seed);
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
        } catch (CorruptedFileWarning e) {
            fail("No exception should be raised");
        }
        return loadedCommands;
    }

    // EFFECTS: clear the data in test file
    private void clearTargetFile() {
        try {
            PrintWriter writer = new PrintWriter(targetFile);
            writer.close();
        } catch (FileNotFoundException e) {
            fail("No exception should be raised.");
        }
    }

    // EFFECTS: write to target file with junk so it will not be empty
    private void writeJunk(String file, List<Command> junk) {
        Saver junkSaver = new Saver(file);
        try {
            junkSaver.write(junk, true);
        } catch (WarningException e) {
            fail("No exception should be raised.");
        }
    }
}
