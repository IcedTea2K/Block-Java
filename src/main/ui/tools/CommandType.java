package ui.tools;

import model.*;

public enum CommandType {
    ADD, SUB, MUL, DIV, EQUAL, LARGER, SMALLER, AND, OR;

    // EFFECTS: return a Command corresponding to the given type
    @SuppressWarnings("methodlength")
    public static Command createCommand(CommandType type) {
        Command newCommand = null;
        switch (type) {
            case ADD:
                newCommand = new Add();
                break;
            case SUB:
                newCommand = new Subtract();
                break;
            case MUL:
                newCommand = new Multiply();
                break;
            case DIV:
                newCommand = new Divide();
                break;
            case EQUAL:
                newCommand = new Equal();
                break;
            case LARGER:
                newCommand = new Larger();
                break;
            case SMALLER:
                newCommand = new Smaller();
                break;
            case AND:
                newCommand = new And();
                break;
            case OR:
                newCommand = new Or();
                break;
        }
        return newCommand;
    }
}
