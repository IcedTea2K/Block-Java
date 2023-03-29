package ui.tools;

import model.*;
import ui.GraphicalApplication;

import java.awt.*;
import java.awt.datatransfer.DataFlavor;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.Serializable;

// Movable command label
public class MovableCommandLabel extends CommandLabel {
    public static final DataFlavor MetaData = new DataFlavor(MovableCommandLabel.class,
            "Movable Commands");
//    protected Command command;
    private MouseListener mouseListener;

    public MovableCommandLabel(String label, CommandType commandType, GraphicalApplication gui) {
        super(label, commandType, gui);
    }

    // MODIFIES: this
    // EFFECTS: remove the command and the mouse listener
    public void deactivateLabel() {
        removeMouseListener(this.mouseListener);
//        this.command = null;
        this.mouseListener = null;
    }

    // MODIFIES: this
    // EFFECTS: activate the label
    public void activateLabel() {
        this.mouseListener = new CommandLabelListener();
        addMouseListener(this.mouseListener);
//        this.command = CommandType.createCommand(this.commandType);
    }

    // EFFECTS: custom class for mouse listener
    private class CommandLabelListener implements MouseListener, Serializable {
        @Override
        // EFFECTS: if the program is in delete mode, when clicked, the command will be removed
        public void mouseClicked(MouseEvent e) {
            System.out.println("wohooo");
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
