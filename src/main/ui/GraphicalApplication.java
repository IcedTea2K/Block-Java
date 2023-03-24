package ui;

import model.Translator;
import ui.tools.CommandLabel;

import javax.swing.*;
import java.awt.*;

// Main class for controlling the GUI
public class GraphicalApplication extends JFrame {
    public static final int WIDTH = 980;
    public static final int HEIGHT = 750;
    private JPanel mainPane;

    private Translator translator;

    // EFFECTS: initialize the GUI
    public GraphicalApplication() {
        initializeGraphics();
        initializeFields();
    }

    // MODIFIES: this
    // EFFECTS: initialize the necessary fields
    private void initializeFields() {
        translator = new Translator();
    }

    // MODIFIES: this
    // EFFECTS: initialize the graphics for the program
    private void initializeGraphics() {
        setLayout(new BorderLayout());
        setSize(WIDTH, HEIGHT);
        setResizable(false);
        centerOnScreen();

        setPanes();

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }

    // MODIFIES: this
    // EFFECTS: set the different views in the application
    private void setPanes() {
        mainPane = new JPanel();
        mainPane.setLayout(new BoxLayout(mainPane, BoxLayout.X_AXIS));
        mainPane.setBorder(BorderFactory.createEmptyBorder());
        setSideBar();
        setEditor();
        add(mainPane);
    }

    // MODIFIES: this
    // EFFECTS: set the sidebar of the program. It contains the commands and the buttons
    private void setSideBar() {
        JPanel sidePane = new JPanel();
        sidePane.setLayout(new BoxLayout(sidePane, BoxLayout.Y_AXIS));
        sidePane.setBackground(new Color(10597848));
        sidePane.setPreferredSize(new Dimension(WIDTH / 8, HEIGHT));
        addCommandPane(sidePane);
        addButtonPane(sidePane);
        mainPane.add(sidePane, BorderLayout.WEST);
    }

    // MODIFIES: this
    // EFFECTS: the main view where the user interact with the program
    private void setEditor() {
        JPanel editorPane = new JPanel();
        editorPane.setLayout(new BoxLayout(editorPane, BoxLayout.Y_AXIS));
        setUpperPane(editorPane);
        setLowerPane(editorPane);
        mainPane.add(editorPane, BorderLayout.EAST);
    }

    // MODIFIES: this
    // EFFECTS: set the upper area of the main editor view
    private void setUpperPane(JPanel editorPane) {
        JPanel upperPane = new JPanel();
        upperPane.setLayout(new BoxLayout(upperPane, BoxLayout.X_AXIS));
        addTranslatorPane(upperPane);
        addTerminalPane(upperPane);
        editorPane.add(upperPane, BorderLayout.NORTH);
    }

    // MODIFIES: this
    // EFFECTS: set the lower pane of the editor view
    private void setLowerPane(JPanel editorPane) {
        JPanel lowerPane = new JPanel();
        lowerPane.setLayout(new BoxLayout(lowerPane, BoxLayout.X_AXIS));
        addJavaPane(lowerPane);
        editorPane.add(lowerPane, BorderLayout.NORTH);
    }

    // MODIFIES: this
    // EFFECTS: add the translator pane
    private void addTranslatorPane(JPanel container) {
        JPanel translatorPane = new JPanel();
        translatorPane.setPreferredSize(new Dimension(300, 300));
        translatorPane.setBackground(new Color(16776439));

        translatorPane.add(new JLabel("Translator"));
        container.add(translatorPane, BorderLayout.WEST);
    }

    // MODIFIES: this
    // EFFECTS: add the terminal pane
    private void addTerminalPane(JPanel container) {
        JPanel terminalPane = new JPanel();
        terminalPane.setPreferredSize(new Dimension(450, 380));
        terminalPane.setBackground(new Color(4607316));

        terminalPane.add(new JLabel("Terminal"));
        container.add(terminalPane, BorderLayout.EAST);
    }

    // MODIFIES: this
    // EFFECTS: add the java pane
    private void addJavaPane(JPanel container) {
        JPanel javaPane = new JPanel();
        javaPane.setPreferredSize(new Dimension(300, 300));
        javaPane.setBackground(new Color(7570064));

        javaPane.add(new JLabel("Java View"));
        container.add(javaPane, BorderLayout.EAST);
    }

    // MODIFIES: this
    // EFFECTS: add area that contains all the commands
    private void addCommandPane(JPanel container) {
        JPanel commandPane = new JPanel();
        commandPane.setPreferredSize(new Dimension(300, 300));
        commandPane.add(new JLabel("Commands"));

        commandPane.add(new CommandLabel(CommandLabel.CommandType.ADD));
        commandPane.add(new CommandLabel(CommandLabel.CommandType.SUB));
        commandPane.add(new CommandLabel(CommandLabel.CommandType.DIV));
        commandPane.add(new CommandLabel(CommandLabel.CommandType.MUL));

        container.add(commandPane, BorderLayout.NORTH);
    }

    // MODIFIES: this
    // EFFECTS: add area that contains all the buttons
    private void addButtonPane(JPanel container) {
        JPanel buttonPane = new JPanel();
        buttonPane.setPreferredSize(new Dimension(300, 300));

        buttonPane.add(new JLabel("Buttons"));
        container.add(buttonPane, BorderLayout.NORTH);
    }

    // MODIFIES: this
    // EFFECTS: center the application on the screen
    private void centerOnScreen() {
        int width = Toolkit.getDefaultToolkit().getScreenSize().width;
        int height = Toolkit.getDefaultToolkit().getScreenSize().height;
        setLocation((width - getWidth()) / 2, (height - getHeight()) / 2);
    }
}
