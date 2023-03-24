package ui.tools;

import model.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

// Label that stays in the command pane area
public class CommandLabel extends JPanel {
    private CommandType commandType;
    private Command command;

    public CommandLabel(CommandType commandType) {
        this.commandType = commandType;
        setCommand();
        initializeTool();
    }

    // MODIFIES: this
    // EFFECTS: create all the necessary components
    private void initializeTool() {
        addMouseListener(new CommandToolListener());
        add(new JLabel(command.getHeader()));
        setBackground(Color.gray);

        setCommand();
    }

    // EFFECTS: get the label (as a srting)
    public String getLabel() {
        return command.getHeader();
    }

    // MODIFIES: this
    // EFFECTS: create the command this tool is currently representing
    public Command getCommand() {
        return this.command;
    }

    // EFFECTS: set the command in this tool
    private void setCommand() {
        switch (commandType) {
            case ADD:
                command = new Add();
                break;
            case SUB:
                command = new Subtract();
                break;
            case MUL:
                command = new Multiply();
                break;
            case DIV:
                command = new Divide();
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
