package de.xonibo.stickes;

import javax.swing.DefaultComboBoxModel;

public class LSList extends DefaultComboBoxModel<LSEntry> {

    @Override
    public LSEntry getSelectedItem() {
        return (LSEntry) super.getSelectedItem();
    }

}
