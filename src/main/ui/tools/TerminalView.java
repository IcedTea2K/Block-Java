package ui.tools;

import javax.swing.*;
import java.awt.*;

// View for the terminal in the program
public class TerminalView extends  ViewPanel {
    private JTextArea terminal;

    public TerminalView(Dimension size, Color color, String label) {
        super(size, color, label);
        terminal = new JTextArea();
        terminal.setOpaque(true);
        terminal.setEditable(false);
        terminal.setBackground(color);
        add(terminal);
    }

    // MODIFIES: this
    // EFFECTS: add log message to the terminal
    public void print(String msg, boolean withIndicator) {
        if (withIndicator) {
            for (String str : msg.split("\n")) {
                terminal.append(">" + str + "\n");
            }
        } else {
            terminal.append(msg);
        }
    }

    // MODIFIES: this
    // EFFECTS: clear the terminal
    public void clearTerminal() {
        terminal.setText("");
    }
}
