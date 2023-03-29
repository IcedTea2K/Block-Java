package ui.tools;

import except.CorruptedFileWarning;
import model.Command;
import persistence.Loader;
import ui.GraphicalApplication;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

// Button for loading the saved file
public class LoadTool extends Tool {
    private GraphicalApplication gui;
    private Loader loader;
    private TranslatorView translatorView;

    public LoadTool(GraphicalApplication gui) {
        super("Load");
        this.gui = gui;
        this.loader = new Loader(gui.getSavedFileName());
    }

    @Override
    // MODIFIES: this
    // EFFECTS: add a customized listener for the button
    protected void addListener() {
        addActionListener(new LoadToolHandler());
    }

    // MODIFIES: this
    // EFFECTS: load the saved progress
    private void loadCommands() {
        resetTranslatorView();
        try {
            List<Command> loadedCommands = loader.read();
            for (Command command : loadedCommands) {
                LoadTool.this.translatorView.add(new MovableCommandLabel(command));
            }
            this.translatorView.revalidate();
            this.translatorView.repaint();
        } catch (CorruptedFileWarning ex) {
            gui.reportException(ex);
        }
    }

    // MODIFIES: this
    // EFFECTS: reset the translator view
    private void resetTranslatorView() {
        this.translatorView = this.gui.getTranslatorView();
        Component label = this.translatorView.getComponent(0);
        this.translatorView.removeAll();
        this.translatorView.add(label);
    }

    // Handler for the button
    private class LoadToolHandler implements ActionListener {
        @Override
        // EFFECTS: load the saved progress when the button is pressed
        public void actionPerformed(ActionEvent e) {
            LoadTool.this.loadCommands();
        }
    }
}
