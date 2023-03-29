package ui.tools;

import ui.GraphicalApplication;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

// Execute button and its functionality
public class ExecuteTool extends Tool {
    private GraphicalApplication gui;

    public ExecuteTool(GraphicalApplication gui) {
        super("Execute");
        this.gui = gui;
    }

    @Override
    // EFFECTS: add a customized listener to the button
    protected void addListener() {
        addActionListener(new ExecuteToolHandler());
    }

    private class ExecuteToolHandler implements ActionListener {
        @Override
        // MODIFIES: gui
        // EFFECTS: execute the program when the button is pressed
        public void actionPerformed(ActionEvent e) {
            gui.executeTranslator();
        }
    }
}
