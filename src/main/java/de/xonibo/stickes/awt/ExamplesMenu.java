package de.xonibo.stickes.awt;

import de.xonibo.stickes.StichData;
import de.xonibo.stickes.assemble.ArrowTip;
import de.xonibo.stickes.assemble.BinTree;
import de.xonibo.stickes.assemble.CCurve;
import de.xonibo.stickes.assemble.DragonCurve;
import de.xonibo.stickes.assemble.Hilbert;
import de.xonibo.stickes.assemble.Knaeuel;
import de.xonibo.stickes.assemble.KochCurve;
import de.xonibo.stickes.assemble.KochSnowFlake;
import de.xonibo.stickes.assemble.LindenmayerTurtle;
import de.xonibo.stickes.assemble.PythagorasTree;
import de.xonibo.stickes.assemble.QR;
import de.xonibo.stickes.assemble.Quadratrosette;
import de.xonibo.stickes.assemble.Siebenkreis;
import de.xonibo.stickes.assemble.SierpinskiTriangle;
import de.xonibo.stickes.assemble.Sternvieleck;
import de.xonibo.stickes.assemble.Zacken;
import de.xonibo.stickes.stiches.BasicShape;
import de.xonibo.stickes.stiches.Plain;
import de.xonibo.stickes.stiches.Satin;
import de.xonibo.stickes.stiches.Text;
import java.awt.Font;
import java.awt.Shape;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.geom.Area;
import java.awt.geom.Ellipse2D;
import java.awt.geom.GeneralPath;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JSeparator;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;

public class ExamplesMenu implements ActionListener {

    public static Shape getPentagramShape(int x, int y, int scale) {
        GeneralPath path = new GeneralPath();
        path.moveTo(x + 10 * scale, y);
        path.lineTo(x + 17 * scale, y + 20 * scale);
        path.lineTo(x, y + 7 * scale);
        path.lineTo(x + 20 * scale, y + 7 * scale);
        path.lineTo(x + 3 * scale, y + 20 * scale);
        path.lineTo(x + 10 * scale, y);
        path.closePath();
        return path;
    }

    public static Shape getSmileShape(int x, int y, int d) {
        int xh = x + d / 2;
        int yh = y + d / 2;
        Area shape = new Area();
        shape.add(new Area(new Ellipse2D.Double(x, y, d, d)));
        int a = d / 5;
        int b = d / 3;
        shape.subtract(new Area(new Ellipse2D.Double(xh - d / 16, yh - d / 8, d / 8, d / 4)));
        shape.subtract(new Area(new Ellipse2D.Double(xh - d / 4, yh + b - d / 16, d / 2, d / 8)));
        shape.subtract(new Area(new Ellipse2D.Double(xh - a - d / 8, yh - b, d / 4, d / 4)));
        shape.subtract(new Area(new Ellipse2D.Double(xh + a - d / 8, yh - b, d / 4, d / 4)));
        return shape;
    }

    public static Text getText(Shape shape, String text, int fontsize) {
        Text o = new Text(shape, new Font("Verdana", Font.PLAIN, fontsize), text);
        o.setFlatness(1);
        return o;
    }

    private final Visual visual;
    protected JMenuItem menuItemExampleOptions = new JMenuItem("Options", KeyEvent.VK_HOME);

    final String exampleText = "Hello World";

    private final List<JMenuItem> menuitems = new ArrayList<>();

    public ExamplesMenu(Visual visual) {
        this.visual = visual;
    }

