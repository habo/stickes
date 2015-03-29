package de.xonibo.stickes.gui;

import de.xonibo.stickes.Stich;
import de.xonibo.stickes.StichData;
import de.xonibo.stickes.gui.ExamplesMenu.StichFormat;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.Shape;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.geom.GeneralPath;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.JSlider;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class Visual extends JPanel implements ChangeListener, ActionListener, KeyListener {

    public static void main(String[] args) throws IOException {
        Visual app = new Visual();
        app.initFrame("Stickes Editor", 1);
    }

    private List<Point> mousepath = new ArrayList<>();

    private StichData stichData = new StichData();
    private int initx = 0;
    private int inity = 0;

    private int crosssize = 8;
    protected double scale;
    protected int stichesMax = 0;
    protected int stichesCurrent = 0;
    protected File file;
    protected StichFormat stichformat = StichFormat.Plain;

    protected JFrame frame = new JFrame();
    protected JMenuBar menu = new JMenuBar();

    protected JMenu menuView = new JMenu("View");
    protected JMenuItem menuitemscaleup = new JMenuItem("Scale +", KeyEvent.VK_PLUS);
    protected JMenuItem menuitemscaledown = new JMenuItem("Scale -", KeyEvent.VK_MINUS);
    protected JMenuItem menuitemflipX = new JMenuItem("Flip X", KeyEvent.VK_X);
    protected JMenuItem menuitemflipY = new JMenuItem("Flip Y", KeyEvent.VK_Y);

    protected JPanel play = new JPanel(new BorderLayout());
    protected JSlider playslider = new JSlider(JSlider.HORIZONTAL);
    protected JButton playbutton = new JButton("Play");

    protected int wx = 1000;
    protected int wy = 700;

    Painter painter = new Painter();

    public void setStichData(StichData stichData) {
        this.stichData = stichData;
    }

    @Override
    public void stateChanged(ChangeEvent e) {
        JSlider source = (JSlider) e.getSource();
        if (!source.getValueIsAdjusting()) {
            stichesCurrent = (int) source.getValue();
            frame.repaint();
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if (e.getSource()
                == menuitemscaledown && scale > 0) {
            scale -= 0.25f;
            repaint();
        }

        if (e.getSource()
                == menuitemscaleup && scale < 20) {
            scale += 0.25f;
            repaint();
        }

        if (e.getSource()
                == menuitemflipX) {
            for (Stich stich : stichData) {
                stich.flipX();
            }
            init();
            repaint();
        }

        if (e.getSource()
                == menuitemflipY) {
            for (Stich stich : stichData) {
                stich.flipY();
            }
            init();
            repaint();
        }

        if (e.getSource()
                == playbutton) {
            Play.call(this);
            repaint();
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyChar() == '+') {
            if (stichesCurrent > stichesMax) {
                stichesCurrent++;
            }
            repaint();
        }
        if (e.getKeyChar() == '-') {
            if (stichesCurrent > 0) {
                stichesCurrent--;
            }
            repaint();
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
    }

    public StichData getStichData() {
        return stichData;
    }

    public void load(StichData sdin) {
        stichData = sdin;
    }

    private String title;

    public void initFrame(String title, double scalefaktor) {
        scale = scalefaktor;
        setBackground(Color.WHITE);
        setPreferredSize(new Dimension(wx, wx));
        init(scalefaktor);

        this.addMouseListener(new StickesMouseListener(this));
        frame.setTitle(title);
        this.title = title;

        menu.add(new Files(this).createMenu());

        menuitemscaleup.addActionListener(this);
        menuView.add(menuitemscaleup);
        menuitemscaledown.addActionListener(this);
        menuView.add(menuitemscaledown);
        menuView.add(new JSeparator());
        menuitemflipX.addActionListener(this);
        menuView.add(menuitemflipX);
        menuitemflipY.addActionListener(this);
        menuView.add(menuitemflipY);

        menu.add(menuView);

        menu.add(new EditorMenu(this).createMenu());
        menu.add(new ExamplesMenu(this).createMenu());

        menu.add(new FormatsMenu(this).createMenu());
        menu.add(painter.createMenu());

        frame.add(menu, BorderLayout.NORTH);
        frame.add(this, BorderLayout.CENTER);

        playslider.addChangeListener(this);
        playslider.setMajorTickSpacing(100);
        playslider.setMinorTickSpacing(20);
        playslider.setPaintTicks(true);

        play.add(playslider, BorderLayout.CENTER);
        playbutton.addActionListener(this);
        play.add(playbutton, BorderLayout.EAST);
        frame.add(play, BorderLayout.SOUTH);

        frame.addKeyListener(this);

        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setSize(wx, wy);
        frame.setVisible(true);
    }

    public void init() {
        init(scale);
    }

    public void init(double scalefaktor) {
        stichesMax = stichData.size();
        stichesCurrent = stichesMax;
        initx = -stichData.getInitCornerX();
        inity = -stichData.getInitCornerY();
        if (scalefaktor <= 0) {
            double dx = (double) wx / stichData.getMaxCornerX();
            double dy = (double) wy / stichData.getMaxCornerY();
            scale = Math.max(dx, dy) + 1;
        }
        playslider.setMaximum(stichData.size());
        playslider.setValue(stichData.size());
    }

    public String getTitle() {
        return title + " - " + stichData.getInfo();
    }

    @Override
    public void paintComponent(Graphics g) {
        // call superclass to make panel display correctly
        super.paintComponent(g);
//        painter.drawSmallScaleData(g, scale);
        painter.drawBigScaleData(g, scale);
        painter.drawMouseData(g, this, getMousePath());
        painter.drawStichData(g, stichData, initx, inity, crosssize, stichesCurrent, scale);
        frame.setTitle(getTitle());
    }

    void resetMousePath() {
        mousepath = new ArrayList<>();
    }

    public List<Point> getMousePath() {
        return mousepath;
    }

    public Shape getMousePathShape() {
        GeneralPath path = new GeneralPath();
        Point start = mousepath.get(0);
        path.moveTo(start.x, start.y);
        for (int i = 1; i < mousepath.size(); i++) {
            Point point = mousepath.get(i);
            path.lineTo(point.x, point.y);
        }
        return path;
    }

    void quit() {
        frame.setVisible(false);
        frame.dispose();
    }
}
