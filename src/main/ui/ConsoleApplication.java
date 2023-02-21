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

    // EFFECTS: process the inputs and call the corresponding commands
    private void processSupportingCommands(String keyWord, String[] parameters) {
        switch (keyWord) {
            case "HELP":
                break;
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
                break;
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
                + "#########################################################\n"
                + "#                Welcome to BlockJava                   #\n"
                + "# This is a program that helps introduce user to        #\n"
                + "# programming as well as Java. Type 'HELP' to see the   #\n"
                + "# the available commands.                               #\n"
                + "#                                                       #\n"
                + "# **Note** BlockJava is still in early development.     #\n"
                + "# Full version with graphical interface is coming soon. #\n"
                + "#########################################################\n";
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
