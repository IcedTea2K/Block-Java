package ui.tools;

import model.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

// Label that stays in the command pane area
public class CommandLabel extends JPanel {
    private CommandType commandType;
    protected Command command;

    public CommandLabel(CommandType commandType) {
        this.commandType = commandType;

        addCommand();
        initializeTool();
    }

    // MODIFIES: this
    // EFFECTS: create all the necessary components
    private void initializeTool() {
        addMouseListener(new CommandToolListener());
        setPreferredSize(new Dimension(100, 30));
        add(new JLabel(command.getHeader()));
        setBackground(Color.gray);
    }

    // EFFECTS: get the label (as a string)
    public String getLabel() {
        return command.getHeader();
    }

    // MODIFIES: this
    // EFFECTS: remove the command
    public void removeCommand() {
        command = null;
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
            System.out.println(getLabel());
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
