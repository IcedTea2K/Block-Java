package ui.tools;

import model.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

// Label that stays in the command pane area
public class CommandLabel extends JPanel {
    private CommandType commandType;
    private Command exampleCommand;

    public CommandLabel(CommandType commandType) {
        this.commandType = commandType;
        exampleCommand = createCommand();
        initializeTool();
    }

    // MODIFIES: this
    // EFFECTS: create all the necessary components
    private void initializeTool() {
        addMouseListener(new CommandToolListener());
        setPreferredSize(new Dimension(100, 30));
        add(new JLabel(exampleCommand.getHeader()));
        setBackground(Color.gray);

        createCommand();
    }

    // EFFECTS: get the label (as a string)
    public String getLabel() {
        return exampleCommand.getHeader();
    }

    // EFFECTS: set the command in this tool
    private Command createCommand() {
        Command createdCommand = null;
        switch (commandType) {
            case ADD:
                createdCommand = new Add();
                break;
            case SUB:
                createdCommand = new Subtract();
                break;
            case MUL:
                createdCommand = new Multiply();
                break;
            case DIV:
                createdCommand = new Divide();
                break;
        }
        return createdCommand;
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
