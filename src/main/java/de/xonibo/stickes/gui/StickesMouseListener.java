package de.xonibo.stickes.gui;

import de.xonibo.stickes.stiches.Plain;
import de.xonibo.stickes.stiches.Satin;
import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class StickesMouseListener implements MouseListener {

    private final Visual visual;

    StickesMouseListener(Visual visual) {
        this.visual = visual;
    }

    @Override
    public void mouseClicked(MouseEvent e) {
    }

    @Override
    public void mousePressed(MouseEvent e) {
        if (e.getButton() == MouseEvent.BUTTON1) {
            visual.getMousePath().add(new Point(e.getX(), e.getY()));
            visual.repaint();
        }
        if (e.getButton() == MouseEvent.BUTTON3) {
            boolean onlyJumps=false;
            switch (visual.stichformat) {
                case JumpOnly:
                    onlyJumps=true;
                case Plain:
                    visual.getStichData().addAll(new Plain(visual.getMousePathShape()).toStichData(onlyJumps));
                    break;
                case Satin:
                    visual.getStichData().addAll(new Satin(visual.getMousePathShape()).toStichData());
                    break;
            }
            visual.init();
            visual.resetMousePath();
            visual.repaint();
        }
    }

    @Override
    public void mouseReleased(MouseEvent e) {
    }

    @Override
    public void mouseEntered(MouseEvent e) {
    }

    @Override
    public void mouseExited(MouseEvent e) {
    }

}
