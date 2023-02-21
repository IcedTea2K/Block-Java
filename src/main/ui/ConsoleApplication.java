package ui;

import model.Translator;

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
        startMenu();
    }

    // EFFECTS: process the inputs and call the corresponding commands
    private void processInput() {

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
        System.out.println(menu);
    }
}