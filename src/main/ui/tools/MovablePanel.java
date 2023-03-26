package ui.tools;

import javax.swing.*;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.Transferable;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.awt.dnd.DragGestureRecognizer;
import java.io.IOException;

// Define which type of panel can be moved/transfered
// Can also be used as a wrapper class for the moving panel
public class MovablePanel implements Transferable {
    private DataFlavor[] flavors = new DataFlavor[] {MovableCommandLabel.MetaData};
    private JPanel panel;

    public MovablePanel(JPanel panel) {
        this.panel = panel;
    }

    // EFFECTS: return the panel this class is holding/representing
    public JPanel getPanel() {
        return panel;
    }

    @Override
    // EFFECTS: return the DataFlavors that this MovablePanel supports
    public DataFlavor[] getTransferDataFlavors() {
        return flavors;
    }

    @Override
    // EFFECTS: return true if the given flavor is supported.
    //          return false otherwise
    public boolean isDataFlavorSupported(DataFlavor flavor) {
        for (DataFlavor currentFlavor : this.flavors) {
            if (currentFlavor.equals(flavor)) {
                return true;
            }
        }
        return false;
    }

    // EFFECTS: Returns an object which represents the data to be transferred
    //          If data is no longer available, throw IOException.
    //          Otherwise, if the requested data flavor is not supported, throw UnsupportedFlavorException
    @Override
    public Object getTransferData(DataFlavor flavor) throws UnsupportedFlavorException, IOException {
        Object data = null;
        if (isDataFlavorSupported(flavor)) {
            data = getPanel();
        } else {
            throw new UnsupportedFlavorException(flavor);
        }
        return data;
    }
}
