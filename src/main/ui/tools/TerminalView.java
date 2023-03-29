package ui.tools;

import javax.swing.*;
import java.awt.*;

// View for the terminal in the program
public class TerminalView extends  ViewPanel {
    private JTextArea terminal;
    public TerminalView(Dimension size, Color color, String label) {
        super(size, color, label);
        terminal = new JTextArea();
        add(terminal);
    }

    // EFFECTS: add log message to the terminal
    public void print(String msg) {
        terminal.append(msg);
    }
}
