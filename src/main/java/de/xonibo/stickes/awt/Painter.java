package de.xonibo.stickes.awt;

import de.xonibo.stickes.Stich;
import de.xonibo.stickes.StichData;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.MouseInfo;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JMenu;
import javax.swing.JRadioButtonMenuItem;
import javax.swing.SwingUtilities;

public class Painter implements ActionListener {

    private final StichColor stichColor = new StichColor(StichColor.Table.Standard);
    private final List<JRadioButtonMenuItem> menuitems = new ArrayList<>();

    public List<JRadioButtonMenuItem> createMenuItems() {

        List<JRadioButtonMenuItem> list = new ArrayList<>();
        for (StichColor.Table c : StichColor.Table.values()) {
            JRadioButtonMenuItem i = new JRadioButtonMenuItem(c.toString());
            if (c.equals(stichColor.getCurrentTable())) {
                i.setSelected(true);
            }
            i.addActionListener(this);
            list.add(i);
        }
        return list;
    }

    public List<JRadioButtonMenuItem> getMenuitems() {
        if (menuitems.isEmpty()) {
            menuitems.addAll(createMenuItems());
        }
        return menuitems;
    }

    public JMenu createMenu() {
        JMenu menuFormat = new JMenu("Color");

        for (JRadioButtonMenuItem jmi : getMenuitems()) {
            menuFormat.add(jmi);
        }
        return menuFormat;
    }

    public void drawMouseData(Graphics g, Visual v, List<Point> mousepath) {
        if (mousepath.isEmpty()) {
            return;
        }
        g.setColor(Color.GREEN);
        Point last;
        Point current;
        for (int i = 1; i < mousepath.size(); i++) {
            last = mousepath.get(i - 1);
            current = mousepath.get(i);
            g.drawLine(last.x, last.y, current.x, current.y);
        }
        last = mousepath.get(mousepath.size() - 1);
        Point ml = MouseInfo.getPointerInfo().getLocation();
        SwingUtilities.convertPointFromScreen(ml, v);
        g.drawLine(last.x, last.y, ml.x, ml.y);
    }

    public void drawStichData(Graphics g, StichData sd, int initx, int inity, int crosssize, int stichesCurrent, double scale) {
        g.setColor(stichColor.getCurrentColor());
        Stich last = sd.get(0);
        for (int count = 0; count < sd.size(); count++) {
            Stich stich = sd.get(count);
            if (count > stichesCurrent) {
                return;
            }
            int lx = (int) ((initx + last.getX()) / scale);
            int ly = (int) ((inity + last.getY()) / scale);
            int lxm = lx - crosssize / 2;
            int lym = ly - crosssize / 2;
            int x = (int) ((initx + stich.getX()) / scale);
            int y = (int) ((inity + stich.getY()) / scale);
            int x1 = x - crosssize;
            int x2 = x + crosssize;
            int y1 = y - crosssize;
            int y2 = y + crosssize;
            int xm = x - crosssize / 2;
            int ym = y - crosssize / 2;
            if (count == sd.size() - 1) {
                g.setColor(Color.PINK);
                g.drawLine(x1, y2, x2, y1);
                g.drawLine(x1, y1, x2, y2);
            }
            if (stich.isEOF()) {
                g.setColor(Color.BLACK);
                g.drawRect(lxm, lym, crosssize, crosssize);
            } else if (stich.isColorChange()) {
                stichColor.setCurrentColor(stich);
                g.setColor(Color.GREEN);
                g.drawRect(xm, ym, crosssize, crosssize);
            } else {
                if (stich.isJump()) {
                    g.setColor(Color.RED);
                    g.drawOval(xm, ym, crosssize, crosssize);
                } else {
                    g.setColor(stichColor.getCurrentColor(count));
                }
                g.drawLine(lx, ly, x, y);
            }
            last = stich;
        }
    }

    public void drawSmallScaleData(Graphics g, double scale) {
        g.setColor(Color.MAGENTA);
        int n = 5;
        double m = 100 / scale + n;
        g.drawLine(n, n, n, (int) m);
        g.drawLine(n, n, (int) m, n);
        g.setColor(Color.WHITE);
        n = 6;
        m = 100 / scale + n;
        g.drawLine(n, n, n, (int) m);
        g.drawLine(n, n, (int) m, n);
    }

    public void drawBigScaleData(Graphics g, double scale) {
        g.setColor(Color.MAGENTA);
        double f = 100f / scale;
        for (int n = 0; n < f * 10; n += f) {
            g.drawLine(n, 0, n, (int) (f * 10));
            g.drawLine(0, n, (int) (f * 10), n);
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        for (JRadioButtonMenuItem mi : getMenuitems()) {
            if (e.getSource() == mi) {
                for (JRadioButtonMenuItem mix : getMenuitems()) {
                    mix.setSelected(false);
                }
                mi.setSelected(true);
                stichColor.setCurrentTable(StichColor.Table.valueOf(mi.getText()));
            }
        }
    }
}
