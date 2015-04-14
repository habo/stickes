package de.xonibo.stickes.gui.ls;

import java.util.Comparator;

public class LSEntryComparator implements Comparator<LSEntry> {

    @Override
    public int compare(LSEntry o1, LSEntry o2) {
        return o1.getName().compareTo(o2.getName());
    }

}
