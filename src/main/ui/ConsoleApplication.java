package ui;

import model.Translator;

import java.util.Arrays;
import java.util.Scanner;

// UI for Block Java on the console
public class ConsoleApplication {
    private Translator mainTranslator;
    private Scanner scanner;

    // EFFECTS: construct and run the application
    public ConsoleApplication() {
        this.mainTranslator = new Translator();
        this.scanner = new Scanner(System.in);

        run();
    }

    // MODIFIES: this
    // EFFECTS: run the program
    private void run() {
        boolean isRunning = true;
        startMenu();

        while (isRunning) {
            System.out.print("> ");
            String[] inputs = takeInput();

            if (inputs[0].equals("QUIT")) {
                isRunning = false;
            }
            processSupportingCommands(inputs[0], Arrays.copyOfRange(inputs, 1, inputs.length));
        }

        endMenu();
    }

    // MODIFIES: this
    // EFFECTS: process the input and call the corresponding supporting commands
    private void processSupportingCommands(String keyWord, String[] parameters) {
        switch (keyWord) {
            case "":
                break;
            case "HELP":
                help(parameters);
            case "EXEC":
                break;
            case "JAVA":
                break;
            case "GET":
                break;
            case "ALL":
                break;
            case "DEL":
                break;
            case "RES":
                break;
            default:
                processBuiltInCommands(keyWord, parameters, false);
        }
    }

    // MODIFIES: this
    // EFFECTS: process inputs and call corresponding built-in commands
    private void processBuiltInCommands(String keyWord, String[] parameters, boolean isInquiring) {
        switch (keyWord) {
            case "ADD":
                break;
            case "SUB":
                break;
            case "MUL":
                break;
            case "DIV":
                break;
            default:
                System.out.println("Command not supported. Type 'HELP' for more information.");;
        }
    }

    private void help(String[] parameters) {
        if (parameters.length == 0) {
            System.out.println(Translator.getHelp());
        }
    }

    // EFFECTS: take in input and return it as an array of string
    private String[] takeInput() {
        String input = scanner.nextLine().strip().toUpperCase();
        return input.split(" ");
    }

    // EFFECTS: display the start menu
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
    }

    // EFFECTS: display the end menu when the program ends
    private void endMenu() {
        String menu = "\n"
                + "Thank you for using the program\n"
                + "Hope you learned something new!!";
        System.out.println(menu);
    }
}
