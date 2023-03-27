package ui.tools;

import javax.swing.*;
import java.awt.*;
import java.awt.dnd.DnDConstants;
import java.awt.dnd.DropTarget;

// A view within the application
public class ViewPanel extends JPanel {
    private DropTarget dropTarget;
    private DropHandler dropHandler;

    public ViewPanel(Dimension size, Color color, String label) {
        setPreferredSize(size);
        setBackground(color);
        add(new JLabel(label));
    }

    @Override
    public void addNotify() {
        super.addNotify();
        this.dropHandler = new DropHandler();
        this.dropTarget = new DropTarget(this, DnDConstants.ACTION_MOVE, dropHandler, true);
    }

    @Override
    public void removeNotify() {
        super.removeNotify();
        dropTarget.removeDropTargetListener(dropHandler);
    }
}
