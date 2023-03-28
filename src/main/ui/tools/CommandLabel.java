package ui.tools;

import model.*;
import ui.GraphicalApplication;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

// Label that stays in the command pane area
public class CommandLabel extends JPanel {
    private CommandType commandType;
    protected Command command;
    private static GraphicalApplication gui;
    private MouseListener mouseListener;

    public CommandLabel(CommandType commandType, GraphicalApplication gui) {
        this.commandType = commandType;
        this.gui = gui;

        initializeLabel();
    }

    // MODIFIES: this
    // EFFECTS: create all the necessary components
    private void initializeLabel() {
        activateLabel();
        setPreferredSize(new Dimension(150, 50));
        add(new JTextField(3), BorderLayout.WEST);
        add(new JLabel(command.getHeader().split(" ")[0]), BorderLayout.CENTER);
        add(new JTextField(3), BorderLayout.EAST);
        setBackground(Color.gray);
    }

    // EFFECTS: get the label (as a string)
    public String getLabel() {
        return command.getHeader();
    }

    // MODIFIES: this
    // EFFECTS: remove the command and the mouse listener
    public void deactivateLabel() {
        removeMouseListener(this.mouseListener);
        this.command = null;
        this.mouseListener = null;
    }

    // MODIFIES: this
    // EFFECTS: activate the label
    public void activateLabel() {
        this.mouseListener = new CommandToolListener();
        addMouseListener(this.mouseListener);
        addCommand();
    }

    // EFFECTS: return true if the label is currently holding a command
    //          return false otherwise
    public boolean hasCommand() {
        return this.command != null;
    }

    // EFFECTS: set the command in this tool
    public void addCommand() {
        switch (commandType) {
            case ADD:
                this.command = new Add();
                break;
            case SUB:
                this.command = new Subtract();
                break;
            case MUL:
                this.command = new Multiply();
                break;
            case DIV:
                this.command = new Divide();
                break;
        }
    }

    public static enum CommandType {
        ADD, SUB, MUL, DIV, EQUAL, LARGER, SMALLER, AND, OR
    }

    // EFFECTS: custom class for mouse listener
    private class CommandToolListener implements MouseListener {
        /**
         * Invoked when the mouse button has been clicked (pressed
         * and released) on a component.
         *
         * @param e the event to be processed
         */
        @Override
        public void mouseClicked(MouseEvent e) {
            if (gui.isInDeleteMode()) {
                Container tempParent = getParent();
                tempParent.remove(CommandLabel.this);
                tempParent.invalidate();
                tempParent.repaint();
            }
        }

        /**
         * Invoked when a mouse button has been pressed on a component.
         *
         * @param e the event to be processed
         */
        @Override
        public void mousePressed(MouseEvent e) {

        }

        /**
         * Invoked when a mouse button has been released on a component.
         *
         * @param e the event to be processed
         */
        @Override
        public void mouseReleased(MouseEvent e) {

        }

        /**
         * Invoked when the mouse enters a component.
         *
         * @param e the event to be processed
         */
        @Override
        public void mouseEntered(MouseEvent e) {

        }

        /**
         * Invoked when the mouse exits a component.
         *
         * @param e the event to be processed
         */
        @Override
        public void mouseExited(MouseEvent e) {

        }
    }
}