    private void displayOptions() {

        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                UIManager.put("swing.boldMetal", Boolean.FALSE);
                new ExamplesOptionDialog("Examples Options").run();
            }
        });

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == menuItemExampleOptions) {
            displayOptions();
        }

        for (JMenuItem mi : getMenuitems()) {
            if (e.getSource() == mi) {
                visual.getStichData().addAll(getStichData(visual.stichformat, mi.getText()));
                visual.init();
                visual.repaint();
            }
        }
    }

    public StichData getStichData(StichFormat format, String name) {
        try {
            switch (ExampleEnum.valueOf(name)) {
                case QRCode: {
                    return new QR(exampleText, 6, 3).toStichData();
                }
            }
        } catch (Exception ex) {
            Logger.getLogger(ExamplesMenu.class.getName()).log(Level.SEVERE, null, ex);
        }
        Shape shape = ExampleEnum.valueOf(name).getShape(visual);
        BasicShape bs;
        switch (format) {
            case JumpOnly:
            case Plain:
                bs = new Plain(shape);
                break;
            case Satin:
                bs = new Satin(shape);
                break;
            case Text:
                bs = getText(shape, exampleText, 20);
                break;
            default:
                return new StichData();
        }
        return bs.toStichData(StichFormat.JumpOnly.equals(format));
    }

    public JMenu createMenu() {
        JMenu menuExamples = new JMenu("Examples");
        for (JMenuItem jmi : createMenuItems()) {
            menuExamples.add(jmi);
        }
        menuExamples.add(new JSeparator());
        menuItemExampleOptions.addActionListener(this);
        menuExamples.add(menuItemExampleOptions);

        return menuExamples;
    }

    public List<JMenuItem> getMenuitems() {
        if (menuitems.isEmpty()) {
            menuitems.addAll(createMenuItems());
        }
        return menuitems;
    }

    public List<JMenuItem> createMenuItems() {
        List<String> strings = new ArrayList<>();
        for (ExampleEnum e : ExampleEnum.values()) {
            strings.add(e.toString());
        }
        Collections.sort(strings);
        for (String label : strings) {
            JMenuItem i = new JMenuItem(label);
            i.addActionListener(this);
            menuitems.add(i);
        }

        return menuitems;
    }

    public enum StichFormat {

        Plain, Satin, Text, JumpOnly
    }

    public enum ExampleEnum {

        DragonCurve, KochCurve, KochSnowFlake, HilbertCurve, CCurve, PythTreeCurve, ArrowTipCurve, BinTreeCurve, Quadratrosette, Siebenkreis, SternVieleck, Knaeuel, ZackenCurve, SierpinskiTriangle, Pentagram, Smile, Line, Metric100, Metric250, Metric500, QRCode,
        Lace;

        public Shape getShape(Visual visual) {
            final String customized_Dialog = "Customized Dialog";
            switch (this) {
                case Lace:
                    LindenmayerTurtle ls = new LindenmayerTurtle(7, 30, 10, "W", "W=+++X--F--ZFX+", "X=---W++F++YFW-", "Y=+ZFX--F--Z+++", "Z=-YFW++F++Y---");
                    return ls.getPath();
                case Smile:
                    return getSmileShape(150, 150, 150);
                case Line:
                    return getLineShape(100, 100);
                case SierpinskiTriangle:
                    return new SierpinskiTriangle().getPath();
                case KochSnowFlake:
                    int i = 3;
                    int length = 200;
                    if (visual != null) {
                        try {
                            i = (int) JOptionPane.showInputDialog(
                                    visual.frame,
                                    "Set N for KochCurve:\n", customized_Dialog,
                                    JOptionPane.PLAIN_MESSAGE,
                                    null,
                                    new Object[]{2, 3, 4, 5, 6, 7},
                                    3);
                            length = (int) JOptionPane.showInputDialog(
                                    visual.frame,
                                    "Set length for KochCurve:\n", customized_Dialog,
                                    JOptionPane.PLAIN_MESSAGE,
                                    null,
                                    new Object[]{25, 50, 100, 200, 300, 400, 500, 750, 1000},
                                    200);
                        } catch (NullPointerException n) {
                        }
                    }
                    return new KochSnowFlake(i, length).getPath();
                case KochCurve:
                    return new KochCurve(3, 200).getPath();
                case DragonCurve:
                    int dim = 10;
                    int step = 10;
                    if (visual != null) {
                        try {
                            dim = (int) JOptionPane.showInputDialog(
                                    visual.frame,
                                    "Set dim for DragonCurve:\n", customized_Dialog,
                                    JOptionPane.PLAIN_MESSAGE,
                                    null,
                                    new Object[]{6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18},
                                    10);
                            step = (int) JOptionPane.showInputDialog(
                                    visual.frame,
                                    "Set step for DragonCurve:\n", customized_Dialog,
                                    JOptionPane.PLAIN_MESSAGE,
                                    null,
                                    new Object[]{5, 10, 15, 20, 25},
                                    10);
                        } catch (NullPointerException n) {
                        }
                    }
                    return new DragonCurve(100, 100, dim, step).getPath();
                case Pentagram:
                    return getPentagramShape(0, 0, 5);
                case Metric100:
                    return new Rectangle2D.Float(0, 0, 100, 100);
                case Metric250:
                    return new Rectangle2D.Float(50, 50, 250, 250);
                case Metric500:
                    return new Rectangle2D.Float(100, 100, 500, 500);
                case ArrowTipCurve:
                    return new ArrowTip(5, 5, 300).getPath();
                case BinTreeCurve:
                    return new BinTree(8, 80, 60).getPath();
                case CCurve:
                    return new CCurve(8, 10).getPath();
                case HilbertCurve:
                    return new Hilbert(5, 10).getPath();
                case PythTreeCurve:
                    return new PythagorasTree(6, 100).getPath();
                case Quadratrosette:
                    return new Quadratrosette(5, 100).getPath();
                case Siebenkreis:
                    return new Siebenkreis(3).getPath();
                case ZackenCurve:
                    return new Zacken(500, 3).getPath();
                case Knaeuel:
                    return new Knaeuel(720, 20, 4, 9).getPath();
                case SternVieleck:
                    return new Sternvieleck(120, 152).getPath();
            }
            return null;
        }

        private Shape getLineShape(int x, int y) {
            GeneralPath path = new GeneralPath();
            path.moveTo(x, y);
            for (int i = 0; i < 300; i += 25) {
                path.lineTo(x, y);
                path.lineTo(x + i, y);
                y = y + 10;
            }
            return path;
        }
    }
}
