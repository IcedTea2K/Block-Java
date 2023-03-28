package ui.tools;

import ui.GraphicalApplication;

import javax.swing.*;
import java.awt.*;
import java.awt.dnd.DnDConstants;
import java.awt.dnd.DragGestureRecognizer;
import java.awt.dnd.DragSource;

// Label that stays in the command pane area
public class CommandLabel extends JPanel {
    protected CommandType commandType;
    protected String label;
    protected GraphicalApplication gui;
    protected DragGestureRecognizer dragRecognizer;
    protected DragGestureHandler dragHandler;

    public CommandLabel(String label, CommandType commandType, GraphicalApplication gui) {
        this.commandType = commandType;
        this.gui = gui;
        this.label = label;

        initializeLabel();
    }

    // MODIFIES: this
    // EFFECTS: create all the necessary components
    private void initializeLabel() {
        setPreferredSize(new Dimension(150, 50));
        add(new JTextField(3), BorderLayout.WEST);
        add(new JLabel(this.label), BorderLayout.CENTER);
        add(new JTextField(3), BorderLayout.EAST);
        setBackground(Color.gray);
    }

    public MovableCommandLabel generateMovableLabel() {
        return new MovableCommandLabel(this.label, this.commandType, this.gui);
    }

    @Override
    // MODIFIES: this
    // EFFECTS: initialize drag handler when the panel has a container
    public void addNotify() {
        super.addNotify();

        if (dragRecognizer == null) {
            dragHandler = new DragGestureHandler(this);
            dragRecognizer = DragSource.getDefaultDragSource().createDefaultDragGestureRecognizer(
                    this, DnDConstants.ACTION_MOVE, dragHandler);
        }
    }

    @Override
    // MODIFIES: this
    // EFFECTS: remove drag handlers when the panel is removed from a container
    public void removeNotify() {
        if (dragRecognizer != null) {
            dragRecognizer.removeDragGestureListener(dragHandler);
            dragHandler = null;
        }
        dragRecognizer = null;

        super.removeNotify();
    }
}
