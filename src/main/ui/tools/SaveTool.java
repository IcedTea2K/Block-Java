package ui.tools;

import except.CorruptedFileWarning;
import except.LoseProgressWarning;
import except.MissingArgumentException;
import except.MissingCommandsException;
import model.Command;
import persistence.Saver;
import ui.GraphicalApplication;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

// Button for saving the application
public class SaveTool extends Tool {
    private GraphicalApplication gui;
    private Saver saver;

    public SaveTool(GraphicalApplication gui) {
        super("Save");
        this.gui = gui;
        this.saver = new Saver(gui.getSavedFileName());
    }

    @Override
    // EFFECTS: add a customized listener to the button
    protected void addListener() {
        addActionListener(new SaveToolHandler());
    }

    // handler for the execute button
    private class SaveToolHandler implements ActionListener {
        @Override
        // EFFECTS: save the program when the button is pressed
        public void actionPerformed(ActionEvent event) {
            try {
                saver.write(gui.getTranslatorCommands(), true);
            } catch (CorruptedFileWarning | MissingArgumentException e) {
                gui.reportException(e);
            } catch (LoseProgressWarning exception) {
                throw new RuntimeException(exception);
            }
        }
    }
}
