package de.xonibo.stickes.gui.ls;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Comparator;
import javax.swing.DefaultComboBoxModel;

public class LSList extends DefaultComboBoxModel<LSEntry> {

    private String filename = "lslist.obj";

    private Comparator comparator = null;

    public LSList(LSEntryComparator comparator) {
        super();
        this.comparator = comparator;
    }

    @Override
    public LSEntry getSelectedItem() {
        return (LSEntry) super.getSelectedItem();
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }

    public String getFilename() {
        return filename;
    }

    public void addElementIfNotExists(LSEntry entry) {
        if (containsName(entry.getName())) {
            return;
        }
        addElement(entry);
    }

    @Override
    public final void addElement(LSEntry element) {
        insertElementAt(element, 0);
    }

    @Override
    public void insertElementAt(LSEntry entry, int index) {
        if (entry == null) {
            return;
        }
        int size = getSize();

        for (index = 0; index < size; index++) {
            if (comparator != null) {
                LSEntry o = getElementAt(index);

                if (comparator.compare(o, entry) > 0) {
                    break;
                }
            } else {
                Comparable c = (Comparable) getElementAt(index);

                if (c.compareTo(entry) > 0) {
                    break;
                }
            }
        }

        super.insertElementAt(entry, index);

        if (index == 0) {
            setSelectedItem(entry);
        }
    }

    public void load() throws FileNotFoundException, IOException, ClassNotFoundException {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(filename))) {
            while (ois.available() > -1) {
                LSEntry e = (LSEntry) ois.readObject();
                addElement(e);
            }
        }
    }

    public void save() throws IOException {
        ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(filename));
        for (int i = 0; i < getSize(); i++) {
            LSEntry e = getElementAt(i);
            oos.writeObject(e);
        }
        oos.close();
    }

    public boolean containsName(String name) {
        for (int i = 0; i < getSize(); i++) {
            LSEntry e = getElementAt(i);
            if (e.getName().equals(name)) {
                return true;
            }
        }
        return false;
    }
}
