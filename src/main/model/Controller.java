package model;

import java.util.List;

// Type of commands that control the flow of the user's program
public abstract class Controller implements Command {
    // EFFECT: return command stream that this controller contains
    public abstract List<Command> getStream();
}
