package ui.tools;

import model.*;
import ui.GraphicalApplication;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class CommandTool extends JPanel {
    private boolean isFixed;
    private CommandType commandType;
    private Command command;

    // EFFECTS: create a command. It can either be fixed or not fixed
    public CommandTool(boolean isFixed, CommandType commandType) {
        this.isFixed = isFixed;
        this.commandType = commandType;
        initializeTool();
    }

    // EFFECTS: create all the necessary components
    private void initializeTool() {
        addMouseListener(new CommandToolListener());
        add(new JLabel("Add Command"));
        setBackground(Color.gray);

        setCommand();
    }

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
            if (isFixed) {

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
