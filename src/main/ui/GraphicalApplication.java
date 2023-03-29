package ui;

import except.MissingArgumentException;
import except.MissingCommandsException;
import except.NotYetExecutedException;
import model.Command;
import model.Translator;
import ui.tools.*;

import javax.swing.*;
import java.awt.*;

// Main class for controlling the GUI
public class GraphicalApplication extends JFrame {
    public static final int WIDTH = 980;
    public static final int HEIGHT = 750;
    private JPanel mainPane;
    private TerminalView terminalView;
    private TranslatorView translatorView;
    private JavaView javaView;

    private DeleteTool deleteTool;
    Translator translator;

    // EFFECTS: initialize the GUI
    public GraphicalApplication() {
        initializeGraphics();
    }

    // MODIFIES: this
    // EFFECTS: execute the commands in the translator
    public void executeTranslator() {
        translator = new Translator();
        Component[] components = translatorView.getComponents();
        for (Component component : components) {
            if (component instanceof MovableCommandLabel) {
                translator.addCommand(((MovableCommandLabel) component).getCommand());
            }
        }

        try {
            translator.executeStream();
            terminalView.clearTerminal();
            terminalView.print(translator.getResults(), false);
        } catch (MissingCommandsException | MissingArgumentException | NotYetExecutedException e) {
            reportException(e);
        }
    }

    // EFFECTS: add the java representation to the Java view
    public void javify() {
        if (translator == null) {
            javaView.printJava("");
            return;
        }

        try {
            javaView.printJava(translator.translateToJava());
        } catch (MissingCommandsException e) {
            reportException(e);
        }
    }

    private void reportException(Exception e) {
        terminalView.print(e.toString().replaceAll("except.", "") + "\n", true);
    }

    // EFFECTS: print log message to terminal
    public void printToTerminal(String logMessage) {
        terminalView.print(logMessage, true);
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
        translatorView = new TranslatorView(new Dimension(300, 300), new Color(16776439),
                "Translator");
        container.add(translatorView, BorderLayout.WEST);
    }

    // MODIFIES: this
    // EFFECTS: add the terminal pane
    private void addTerminalPane(JPanel container) {
        terminalView = new TerminalView(new Dimension(450, 380), new Color(4607316),
                "Terminal");
        container.add(terminalView, BorderLayout.EAST);
    }

    // MODIFIES: this
    // EFFECTS: add the java pane
    private void addJavaPane(JPanel container) {
        javaView = new JavaView(new Dimension(300, 300), new Color(7570064),
                "Java View");
        container.add(javaView, BorderLayout.EAST);
    }

    // MODIFIES: this
    // EFFECTS: add area that contains all the commands
    private void addCommandPane(JPanel container) {
        JPanel commandPane = new ViewPanel(new Dimension(300, 300), null,
                "Commands");

        commandPane.add(new CommandLabel("ADD", CommandType.ADD, this));
        commandPane.add(Box.createRigidArea(new Dimension(10, 2)));
        commandPane.add(new CommandLabel("SUB", CommandType.SUB, this));
        commandPane.add(Box.createRigidArea(new Dimension(10, 2)));
        commandPane.add(new CommandLabel("DIV", CommandType.DIV, this));
        commandPane.add(Box.createRigidArea(new Dimension(10, 2)));
        commandPane.add(new CommandLabel("MUL", CommandType.MUL, this));
        commandPane.add(Box.createRigidArea(new Dimension(10, 2)));

        container.add(commandPane, BorderLayout.NORTH);
    }

    // MODIFIES: this
    // EFFECTS: add area that contains all the buttons
    private void addButtonPane(JPanel container) {
        JPanel buttonPane = new ViewPanel(new Dimension(300, 300), null,
                "Buttons");
        deleteTool = new DeleteTool();
        buttonPane.add(deleteTool);
        buttonPane.add(new ExecuteTool(this));
        buttonPane.add(new JavaTool(this));
        container.add(buttonPane, BorderLayout.NORTH);
    }

    // EFFECTS: return true if the it's in delete mode
    public boolean isInDeleteMode() {
        return deleteTool.isActive();
    }

    // MODIFIES: this
    // EFFECTS: center the application on the screen
    private void centerOnScreen() {
        int width = Toolkit.getDefaultToolkit().getScreenSize().width;
        int height = Toolkit.getDefaultToolkit().getScreenSize().height;
        setLocation((width - getWidth()) / 2, (height - getHeight()) / 2);
    }

}
