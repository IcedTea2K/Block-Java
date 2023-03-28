package ui.tools;

import javax.swing.*;
import java.awt.event.ActionListener;

// Tools that are used in the application
public abstract class Tool extends JButton {
    public Tool(String label) {
        super(label);
        addListener();
    }

    // EFFECTS: add a customized listener to the button
    protected abstract void addListener();
}
