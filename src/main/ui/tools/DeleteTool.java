package ui.tools;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class DeleteTool extends Tool {
    private boolean state;

    public DeleteTool() {
        super("Delete");
        state = false;
        changeColor();
    }

    // EFFECTS: change the background to red if the button is active
    private void changeColor() {
        if (isActive()) {
            System.out.println("doesn't work");
            setBackground(Color.RED);
        } else {
            setBackground(Color.WHITE);
        }
        System.out.println(isActive());
    }

    @Override
    // EFFECTS: add a customized listener to the button
    protected void addListener() {
        addActionListener(new DeleteToolHandler());
    }

    // EFFECTS: return the state of the button
    public boolean isActive() {
        return state;
    }

    // Handler for the delete button
    private class DeleteToolHandler implements ActionListener {
        @Override
        // EFFECTS: delete a label when clicked
        public void actionPerformed(ActionEvent e) {
            state = !state;
            changeColor();
        }
    }
}
