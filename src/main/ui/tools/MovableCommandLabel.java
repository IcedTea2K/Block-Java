package ui.tools;

import model.*;

import java.awt.datatransfer.DataFlavor;

// Movable command label
public class MovableCommandLabel extends CommandLabel {
    public static final DataFlavor MetaData = new DataFlavor(MovableCommandLabel.class,
            "Movable Commands");
    private Command command;

    public MovableCommandLabel(CommandType commandType) {
        super(commandType);
    }
}
