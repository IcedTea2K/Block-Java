package model;

import except.*;
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
            assertEquals("#1| ADD 10 128\n", testTranslator.getStringStream());
            testTranslator.addCommand(sub);
            assertEquals("#1| ADD 10 128\n" +
                    "#2| SUB -12 -8391\n", testTranslator.getStringStream());
            testTranslator.addCommand(mul);
            assertEquals("#1| ADD 10 128\n" +
                    "#2| SUB -12 -8391\n" +
                    "#3| MUL 89 -4\n", testTranslator.getStringStream());
            testTranslator.addCommand(div);
            assertEquals("#1| ADD 10 128\n" +
                    "#2| SUB -12 -8391\n" +
                    "#3| MUL 89 -4\n" +
                    "#4| DIV 1337 7\n", testTranslator.getStringStream());
        } catch (MissingCommandsException e) {
            fail("No exception should be raised");
        }
    }

    @Test
    public void testGetStreamWithNoCommands() {
        try {
            testTranslator.getStringStream();
            fail("MissingComandsException should have been raised");
        } catch (MissingCommandsException e) {
            assertEquals("except.MissingCommandsException: No commands have been provided to the translator"
                    , e.toString());
        }
    }

    @Test
    public void testExecuteStreamWithNoCommands() {
        try {
            testTranslator.executeStream();
            fail("MissingComandsException should have been raised");
        } catch (MissingCommandsException e) {
            assertEquals("except.MissingCommandsException: No commands have been provided to the translator",
                    e.toString());
        }
    }

    @Test
    public void testExecuteWithSingleCommand() {
        testTranslator.addCommand(add);

        try {
            testTranslator.executeStream();
            assertEquals("#1| 138\n", testTranslator.getResults());
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
            assertEquals("#1| 138\n" +
                    "#2| 8379\n" +
                    "#3| -356\n" +
                    "#4| 191\n", testTranslator.getResults());
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
            assertEquals("except.NotYetExecutedException: " +
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
                "NotYetExecutedException    The commands have not been executed yet\n";
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
        checkAccessingBehaviorAtOutOfBoundStream(1);
    }

    @Test
    public void testGetStringCommandAtOutOfBound() {
        testTranslator.addCommand(add);
        checkAccessingBehaviorAtOutOfBoundStream(0);
        checkAccessingBehaviorAtOutOfBoundStream(-10);

        testTranslator.addCommand(sub);
        testTranslator.addCommand(div);
        testTranslator.addCommand(mul);
        checkAccessingBehaviorAtOutOfBoundStream(5);
        checkAccessingBehaviorAtOutOfBoundStream(20);
    }

    @Test
    public void testGetStringCommandAtValidIdx() {
        testTranslator.addCommand(add);
        try {
            assertEquals("#1| " + add.toString(), testTranslator.getStringCommandAtIndex(1));

            testTranslator.addCommand(mul);
            testTranslator.addCommand(div);
            testTranslator.addCommand(sub);
            assertEquals("#2| " + mul.toString(), testTranslator.getStringCommandAtIndex(2));
            assertEquals("#3| " + div.toString(), testTranslator.getStringCommandAtIndex(3));
            assertEquals("#4| " + sub.toString(), testTranslator.getStringCommandAtIndex(4));
        } catch (CommandNotFoundException e) {
            fail("No exception should be raised");
        }
    }

    @Test
    public void testDeleteCommandWithEmptyStream() {
        checkDeletingBehaviorAtOutOfBoundStream(1);
    }

    @Test
    public void testDeleteCommandAtOutOfBound() {
        testTranslator.addCommand(add);
        checkDeletingBehaviorAtOutOfBoundStream(0);
        checkDeletingBehaviorAtOutOfBoundStream(-10);

        testTranslator.addCommand(mul);
        testTranslator.addCommand(div);
        testTranslator.addCommand(sub);
        checkDeletingBehaviorAtOutOfBoundStream(5);
        checkDeletingBehaviorAtOutOfBoundStream(20);
    }

    @Test
    public void testDeleteCommandsUntilEmptyInDescendingOrder() {
        testTranslator.addCommand(sub);
        testTranslator.addCommand(mul);
        testTranslator.addCommand(div);

        checkDeletingCommandAtValidIdxBehaviour(3,
                "#1| SUB -12 -8391\n" +
                        "#2| MUL 89 -4\n");
        checkDeletingCommandAtValidIdxBehaviour(2, "#1| SUB -12 -8391\n");
        checkDeletingTheLastCommandBehaviour();
    }

    @Test
    public void testDeleteCommandsUntilEmptyInAscendingOrder() {
        testTranslator.addCommand(sub);
        testTranslator.addCommand(mul);
        testTranslator.addCommand(div);

        checkDeletingCommandAtValidIdxBehaviour(1,
                "#1| MUL 89 -4\n" +
                        "#2| DIV 1337 7\n");
        checkDeletingCommandAtValidIdxBehaviour(1, "#1| DIV 1337 7\n");
        checkDeletingTheLastCommandBehaviour();
    }

    @Test
    public void testDeleteCommandsUntilEmptyOutOfOrder() {
        testTranslator.addCommand(add);
        checkDeletingTheLastCommandBehaviour();

        testTranslator.addCommand(sub);
        testTranslator.addCommand(mul);
        testTranslator.addCommand(div);

        checkDeletingCommandAtValidIdxBehaviour(3,
                "#1| SUB -12 -8391\n" +
                        "#2| MUL 89 -4\n");
        checkDeletingCommandAtValidIdxBehaviour(1, "#1| MUL 89 -4\n");
        checkDeletingTheLastCommandBehaviour();
    }

    @Test
    public void testDeleteCommandAtEarlierIdxCausesOutOfBound() {
        testTranslator.addCommand(add);
        testTranslator.addCommand(sub);
        testTranslator.addCommand(mul);
        checkDeletingCommandAtValidIdxBehaviour(1, "#1| SUB -12 -8391\n" +
                "#2| MUL 89 -4\n");
        checkDeletingBehaviorAtOutOfBoundStream(3);
    }

    @Test
    public void testDeleteCommandWithLeftOver() {
        testTranslator.addCommand(add);
        testTranslator.addCommand(sub);
        testTranslator.addCommand(mul);
        testTranslator.addCommand(div);
        checkDeletingCommandAtValidIdxBehaviour(2, "#1| ADD 10 128\n" +
                "#2| MUL 89 -4\n" +
                "#3| DIV 1337 7\n");
        checkDeletingCommandAtValidIdxBehaviour(3, "#1| ADD 10 128\n" +
                "#2| MUL 89 -4\n");
    }

    @Test
    public void testTranslateToJavaWithNoCommands() {
        try {
            testTranslator.translateToJava();
            fail("MissingCommandsException should have been raised");
        } catch (MissingCommandsException e) {
            assertEquals("except.MissingCommandsException: " +
                    "No commands have been provided to the translator", e.toString());
        }
    }

    @Test
    public void testTranslateToJavaWithTheWholeStream() {
        testTranslator.addCommand(add);
        testTranslator.addCommand(div);
        testTranslator.addCommand(sub);
        testTranslator.addCommand(mul);

        String msg = "#1| int result1 = 10 + 128;\n" +
                "#2| int result2 = 1337 / 7;\n" +
                "#3| int result3 = -12 - -8391;\n" +
                "#4| int result4 = 89 * -4;\n";
        try {
            assertEquals(msg, testTranslator.translateToJava());
        } catch (MissingCommandsException e) {
            fail("No exception should be raised");
        }
    }

    @Test
    public void testGetEmptyStreamAsCommand() {
        assertEquals(new LinkedList<Command>(), testTranslator.getStream());
    }

    @Test
    public void testGetNonEmptyStreamAsCommands() {
        List<Command> storedStream = new LinkedList<>();
        storedStream.add(add);
        storedStream.add(sub);
        storedStream.add(mul);
        storedStream.add(div);
        testTranslator.addCommand(add);
        testTranslator.addCommand(sub);
        testTranslator.addCommand(mul);
        testTranslator.addCommand(div);
        assertEquals(storedStream, testTranslator.getStream());
    }

    private void checkDeletingCommandAtValidIdxBehaviour(int idx, String newExpectedStream) {
        try {
            assertEquals(newExpectedStream, testTranslator.deleteCommandAtIndex(idx));
        } catch (CommandNotFoundException | MissingCommandsException e) {
            fail("No exception should be raised");
        }
    }

    private void checkDeletingTheLastCommandBehaviour() {
        try {
            testTranslator.deleteCommandAtIndex(1);
        } catch (CommandNotFoundException e) {
            fail("MissingCommandsException shoulve have been raised instead");
        } catch (MissingCommandsException e) {
            assertEquals("except.MissingCommandsException: " +
                    "No commands have been provided to the translator", e.toString());
        }
    }

    private void checkDeletingBehaviorAtOutOfBoundStream(int idx) {
        try {
            testTranslator.deleteCommandAtIndex(idx);
            fail("CommandNotFoundException should have been raised");
        } catch (CommandNotFoundException e) {
            assertEquals("except.CommandNotFoundException: " +
                    "No command is found at index #" + idx, e.toString());
        } catch (MissingCommandsException e) {
            fail("CommandNotFoundException should have been raised instead");
        }
    }

    private void checkAccessingBehaviorAtOutOfBoundStream(int idx) {
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
