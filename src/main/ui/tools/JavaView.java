package ui.tools;

import javax.swing.*;
import java.awt.*;

// the Java view of the program
public class JavaView extends ViewPanel {
    private JTextArea textArea;

    public JavaView(Dimension size, Color color, String label) {
        super(size, color, label);
        textArea = new JTextArea();
        textArea.setOpaque(true);
        textArea.setEditable(false);
        textArea.setBackground(color);
        add(textArea);
    }

    // EFFECTS: print the java representation with a template
    public void printJava(String javaMsg) {
        textArea.setText("public class Main {\n"
                + "    public static void main(String[] args) {\n"
                + processJavaMsg(javaMsg)
                + "    }\n"
                + "}");
    }

    // EFFECTS: remove line number and add tabs from the message
    private String processJavaMsg(String javaMsg) {
        if (javaMsg.isEmpty()) {
            return javaMsg;
        }
        String newMsg = "";
        for (String msg : javaMsg.split("\n")) {
            newMsg += "      " + msg.substring(3) + "\n";
        }
        return newMsg;
    }
}
