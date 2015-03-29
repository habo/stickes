package de.xonibo.stickes.gui;

import java.awt.Dimension;
import javax.swing.*;
import javax.swing.JTabbedPane;

public class ExamplesOptionDialog extends JFrame {

    private final int tabNumber = 5;
    private final JTabbedPane pane = new JTabbedPane();

    public ExamplesOptionDialog(String title) {
        super(title);
        // TODO save on close or something, maybe save-yes-no-dialog
        setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        add(pane);
    }

    public void run() {
        pane.removeAll();
        for (int i = 0; i < tabNumber; i++) {
            String title = "Tab " + i;
            pane.add(title, new JLabel(title));
            TabPanel content = new TabPanel(pane);
            pane.setTabComponentAt(i, content);
        }
        pane.setTabLayoutPolicy(JTabbedPane.WRAP_TAB_LAYOUT);
        setSize(new Dimension(400, 200));
        setLocationRelativeTo(null);
        setVisible(true);
    }
}
