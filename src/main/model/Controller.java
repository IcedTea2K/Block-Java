package model;

import java.util.List;

// Type of commands that control the flow of the user's program
public abstract class Controller implements Command {
    // EFFECT: return command stream that this controller contains
    public abstract List<Command> getStream();

    @Override
    // EFFECTS: return the constraints specific to this command
    public String getConstraints() {
        return "Controllers can accept an arbitrary number of commands";
    }
}
