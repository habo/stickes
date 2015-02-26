package de.xonibo.stickes.awt;

import de.xonibo.stickes.StichData;
import de.xonibo.stickes.format.StichFile;
import de.xonibo.stickes.format.StichFileSave;
import java.awt.FileDialog;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JSeparator;

public class Files implements ActionListener {

    private final Visual visual;

    protected StichFile sf = new StichFile();
    protected JMenuItem menuItemNew = new JMenuItem("New", KeyEvent.VK_N);
    protected List<JMenuItem> menuItemSavelist = createSaveMenuItems();
    protected JMenuItem menuItemLoad = new JMenuItem("Load", KeyEvent.VK_L);
    protected JMenuItem menuItemInfo = new JMenuItem("Info", KeyEvent.VK_I);
    protected JMenuItem menuItemQuit = new JMenuItem("Quit", KeyEvent.VK_I);

    public Files(Visual visual) {
        this.visual = visual;
    }

    public void importFile(File file) throws Exception {
        visual.setStichData(sf.load(file));
        visual.file = file;
    }

    private List<JMenuItem> createSaveMenuItems() {
        List<JMenuItem> list = new ArrayList<>();
        for (StichFileSave sfb : StichFile.getSaveableFileFormats()) {
            JMenuItem i = new JMenuItem("Save as " + sfb.getExtention());
            i.addActionListener(this);
            //i.addKeyListener(this);
            list.add(i);
        }
        return list;
    }

    public JMenu createMenu() {
        JMenu menuFile = new JMenu("File");

        menuFile.add(menuItemNew);
        menuItemNew.addActionListener(this);
//        menuItemNew.addKeyListener(this);
        menuFile.add(new JSeparator());
        menuFile.add(menuItemLoad);
        menuItemLoad.addActionListener(this);
        menuFile.add(menuItemLoad);
//        menuItemLoad.addKeyListener(this);
        for (JMenuItem jmi : menuItemSavelist) {
            menuFile.add(jmi);
        }
        menuFile.add(new JSeparator());
        menuFile.add(menuItemInfo);
        menuItemInfo.addActionListener(this);
//        menuItemInfo.addKeyListener(this);
        menuFile.add(new JSeparator());
        menuFile.add(menuItemQuit);
        menuItemQuit.addActionListener(this);
//        menuItemQuit.addKeyListener(this);

        return menuFile;
    }

    void loadDialog() {
        FileDialog fd = new FileDialog(visual.frame, "Choose a file", FileDialog.LOAD);
        fd.setFilenameFilter(sf.getLoadFilenameFilter());
        fd.setFile("*");
        fd.setVisible(true);
        if (fd.getFiles().length == 0) {
            return;
        }
        File f = fd.getFiles()[0];
        try {
            if (f.exists()) {
                importFile(f);
                visual.init(2);
            } else {
                JOptionPane.showMessageDialog(visual.frame, f.getName() + " does not exist");
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(visual.frame, "error loading " + f.getName() + ": " + ex);
        }
    }

    void saveDialog(String text) {
        try {
            FileDialog fd = new FileDialog(visual.frame, text, FileDialog.SAVE);
            fd.setFilenameFilter(sf.getSaveFilenameFilter());
            fd.setVisible(true);
            if (fd.getFiles().length == 0) {
                return;
            }
            sf.save(fd.getFiles()[0], visual.getStichData());

        } catch (Exception ex) {
            JOptionPane.showMessageDialog(visual.frame, "error during save: " + ex.getMessage());
            Logger
                    .getLogger(Visual.class
                            .getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == menuItemNew) {
            visual.file = null;
            visual.setStichData(new StichData());
            visual.scale = 1;
            visual.init(visual.scale);
            visual.repaint();
        }
        for (JMenuItem mi : menuItemSavelist) {
            if (e.getSource() == mi) {
                saveDialog(mi.getText());
                visual.repaint();
            }
        }

        if (e.getSource()
                == menuItemLoad) {
            loadDialog();
            visual.repaint();
        }

        if (e.getSource()
                == menuItemInfo) {
            info();
            visual.repaint();
        }

        if (e.getSource()
                == menuItemQuit) {
            visual.quit();
        }

    }

    private void info() {
        StichData sd = visual.getStichData();
        String text = "Stiches: " + sd.size() + "\n"
                + "Colors: " + sd.getColors().size() + "\n"
                + "Jumps: " + sd.getJumps() + "\n";
        JOptionPane.showMessageDialog(visual.frame, text);
    }

}
