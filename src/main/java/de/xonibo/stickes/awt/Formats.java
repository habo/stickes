package de.xonibo.stickes.awt;

import de.xonibo.stickes.awt.Examples.StichFormat;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JMenu;
import javax.swing.JRadioButtonMenuItem;

public class Formats implements ActionListener {

    final private Visual visual;


    private final List<JRadioButtonMenuItem> menuitems = new ArrayList<>();

    public Formats(Visual visual) {
        this.visual = visual;
    }

    public List<JRadioButtonMenuItem> createMenuItems() {
        List<JRadioButtonMenuItem> list = new ArrayList<>();
        for (Examples.StichFormat sf : Examples.StichFormat.values()) {
            JRadioButtonMenuItem i = new JRadioButtonMenuItem(sf.toString());
            if (sf.equals(visual.stichformat)) {
                i.setSelected(true);
            }
            i.addActionListener(this);
            //i.addKeyListener(this.visual);
            list.add(i);
        }
        return list;
    }

    public List<JRadioButtonMenuItem> getMenuitems() {
        if (menuitems.isEmpty())
            menuitems.addAll(createMenuItems());
        return menuitems;
    }

    public JMenu createMenu() {
        JMenu menuFormat = new JMenu("Format");

        for (JRadioButtonMenuItem jmi : getMenuitems()) {
            menuFormat.add(jmi);
        }
        return menuFormat;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        for (JRadioButtonMenuItem mi : getMenuitems()) {
            if (e.getSource() == mi) {
                for (JRadioButtonMenuItem mix : getMenuitems()) {
                    mix.setSelected(false);
                }
                mi.setSelected(true);
                visual.stichformat = StichFormat.valueOf(mi.getText());
            }
        }
    }
}
