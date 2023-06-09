package ui.tools;

import java.awt.*;
import java.awt.dnd.DnDConstants;
import java.awt.dnd.DropTarget;

// A interactive view within the application
public class TranslatorView extends ViewPanel {
    private DropTarget dropTarget;
    private DropHandler dropHandler;

    public TranslatorView(Dimension size, Color color, String label) {
        super(size, color, label);
        setLayout(new FlowLayout());
    }

    @Override
    // EFFECTS: invoked when this component has a parent
    public void addNotify() {
        super.addNotify();
        this.dropHandler = new DropHandler();
        this.dropTarget = new DropTarget(this, DnDConstants.ACTION_MOVE, dropHandler, true);
    }

    @Override
    // EFFECTS: invoked when this component's parent is removed
    public void removeNotify() {
        super.removeNotify();
        dropTarget.removeDropTargetListener(dropHandler);
    }
}
