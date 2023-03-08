package ui;

import except.*;
import model.*;
import persistence.Loader;
import persistence.Saver;

import java.util.*;

// UI for Block Java on the console
public class ConsoleApplication {
    private Translator mainTranslator;
    private Scanner scanner;
    private Saver saver;
    private Loader loader;
    private boolean isRunning;
    private String savedFileName;

    // EFFECTS: construct and run the application
    public ConsoleApplication() {
        this.mainTranslator = new Translator();
        this.scanner = new Scanner(System.in);
        this.savedFileName = "./data/saved_progress.json";
        this.saver = new Saver(savedFileName);
        this.loader = new Loader(savedFileName);

        run();
    }

    // MODIFIES: this
    // EFFECTS: run the program
    private void run() {
        isRunning = true;
        startMenu();

        while (isRunning) {
            String[] inputs = takeInput();

            if (inputs[0].equals("QUIT")) {
                stop();
                break;
            }
            processSupportingCommands(inputs[0], Arrays.copyOfRange(inputs, 1, inputs.length));
        }

        endMenu();
    }

    private void stop() {
        isRunning = false;
    }

    // MODIFIES: this
    // EFFECTS: process the input and call the corresponding supporting commands
    @SuppressWarnings("methodlength")
    private void processSupportingCommands(String keyWord, String[] parameters) {
        switch (keyWord) {
            case "":
                break;
            case "HELP":
                help(parameters);
                break;
            case "LOAD":
                load();
                break;
            case "SAVE":
                save(false);
                break;
            case "EXEC":
                execCommand();
                break;
            case "JAVA":
                javaCommand();
                break;
            case "GET":
                getCommand(parameters);
                break;
            case "ALL":
                allCommand();
                break;
            case "DEL":
                delCommand(parameters);
                break;
            case "RES":
                this.mainTranslator = new Translator();
                break;
            default:
                processBuiltInCommands(keyWord, parameters, false);
        }
    }

    // MODIFIES: this
    // EFFECTS: process inputs and call corresponding built-in commands
    private void processBuiltInCommands(String keyWord, String[] parameters, boolean isInquiring) {
        Command command;
        switch (keyWord) {
            case "ADD":
                command = new Add();
                break;
            case "SUB":
                command = new Subtract();
                break;
            case "MUL":
                command = new Multiply();
                break;
            case "DIV":
                command = new Divide();
                break;
            default:
                System.out.println("Command not supported. Type 'HELP' for more information.");
                return;
        }
        addCommandToTranslator(command, parameters, isInquiring);
    }

    // MODIFIES: this
    // EFFECTS: try to add command to the translator with given parameters.
    //          If isInquiring is true, then provide help for the specific command
    private void addCommandToTranslator(Command command, String[] parameters, boolean isInquiring) {
        if (isInquiring) {
            System.out.print(Translator.getHelp(command));
            return;
        }

        try {
            command.input(convertInputsToDataType(parameters));
            mainTranslator.addCommand(command);
        } catch (InvalidArgumentException e) {
            System.out.println(cleanExceptionMessage(e.toString()));
        } catch (NumberFormatException e) {
            System.out.println("WrongArgumentTypeException: Given input is not a number");
        }
    }

    // EFFECTS: provide help to the user
    private void help(String[] parameters) {
        if (parameters.length == 0) {
            System.out.println(Translator.getHelp());
        } else {
            processBuiltInCommands(parameters[0], new String[0], true);
        }
    }

    // MODIFIES: this
    // EFFECTS: load the saved progress to the program
    private void load() {
        System.out.println("Loading saved progress");
        this.mainTranslator = new Translator();
        boolean isSuccessful = false;

        try {
            List<Command> loadedCommands = loader.read();
            for (Command command : loadedCommands) {
                mainTranslator.addCommand(command);
            }
            isSuccessful = true;
        } catch (CorruptedFileWarning e) {
            if (warn(e, "Would you like to wipe out the saved file?")) {
                clearSavedFile();
            } else if (warn(e, "Would you like to end the program and recover manually?")) {
                stop();
            }
            isSuccessful = false;
        }
        System.out.println(isSuccessful ? "The program has been loaded successfully" : "Loading has been canceled");
    }

    // EFFECTS: save the current progress to a file
    private void save(boolean isForcedWriting) {
        System.out.println("Saving the progress");
        boolean isSuccessful = false;
        try {
            saver.write(mainTranslator.getStream(), isForcedWriting);
            isSuccessful = true;
        } catch (CorruptedFileWarning e) {
            warn(e);
            isSuccessful = false;
        } catch (LoseProgressWarning e) {
            if (warn(e, "Would you like to continue?")) {
                save(true);
                return;
            } else {
                isSuccessful = false;
            }
        }
        System.out.println(isSuccessful ? "The program has been saved successfully" : "Saving has been canceled.");
    }

