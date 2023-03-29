package ui.tools;

import except.InvalidArgumentException;
import except.MissingArgumentException;
import except.MissingCommandsException;
import model.Command;
import model.DataType;

import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.*;
import java.awt.datatransfer.DataFlavor;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

// Movable command label
public class MovableCommandLabel extends CommandLabel {
    public static final DataFlavor MetaData = new DataFlavor(MovableCommandLabel.class,
            "Movable Commands");
    private MouseListener mouseListener;
    private Command command;
    private DataType leftOperand;
    private DataType rightOperand;
    private Integer index;

    public MovableCommandLabel(String label, CommandType commandType) {
        super(label, commandType, CommandLabel.gui);
        this.index = null;
    }

    // MODIFIES: this
    // EFFECTS: remove the command and the mouse listener
    public void deactivateLabel() {
        removeMouseListener(this.mouseListener);
        this.mouseListener = null;
    }

    // MODIFIES: this
    // EFFECTS: activate the label
    public void activateLabel() {
        this.mouseListener = new CommandLabelListener();
        this.command = CommandType.createCommand(this.commandType);
        this.leftTextField.getDocument().addDocumentListener(new InputListener(true));
        this.rightTextField.getDocument().addDocumentListener(new InputListener(false));
        leftOperand = new DataType(0);
        rightOperand = new DataType(0);
        addMouseListener(this.mouseListener);
    }

    // EFFECTS: return the command this label is holding
    public Command getCommand() {
        return this.command;
    }

    private class InputListener implements DocumentListener {
        private boolean isLeft;

        public InputListener(boolean side) {
            this.isLeft = side;
        }

        /**
         * Gives notification that there was an insert into the document.  The
         * range given by the DocumentEvent bounds the freshly inserted region.
         *
         * @param e the document event
         */
        @Override
        public void insertUpdate(DocumentEvent e) {
            notifyNewInput(isLeft);
        }

        /**
         * Gives notification that a portion of the document has been
         * removed.  The range is given in terms of what the view last
         * saw (that is, before updating sticky positions).
         *
         * @param e the document event
         */
        @Override
        public void removeUpdate(DocumentEvent e) {
            notifyNewInput(isLeft);
        }

        /**
         * Gives notification that an attribute or set of attributes changed.
         *
         * @param e the document event
         */
        @Override
        public void changedUpdate(DocumentEvent e) {

        }
    }

    // EFFECTS: check the new input and print the error message to the terminal view if necessary
    private void notifyNewInput(boolean isLeft) {
        try {
            parseTextFields(isLeft);
            giveInputToCommand();
            gui.printToTerminal("Input added successfully\n");
        } catch (NumberFormatException e) {
            gui.printToTerminal("WrongArgumentTypeException: Given input is not a number\n");
        } catch (InvalidArgumentException | MissingArgumentException e) {
            gui.printToTerminal(e.toString().replaceAll("except.", "") + "\n");
        }
    }

    // EFFECTS: check and parse text fields and assign to appropriate operand
    //          If the text is not integer, throws NumberFormatException
    private void parseTextFields(boolean isLeft) throws NumberFormatException {
        if (isLeft) {
            this.leftOperand =  leftTextField.getText().isEmpty()
                    ? null : new DataType(Integer.parseInt(leftTextField.getText()));
        } else {
            this.rightOperand =  rightTextField.getText().isEmpty()
                    ? null : new DataType(Integer.parseInt(rightTextField.getText()));
        }
    }

    // EFFECTS: add the inputs to the command
    //          If the input type does not match, InvalidArgumentException will be thrown
    //          Otherwise if either left or right operand is null, throw MissingArgumentEcception
    private void giveInputToCommand() throws InvalidArgumentException, MissingArgumentException {
        if (leftOperand == null && rightOperand == null) {
            command.input();
        } else if (leftOperand == null) {
            command.input(rightOperand);
        } else if (rightOperand == null) {
            command.input(leftOperand);
        }
        command.input(this.leftOperand, this.rightOperand);
    }

    // EFFECTS: custom class for mouse listener
    private class CommandLabelListener implements MouseListener {
        @Override
        // EFFECTS: if the program is in delete mode, when clicked, the command will be removed
        public void mouseClicked(MouseEvent e) {
            if (gui.isInDeleteMode()) {
                Container tempParent = getParent();
                tempParent.remove(MovableCommandLabel.this);
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
