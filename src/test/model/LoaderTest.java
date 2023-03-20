package model;

import except.CorruptedFileWarning;
import except.InvalidArgumentException;
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

public class LoaderTest {
    private String targetFile;
    private Loader testLoader;
    private Saver helpingSaver;
    private List<Command> exampleData;

    @BeforeEach
    public void setup() {
        targetFile = "./data/test_data.json";
        exampleData = createExampleData();
        writeExampleData(exampleData, targetFile);
    }

    @Test
    public void testConstructor() {
        testLoader = new Loader(targetFile);
        assertEquals(targetFile, testLoader.getFileName());
        assertFalse("JOJI.json" == testLoader.getFileName());
    }

    @Test
    public void testReadInvalidFile() {
        testLoader = new Loader("Invalid Path.json");
        List<Command> loadedCommands;
        try {
            loadedCommands = testLoader.read();
            fail("FileNotFoundException should be raised");
        } catch (CorruptedFileWarning e) {
            assertEquals("Warning: The file Invalid Path.json can not be resolved.", e.getMessage());
        }
    }

    @Test
    public void testReadInvalidJsonFormatInvalidKeyIndex() {
        String junk = "{\n" +
                "   \"9\": {\n" +
                "        \"operandOne\": \"429993313\",\n" +
                "        \"operandTwo\": \"1592168621\",\n" +
                "        \"command\": \"SUB\"\n" +
                "    },\n" +
                "    \"10\": {\n" +
                "        \"operandOne\": \"673194132\",\n" +
                "        \"operandTwo\": \"-96193699\",\n" +
                "        \"command\": \"MUL\"\n" +
                "    }\n" +
                "}\n";
        writeMessageDirectly(junk, targetFile);
        testLoader = new Loader(targetFile);
        try {
            List<Command> loadedCommands = testLoader.read();
            fail("CorruptedFileWarning should have been raised.");
        } catch (CorruptedFileWarning e) {
            assertEquals("Warning: Object at position 0 can not be resolved. The file "
            + targetFile + " might be corrupted.", e.getMessage());
        }
    }

    @Test
    public void testReadInvalidJsonFormatInvalidCommand() {
        String junk = "{\n" +
                "   \"0\": {\n" +
                "        \"operandOne\": \"429993313\",\n" +
                "        \"operandTwo\": \"1592168621\",\n" +
                "        \"command\": \"SUB\"\n" +
                "    },\n" +
                "    \"1\": {\n" +
                "        \"operandOne\": \"673194132\",\n" +
                "        \"operandTwo\": \"-96193699\",\n" +
                "        \"comman\": \"MUL\"\n" +
                "    }\n" +
                "}\n";
        writeMessageDirectly(junk, targetFile);
        testLoader = new Loader(targetFile);
        try {
            List<Command> loadedCommands = testLoader.read();
            fail("CorruptedFileWarning should have been raised.");
        } catch (CorruptedFileWarning e) {
            assertEquals("Warning: Object at position 1 can not be resolved. The file "
                    + targetFile + " might be corrupted.", e.getMessage());
        }
    }

    @Test
    public void testReadInvalidJsonFormatInvalidOperand() {
        String junk = "{\n" +
                "   \"0\": {\n" +
                "        \"operadOne\": \"429993313\",\n" +
                "        \"operandwo\": \"1592168621\",\n" +
                "        \"command\": \"SUB\"\n" +
                "    },\n" +
                "    \"1\": {\n" +
                "        \"operandOne\": \"673194132\",\n" +
                "        \"operandTwo\": \"1\",\n" +
                "        \"command\": \"DIV\"\n" +
                "    }\n" +
                "}\n";
        writeMessageDirectly(junk, targetFile);
        testLoader = new Loader(targetFile);
        try {
            List<Command> loadedCommands = testLoader.read();
            fail("CorruptedFileWarning should have been raised.");
        } catch (CorruptedFileWarning e) {
            assertEquals("Warning: Object at position 0 can not be resolved. The file "
                    + targetFile + " might be corrupted.", e.getMessage());
        }
    }

    @Test
    public void testReadInvalidJsonFormatImpossibleOperand() {
        String junk = "{\n" +
                "   \"0\": {\n" +
                "        \"operandOne\": \"429993313\",\n" +
                "        \"operandTwo\": \"1592168621\",\n" +
                "        \"command\": \"SUB\"\n" +
                "    },\n" +
                "    \"1\": {\n" +
                "        \"operandOne\": \"673194132\",\n" +
                "        \"operandTwo\": \"0\",\n" +
                "        \"command\": \"DIV\"\n" +
                "    }\n" +
                "}\n";
        writeMessageDirectly(junk, targetFile);
        testLoader = new Loader(targetFile);
        try {
            List<Command> loadedCommands = testLoader.read();
            fail("CorruptedFileWarning should have been raised.");
        } catch (CorruptedFileWarning e) {
            assertEquals("Warning: Object at position 1 can not be resolved. The file "
                    + targetFile + " might be corrupted.", e.getMessage());
        }
    }

    @Test
    public void testReadEmptyFile() {
        testLoader = new Loader(targetFile);
        writeExampleData(new LinkedList<>(), targetFile);
        List<Command> loadedCommands = null;
        try {
            loadedCommands = testLoader.read();
        } catch (CorruptedFileWarning e) {
            fail("No exception should be raised");
        }
        assertEquals(0, loadedCommands.size());
    }

    @Test
    public void testReadFile() {
        testLoader = new Loader(targetFile);
        List<Command> loadedCommands = null;
        try {
            loadedCommands = testLoader.read();
        } catch (CorruptedFileWarning e) {
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

            switch (i % 9) {
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
                case 4:
                    command = new And();
                    break;
                case 5:
                    command = new Or();
                    break;
                case 6:
                    command = new Larger();
                    break;
                case 7:
                    command = new Smaller();
                    break;
                case 8:
                    command = new Equal();
                    break;
            }

            try {
                if (command instanceof And || command instanceof Or) {
                    command.input(new DataType(true), new DataType(false));
                } else {
                    command.input(numOne, numTwo);
                }
            } catch (InvalidArgumentException e) {
                fail("No exception should be thrown");
            }

            addedCommands.add(command);
        }
        writeExampleData(addedCommands, targetFile);
        return addedCommands;
    }

    private void writeExampleData(List<Command> commands, String targetFile) {
        helpingSaver = new Saver(targetFile);
        try {
            helpingSaver.write(commands, true);
        } catch (WarningException e) {
            fail("No exception should be raised");
        }
        helpingSaver = null;
    }

    // EFFECTS: write given message to a file directly
    private void writeMessageDirectly(String msg, String fileName) {
        PrintWriter writer = null;
        try {
            writer = new PrintWriter(targetFile);
        } catch (FileNotFoundException e) {
            fail("No exception should be thrown.");
        }
        writer.print(msg);
        writer.close();
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
