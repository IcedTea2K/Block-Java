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
    protected static GraphicalApplication gui;
    protected DragGestureRecognizer dragRecognizer;
    protected DragGestureHandler dragHandler;
    protected JTextField leftTextField;
    protected JTextField rightTextField;

    public CommandLabel(String label, CommandType commandType, GraphicalApplication gui) {
        this.commandType = commandType;
        CommandLabel.gui = gui;
        this.label = label;

        initializeFields();
        initializeLabel();
    }

    // MODIFIES: this
    // EFFECTS: initialize the fields of the class
    private void initializeFields() {
        leftTextField = new JTextField(3);
        rightTextField = new JTextField(3);
    }

    // MODIFIES: this
    // EFFECTS: create all the necessary components
    private void initializeLabel() {
        setMaximumSize(new Dimension(150, 50));
        add(leftTextField, BorderLayout.WEST);
        add(new JLabel(this.label), BorderLayout.CENTER);
        add(rightTextField, BorderLayout.EAST);
        setBackground(Color.gray);
    }

    // EFFECTS: generate a similar label but it's movable
    public MovableCommandLabel generateMovableLabel() {
        return new MovableCommandLabel(this.label, this.commandType);
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
}
