package ui.tools;

import ui.GraphicalApplication;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

// button to get the java version
public class JavaTool extends Tool {
    private GraphicalApplication gui;

    public JavaTool(GraphicalApplication gui) {
        super("Java");
        this.gui = gui;
    }

    @Override
    // EFFECTS: add a customized listener to the button
    protected void addListener() {
        addActionListener(new JavaToolHandler());
    }

    // EFFECTS: handler fo the java button
    private class JavaToolHandler implements ActionListener {
        @Override
        // EFFECTS: add the java version of the commands to the Java view
        public void actionPerformed(ActionEvent e) {
            gui.javify();
        }
    }
}
