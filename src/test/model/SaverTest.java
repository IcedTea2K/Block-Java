package model;

import except.InvalidArgumentException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import persistence.Saver;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

import static org.junit.jupiter.api.Assertions.*;

public class SaverTest {
    private Saver testSaver;
    private String targetFile;
    private List<Command> helpingCommands;

    @BeforeEach
    public void setup() {
        targetFile = "saved_progress.json";
        testSaver = new Saver(targetFile);
        helpingCommands = addCommands();
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
            testSaver.write(helpingCommands);
            fail("FileNotFoundException should be raised");
        } catch(FileNotFoundException e) {
            // pass the test
        }
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
}
