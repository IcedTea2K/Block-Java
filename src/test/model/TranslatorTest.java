package model;

import except.CommandNotFoundException;
import except.InvalidArgumentException;
import except.MissingCommandsException;
import except.NotYetExecutedException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.LinkedList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class TranslatorTest {
    private Translator testTranslator;
    private Command add;
    private Command sub;
    private Command mul;
    private Command div;

    @BeforeEach
    public void setup() {
        testTranslator = new Translator();

        add = new Add();
        sub = new Subtract();
        mul = new Multiply();
        div = new Divide();

        try {
            add.input(new DataType(10), new DataType(128));
            sub.input(new DataType(-12), new DataType(-8391));
            mul.input(new DataType(89), new DataType(-4));
            div.input(new DataType(1337), new DataType(7));
        } catch (InvalidArgumentException e) {
            fail("No exception should be raised");
        }
    }

    @Test
    public void testAddCommand() {
        try {
            testTranslator.addCommand(add);
            assertEquals("#1| ADD 10 128\n", testTranslator.getStream());
            testTranslator.addCommand(sub);
            assertEquals("#1| ADD 10 128\n" +
                    "#2| SUB -12 -8391", testTranslator.getStream());
            testTranslator.addCommand(mul);
            assertEquals("#1| ADD 10 128\n" +
                    "#2| SUB -12 -8391\n" +
                    "#3| MUL 89 -4", testTranslator.getStream());
            testTranslator.addCommand(div);
            assertEquals("#1| ADD 10 128\n" +
                    "#2| SUB -12 -8391\n" +
                    "#3| MUL 89 -4\n" +
                    "#5| DIV 1337 7\n", testTranslator.getStream());
        } catch (MissingCommandsException e) {
            fail("No exception should be raised");
        }
    }

    @Test
    public void testGetStreamWithNoCommands() {
        try {
            testTranslator.getStream();
            fail("MissingComandsException should have been raised");
        } catch (MissingCommandsException e) {
            assertEquals("except.MissingCommandsException: No commands have been given", e.toString());
        }
    }

    @Test
    public void testExecuteStreamWithNoCommands() {
        try {
            testTranslator.executeStream();
            fail("MissingComandsException should have been raised");
        } catch (MissingCommandsException e) {
            assertEquals("except.MissingCommandsException: No commands have been given", e.toString());
        }
    }

    @Test
    public void testExecuteWithSingleCommand() {
        testTranslator.addCommand(add);

        List<Command> addedCommands = new LinkedList<>();
        addedCommands.add(add);

        try {
            testTranslator.executeStream();
            checkResult(addedCommands, testTranslator.getResults());
        } catch (MissingCommandsException | NotYetExecutedException e) {
            fail("No exception should be raised");
        }
    }

    @Test
    public void testExecuteWithMultipleCommands() {
        testTranslator.addCommand(add);
        testTranslator.addCommand(sub);
        testTranslator.addCommand(mul);
        testTranslator.addCommand(div);

        List<Command> addedCommands = new LinkedList<>();
        addedCommands.add(add);
        addedCommands.add(sub);
        addedCommands.add(mul);
        addedCommands.add(div);

        try {
            testTranslator.executeStream();
            checkResult(addedCommands, testTranslator.getResults());
        } catch (MissingCommandsException | NotYetExecutedException e) {
            fail("No exception should be raised");
        }
    }

    @Test
    public void testGetResultWithoutCommands() {
        try {
            testTranslator.getResults();
            fail("MissingCommandsException should have been raised");
        } catch (MissingCommandsException e) {
            assertEquals("except.MissingCommandsException: " +
                    "No commands have been provided to the translator", e.toString());
        } catch (NotYetExecutedException e) {
            fail("MissingCommandsException should have been raised instead");
        }
    }

    @Test
    public void testGetResultWithoutExecuting() {
        testTranslator.addCommand(add);
        try {
            testTranslator.getResults();
            fail("NotYetExecutedException should have been raised");
        } catch (MissingCommandsException e) {
            fail("NotYetExecutedException should have been raised instead");
        } catch (NotYetExecutedException e) {
            assertEquals("except.MissingCommandsException: " +
                    "Unable to obtain the result. The command has not been executed yet", e.toString());
        }
    }

    @Test
    public void testGetGeneralHelp() {
        String msg = "++++++++++++++++Block Java Available Commands & Exceptions++++++++++++++++\n" +
                "Built-in Commands:\n" +
                "ADD numOne numTwo    Add numOne and numTwo together\n" +
                "SUB numOne numTwo    Subtract numTwo from numOne\n" +
                "MUL numOne numTwo    Multiply numOne and numTwo together\n" +
                "DIV numOne numTwo    Divide numTwo from numOne\n" +
                "\n" +
                "Supporting Commands:\n" +
                "HELP           General help about Block Java\n" +
                "HELP COMMAND   Help for a specific command\n" +
                "EXEC           Execute the commands and get the result\n" +
                "JAVA           Get Java code of the commands\n" +
                "GET Index      Get a command at index (based 1)\n" +
                "ALL            Display all input commands\n" +
                "DEL Index      Delete a command at index (based 1)\n" +
                "RES            Reset the translator and delete all commands\n" +
                "\n" +
                "Exceptions:\n" +
                "InvalidArgumentException   Input for command is invalid\n" +
                " | WrongArgumentTypeException            Input has the wrong type\n" +
                " | UnexpectedNumberOfArgumentsException  The number of inputs don't match\n" +
                " | DivideByZeroException                 Trying to divide by zero\n" +
                "MissingCommandsException   No commands have been given to the translator\n" +
                "NotYetExecutedException    The commands have not been executed yet";
        assertEquals(msg, testTranslator.getHelp());
    }

    @Test
    public void testGetHelpAboutCommand() {
        String msg = add.getHeader() + "\n" +
                "Constraints   " + add.getConstraints() + "\n" +
                "Return Type   " + add.getReturnType() + "\n" +
                "Examples:\n";
        String[] examples = add.getExamples().split("\n");
        for (String example : examples) {
            msg += "  " + example + "\n";
        }

        assertEquals(msg, testTranslator.getHelp(add));
    }

    @Test
    public void testGetStringCommandWithEmptyStream() {
        checkBehaviorAtOutOfBoundStream(1);
    }

    @Test
    public void testGetStringCommandAtOutOfBound() {
        testTranslator.addCommand(add);
        checkBehaviorAtOutOfBoundStream(0);
        checkBehaviorAtOutOfBoundStream(-10);

        testTranslator.addCommand(sub);
        testTranslator.addCommand(div);
        testTranslator.addCommand(mul);
        checkBehaviorAtOutOfBoundStream(5);
        checkBehaviorAtOutOfBoundStream(2);
    }

    @Test
    public void testGetStringCommandAtValidIdx() {
        testTranslator.addCommand(add);
        try {
            assertEquals("#1: " + add.toString(), testTranslator.getStringCommandAtIndex(1));

            testTranslator.addCommand(mul);
            testTranslator.addCommand(div);
            testTranslator.addCommand(sub);
            assertEquals("#2: " + mul.toString(), testTranslator.getStringCommandAtIndex(2));
            assertEquals("#3: " + div.toString(), testTranslator.getStringCommandAtIndex(3));
            assertEquals("#4: " + sub.toString(), testTranslator.getStringCommandAtIndex(4));
        } catch (CommandNotFoundException e) {
            fail("No exception should be raised");
        }
    }

    private void checkBehaviorAtOutOfBoundStream(int idx) {
        try {
            testTranslator.getStringCommandAtIndex(idx);
            fail("CommandNotFoundException should have been raised");
        } catch (CommandNotFoundException e) {
            assertEquals("except.CommandNotFoundException: " +
                    "No command is found at index #" + idx, e.toString());
        }
    }

    private void checkResult(List<Command> addedCommands, List<DataType> result) {
        assertEquals(result.size(), addedCommands.size());
        for (int i = 0; i < result.size(); i++) {
            try {
                assertEquals(addedCommands.get(i).getResult(), result.get(i));
            } catch (NotYetExecutedException e) {
                fail("No exceptions should have been raised");
            }
        }
    }
}
