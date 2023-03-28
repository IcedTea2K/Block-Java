package ui.tools;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class DeleteTool extends Tool {
    public DeleteTool() {
        super("Delete");
    }

    @Override
    // EFFECTS: add a customized listener to the button
    protected void addListener() {
        addActionListener(new DeleteToolHandler());
    }

    // Handler for the delete button
    private static class DeleteToolHandler implements ActionListener {
        @Override
        // EFFECTS: delete a label when clicked
        public void actionPerformed(ActionEvent e) {
            System.out.println(e);
        }
    }
}
