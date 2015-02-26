package de.xonibo.stickes.assemble;

import java.awt.geom.GeneralPath;
import java.awt.geom.PathIterator;

// Source: http://introcs.cs.princeton.edu/java/32class/Turtle.java
// moved to GPLv3 by Owner Kevin Wayne
public class Turtle {

    private double x = 0, y = 0;     // turtle is at (x, y)
    private double angle = 0;    // facing this many degrees counterclockwise from the x-axis

    private final GeneralPath generalPath = new GeneralPath();

    public Turtle() {
        init();
    }

    // start at (x0, y0), facing a0 degrees counterclockwise from the x-axis
    public Turtle(double startx, double starty, double angel) {
        x = startx;
        y = starty;
        angle = angel;
        init();
    }

    private void init() {
        generalPath.moveTo(x, y);
    }

    public void setAngle(double angle) {
        this.angle = angle;
    }

    public GeneralPath getPath() {
        return generalPath;
    }

    public int getPathSize() {
        PathIterator p = generalPath.getPathIterator(null);
        int i = 0;
        while (!p.isDone()) {
            p.next();
            i++;
        }
        return i;

    }

    public double getAngle() {
        return angle;
    }
    
    // rotate orientation delta degrees counterclockwise
    public void turn(double delta) {
        angle += delta;
    }

    // move forward the given amount, with the pen down
    public void move(double step) {
        x += step * Math.cos(Math.toRadians(angle));
        y += step * Math.sin(Math.toRadians(angle));
        generalPath.lineTo(x, y);
    }

}