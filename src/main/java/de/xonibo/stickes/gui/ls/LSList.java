package de.xonibo.stickes.gui.ls;

import javax.swing.DefaultComboBoxModel;

public class LSList extends DefaultComboBoxModel<LSEntry> {

    @Override
    public LSEntry getSelectedItem() {
        return (LSEntry) super.getSelectedItem();
    }

}
