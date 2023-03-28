package ui.tools;

import javax.swing.*;
import java.awt.*;
import java.awt.datatransfer.Transferable;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.awt.dnd.*;
import java.io.IOException;

// The class to handle the drop operation
public class DropHandler implements DropTargetListener {
    @Override
    // EFFECTS: Called while a drag operation is ongoing, when the mouse pointer enters
    //          operable part of the drop site
    public void dragEnter(DropTargetDragEvent dtde) {
        if (dtde.isDataFlavorSupported(MovableCommandLabel.MetaData)) {
            dtde.acceptDrag(DnDConstants.ACTION_MOVE);
        } else {
            dtde.rejectDrag();
        }
    }

    @Override
    public void dragOver(DropTargetDragEvent dtde) {

    }

    @Override
    public void dropActionChanged(DropTargetDragEvent dtde) {

    }

    @Override
    public void dragExit(DropTargetEvent dte) {

    }

    @Override
    // EFFECTS: drop the content at the drop site
    public void drop(DropTargetDropEvent dtde) {
        boolean success = false;
        if (dtde.isDataFlavorSupported(MovableCommandLabel.MetaData)) {
            Transferable transferable = dtde.getTransferable();

            try {
                Object content = transferable.getTransferData(MovableCommandLabel.MetaData);
                DropTargetContext dropTargetContext = dtde.getDropTargetContext();
                Component dropTarget = dropTargetContext.getComponent();

                if (content instanceof CommandLabel && dropTarget instanceof Container) {
                    swapParent((JPanel) content, (Container) dropTarget);
                    ((MovableCommandLabel) content).activateLabel();

                    success = true;
                    dtde.acceptDrop(DnDConstants.ACTION_MOVE);
                } else {
                    success = false;
                }
            } catch (UnsupportedFlavorException | IOException e) {
                success = false;
            }
        }

        if (!success) {
            dtde.rejectDrop();
        }

        dtde.dropComplete(success);
    }

    // MODIFIES: content and newContainer
    // EFFECTS: remove the content from the current container and add to the new container
    private void swapParent(JPanel content, Container newContainer) {
        Container oldContainer = content.getParent();
        if (oldContainer != null) {
            oldContainer.remove(content);
            updateComponent(oldContainer);
        }
        newContainer.add(content);
        updateComponent(newContainer);
    }

    // MODIFIES: component
    // EFFECTS: update the given JComponent
    private void updateComponent(Container component) {
        component.invalidate();
        component.repaint();
    }
}
