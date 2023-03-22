package ui;

import javax.swing.*;
import java.awt.*;

// Main class for controlling the GUI
public class GraphicalApplication extends JFrame {
    public static final int WIDTH = 980;
    public static final int HEIGHT = 750;

    // EFFECTS: initialize the GUI
    public GraphicalApplication() {
        setLayout(new BorderLayout());
        setSize(WIDTH, HEIGHT);
        setResizable(false);
        centerOnScreen();

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }

    // MODIFIES: this
    // EFFECTS: center the application on the screen
    private void centerOnScreen() {
        int width = Toolkit.getDefaultToolkit().getScreenSize().width;
        int height = Toolkit.getDefaultToolkit().getScreenSize().height;
        setLocation((width - getWidth())/2, (height - getHeight())/2);
    }
}
