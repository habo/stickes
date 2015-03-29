package de.xonibo.stickes.gui;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;

public class TabPanel extends JPanel {

    private final JTabbedPane pane;

    TabPanel(final JTabbedPane pane) {
        this.pane = pane;
        setOpaque(false);
        JLabel label = new JLabel() {
            public String getText() {
                int i = pane.indexOfTabComponent(TabPanel.this);
                if (i != -1) {
                    return pane.getTitleAt(i);
                }
                return null;
            }
        };

        add(label);
    }

}
