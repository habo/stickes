package de.xonibo.stickes.stiches;

import java.awt.Shape;
import java.awt.geom.FlatteningPathIterator;
import java.awt.geom.GeneralPath;
import java.awt.geom.PathIterator;

public class Satin extends BasicShape {

    protected float wavelength = 1;
    protected float amplitude = 10;

    public Satin(Shape shape, float wavelength, float amplitude) {
        this.amplitude = amplitude;
        this.wavelength = wavelength;
        setShape(satin(shape));
    }

    public Satin(Shape shape) {
        setShape(satin(shape));
    }

    public void setAmplitude(float amplitude) {
        this.amplitude = amplitude;
    }

    public void setWavelength(float wavelength) {
        this.wavelength = wavelength;
    }

    // methode von ZIGSAG siehe  http://www.jhlabs.com/java/java2d/strokes/
    protected Shape satin(Shape shape) {
        if (shape == null) {
            return null;
        }
        GeneralPath result = new GeneralPath();
        PathIterator it = new FlatteningPathIterator(shape.getPathIterator(null), flatness);
        float[] points = new float[6];
        float moveX = 0, moveY = 0;
        float lastX = 0, lastY = 0;
        float thisX, thisY;
        int type;
        float next = 0;
        int phase = 0;

        while (!it.isDone()) {
            type = it.currentSegment(points);
            switch (type) {
                case PathIterator.SEG_MOVETO:
                    moveX = lastX = points[0];
                    moveY = lastY = points[1];
                    result.moveTo(moveX, moveY);
                    next = wavelength / 2;
                    break;

                case PathIterator.SEG_CLOSE:
                    points[0] = moveX;
                    points[1] = moveY;
                // Fall into....

                case PathIterator.SEG_LINETO:
                    thisX = points[0];
                    thisY = points[1];
                    float dx = thisX - lastX;
                    float dy = thisY - lastY;
                    float distance = (float) Math.sqrt(dx * dx + dy * dy);
                    if (distance >= next) {
                        float r = 1.0f / distance;
                        while (distance >= next) {
                            float x = lastX + next * dx * r;
                            float y = lastY + next * dy * r;
                            if ((phase & 1) == 0) {
                                result.lineTo(x + amplitude * dy * r, y - amplitude * dx * r);
                            } else {
                                result.lineTo(x - amplitude * dy * r, y + amplitude * dx * r);
                            }
                            next += wavelength;
                            phase++;
                        }
                    }
                    next -= distance;
                    lastX = thisX;
                    lastY = thisY;
                    if (type == PathIterator.SEG_CLOSE) {
                        result.closePath();
                    }
                    break;
            }
            it.next();
        }
        return result;
    }
}
