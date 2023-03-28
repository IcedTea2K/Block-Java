package ui.tools;

import model.*;
import ui.GraphicalApplication;

import java.awt.datatransfer.DataFlavor;
import java.awt.dnd.DnDConstants;
import java.awt.dnd.DragGestureRecognizer;
import java.awt.dnd.DragSource;

// Movable command label
public class MovableCommandLabel extends CommandLabel {
    public static final DataFlavor MetaData = new DataFlavor(MovableCommandLabel.class,
            "Movable Commands");
    private DragGestureRecognizer dragRecognizer;
    private DragGestureHandler dragHandler;

    public MovableCommandLabel(CommandType commandType, GraphicalApplication gui) {
        super(commandType, gui);
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
