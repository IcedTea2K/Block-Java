package ui.tools;

import ui.GraphicalApplication;

import javax.swing.*;
import java.awt.*;

// Tools that are used in the application
public abstract class Tool extends JButton {
    protected GraphicalApplication gui;

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
