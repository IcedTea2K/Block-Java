package ui.tools;

import javax.swing.*;
import java.awt.*;
import java.awt.datatransfer.Transferable;
import java.awt.dnd.*;

public class DragGestureHandler implements DragGestureListener, DragSourceListener {
    private Container container;
    private JPanel content;

    public DragGestureHandler(JPanel content) {
        this.content = content;
    }

    // EFFECTS: return panel the handler is currently holding
    public JPanel getContent() {
        return content;
    }

    // MODIFIES: this
    // EFFECTS: set the container that is holding the content
    public void setContainer(Container container) {
        this.container = container;
    }

    // EFFECTS: return the container that is holding the content
    public Container getContainer() {
        return this.container;
    }

    // MODIFIES: this
    // EFFECTS: update the container
    private void updateContainer() {
        container.invalidate();
        container.repaint();
    }

    @Override
    // MODIFIES: this
    // EFFECTS: begin the drag and drop operation
    public void dragGestureRecognized(DragGestureEvent dge) {
        this.container = this.content.getParent();
        this.container.remove(content);

        updateContainer();

        Transferable transferable = new MovablePanel(content);
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
        if (!dsde.getDropSuccess()) {
            this.container.add(this.content);
            updateContainer();
        }
    }
}
