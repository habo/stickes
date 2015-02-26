package de.xonibo.stickes.stiches;

import de.xonibo.stickes.Stich;
import de.xonibo.stickes.StichData;
import java.awt.Shape;
import java.awt.geom.AffineTransform;
import java.awt.geom.PathIterator;

public class BasicShape {

    protected float flatness = 1;
    protected Shape shape;

    public void setFlatness(float flatness) {
        this.flatness = flatness;
    }

    public void setShape(Shape shape) {
        this.shape = shape;
    }

    public StichData toStichData() {
        return toStichData(false);
    }

    public StichData toStichData(boolean jumponly) {
        StichData sd = new StichData();
        if (shape == null) {
            return sd;
        }
        AffineTransform at = null;
        PathIterator iter = shape.getPathIterator(at, flatness);
        double[] coords = new double[6];
        Stich stich = null;
        while (!iter.isDone()) {
            int type = iter.currentSegment(coords);
            stich = new Stich((int) coords[0], (int) coords[1], type == PathIterator.SEG_CLOSE);
            if (sd.isEmpty()) {
                sd.add(new Stich((int) coords[0], (int) coords[1], true));
            }
            if (jumponly) {
                stich.setJump(true);
            }
            sd.add(stich);
            iter.next();
        }
        return sd;
    }

}
