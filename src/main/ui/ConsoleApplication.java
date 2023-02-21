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
    }

    // EFFECTS: run the program
    private void run() {

    }

    // EFFECTS: process the inputs and call the corresponding commands
    private void processInput() {

    }
}
