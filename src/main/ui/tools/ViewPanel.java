package ui.tools;

import javax.swing.*;
import java.awt.*;

// A view in the application. It's non-interactive by default
public class ViewPanel extends JPanel {
    public ViewPanel(Dimension size, Color color, String label) {
        setPreferredSize(size);
        setBackground(color);
        setLayout(new FlowLayout());
        setOpaque(true);
        add(new JLabel(label));
    }
}
