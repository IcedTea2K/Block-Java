package ui.tools;

import javax.swing.*;
import java.awt.*;

// Tools that are used in the application
public abstract class Tool extends JButton {
    public Tool(String label) {
        super(label);
        addListener();
        setBorderPainted(false);
        setOpaque(true);
        setBackground(Color.white);
    }

    // EFFECTS: add a customized listener to the button
    protected abstract void addListener();
}