    // EFFECTS: wipe out the progress in the saved file
    private void clearSavedFile() {
        Saver saver = new Saver(savedFileName);
        try {
            saver.write(new LinkedList<Command>(), true);
        } catch (WarningException e) {
            throw new RuntimeException(e); // no exception should be raised
        }
    }

    // EFFECTS: warn the user about a specific issue
    private void warn(WarningException e) {
        System.out.println(e.getMessage());
    }

    // EFFECTS: warn the user about a specific issue and take in Yes/No
    //          input according to caller's course of action.
    private boolean warn(WarningException e, String action) {
        System.out.println(e.getMessage());
        System.out.println(action + " (YES/NO)");
        String response = takeInput()[0];
        while (!response.equals("YES") && !response.equals("NO")) {
            System.out.println("Invalid input. Please try again.");
            response = takeInput()[0];
        }

        return response.equals("YES");
    }

    // MODIFIES: this
    // EFFECTS: execute the commands and print the result
    private void execCommand() {
        try {
            mainTranslator.executeStream();
            System.out.print(mainTranslator.getResults());
        } catch (MissingCommandsException | NotYetExecutedException e) {
            System.out.println(cleanExceptionMessage(e.toString()));
        }
    }

    // EFFECTS: print the Java representation of the commands
    private void javaCommand() {
        try {
            System.out.print(mainTranslator.translateToJava());
        } catch (MissingCommandsException e) {
            System.out.println(cleanExceptionMessage(e.toString()));
        }
    }

    // EFFECTS: print a specific command at the given index
    private void getCommand(String[] parameters) {
        int idx;
        try {
            idx = Integer.parseInt(parameters[0]);
            System.out.println(mainTranslator.getStringCommandAtIndex(idx));
        } catch (NumberFormatException e) {
            System.out.println("WrongArgumentTypeException: Given input is not a number");
        } catch (CommandNotFoundException e) {
            System.out.println(cleanExceptionMessage(e.toString()));
        }
    }

    // EFFECTS: print all the input command.
    private void allCommand() {
        try {
            System.out.print(mainTranslator.getStringStream());
        } catch (MissingCommandsException e) {
            System.out.println(cleanExceptionMessage(e.toString()));
        }
    }

    // MODIFIES: this
    // EFFECTS: delete command at a given index and print the new commands
    private void delCommand(String[] parameters) {
        int idx;
        try {
            idx = Integer.parseInt(parameters[0]);
            System.out.print(mainTranslator.deleteCommandAtIndex(idx));
        } catch (NumberFormatException e) {
            System.out.println("WrongArgumentTypeException: Given input is not a number");
        } catch (CommandNotFoundException | MissingCommandsException e) {
            System.out.println(cleanExceptionMessage(e.toString()));
        }
    }

    // EFFECTS: convert the inputs to numbers. If the input is not a valid number, throw
    //          NumberFormatException
    private DataType[] convertInputsToDataType(String[] inputs) throws NumberFormatException {
        List<DataType> convertedInputs = new ArrayList<>();

        for (String input : inputs) {
            int tempNum = Integer.parseInt(input);
            convertedInputs.add(new DataType(tempNum));
        }

        return convertedInputs.toArray(new DataType[0]);
    }

    // EFFECTS: clean the exception message to a more readable state
    private String cleanExceptionMessage(String msg) {
        return msg.replaceAll("except.", "");
    }

    // EFFECTS: take in input and return it as an array of string
    private String[] takeInput() {
        System.out.print("> ");
        String input = scanner.nextLine().trim().toUpperCase();
        return input.split(" ");
    }

    // MODIFIES: this
    // EFFECTS: display the start menu. Also prompt use the option to load saved program
    private void startMenu() {
        String menu = ""
                + "##########################################################################\n"
                + "#                         Welcome to BlockJava                           #\n"
                + "# This is a program that helps introduce user to programming as well as  #\n"
                + "# Java. Type 'HELP' to see the  the available commands.                  #\n"
                + "#                                                                        #\n"
                + "#                                                                        #\n"
                + "# **Note** BlockJava is still in early development. Expect full version  #\n"
                + "# with graphical interface soon!!                                        #\n"
                + "##########################################################################\n";
        System.out.print(menu);
        if (!saver.isFileEmpty()
                && warn(new WarningException("Saved progress detected."), "Would you like to load it?")) {
            load();
        }
    }

    // EFFECTS: display the end menu when the program ends. Also prompt the user to save the changes
    //          they made to the program
    private void endMenu() {
        if (!mainTranslator.isStreamEmpty() && warn(new WarningException("New changes are made to the program"),
                "Would you like to save it?")) {
            save(false);
        }

        String menu = "\n"
                + "Thank you for using the program\n"
                + "Hope you learned something new!!";
        System.out.println(menu);
    }
}
