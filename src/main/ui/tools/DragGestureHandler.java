package ui.tools;

import javax.swing.*;
import java.awt.*;
import java.awt.datatransfer.Transferable;
import java.awt.dnd.*;
import java.io.Serializable;

public class DragGestureHandler implements DragGestureListener, DragSourceListener, Serializable {
    private MovableCommandLabel content;
    private CommandLabel commandLabel;

    public DragGestureHandler(CommandLabel commandLabel) {
        this.commandLabel = commandLabel;
    }

    @Override
    // MODIFIES: this
    // EFFECTS: begin the drag and drop operation
    public void dragGestureRecognized(DragGestureEvent dge) {
        System.out.println("label deactivated");
        this.content = this.commandLabel.generateMovableLabel();
        this.content.deactivateLabel();

        Transferable transferable = new MovablePanel(this.content);
        DragSource dragSource = dge.getDragSource();
        dragSource.startDrag(dge, Cursor.getPredefinedCursor(Cursor.MOVE_CURSOR), transferable, this);
    }

    @Override
    public void dragEnter(DragSourceDragEvent dsde) {

    }

    @Override
    public void dragOver(DragSourceDragEvent dsde) {

    }

    @Override
    public void dropActionChanged(DragSourceDragEvent dsde) {

    }

    @Override
    public void dragExit(DragSourceEvent dse) {

    }

    @Override
    // MODIFIES: this
    // EFFECTS: drop the content in the new container.
    //          If the drop is not successful, return the content to the previous container
    public void dragDropEnd(DragSourceDropEvent dsde) {
//        if (!dsde.getDropSuccess()) {
//            this.content = null;
//        }
        this.content = null;
    }
}
