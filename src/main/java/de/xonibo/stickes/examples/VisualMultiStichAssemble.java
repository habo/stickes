package de.xonibo.stickes.examples;

import de.xonibo.stickes.Stich;
import de.xonibo.stickes.StichData;
import de.xonibo.stickes.StichType;
import de.xonibo.stickes.awt.Visual;
import de.xonibo.stickes.stiches.Plain;
import de.xonibo.stickes.stiches.Satin;
import de.xonibo.stickes.stiches.Text;
import java.awt.Color;
import java.awt.Font;
import java.awt.Shape;
import java.awt.geom.Area;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Line2D;
import java.awt.geom.RoundRectangle2D;
import java.io.IOException;

public class VisualMultiStichAssemble {

    public static Shape xonibo(int x, int y, int d) {
        Area shape = new Area(new Ellipse2D.Double(x + d / 2, y - d / 2, d, d));
        shape.add(new Area(new Ellipse2D.Double(x - d / 2, y - d / 6, d * 3, d / 3)));
        return shape;
    }

    public static Shape roundRectangle(int x1, int y1, int w, int h, float warc, float harc) {
        return new RoundRectangle2D.Double(x1, y1, h, w, warc, harc);
    }

    public static void main(String[] args) throws IOException {
        StichData sd = new StichData();
        createStichDataExample(sd);

        Visual app = new Visual();
        app.load(sd);
        app.initFrame("exampleStichassemle", 1);
    }

    public static void createStichDataExample(StichData sd) {
        int y = 30;
        float wavelength = 1;
        sd.addAll(plainCircleTest(50, y, 40));
        sd.addAll(satinCircleTest(125, y, 40, wavelength, 10));

        sd.add(new Stich(200, y, true));
        sd.add(new Stich(100, y, true));
        y = 10;
        wavelength = 0.75f;
        sd.addAll(satinLineTest(50, y, wavelength, 5));
        sd.addAll(satinLineTest(100, y, wavelength, 10));
        sd.addAll(satinLineTest(150, y, wavelength, 15));
        sd.addAll(satinLineTest(200, y, wavelength, 20));
        sd.addAll(satinLineTest(250, y, wavelength, 25));
        sd.add(new Stich(Color.BLUE));

        sd.add(new Stich(200, y, true));
        sd.add(new Stich(100, y, true));
        y = -30;
        wavelength = 1;
        sd.addAll(satinLineTest(50, y, wavelength, 5));
        sd.addAll(satinLineTest(100, y, wavelength, 10));
        sd.addAll(satinLineTest(150, y, wavelength, 15));
        sd.addAll(satinLineTest(200, y, wavelength, 20));
        sd.addAll(satinLineTest(250, y, wavelength, 25));
        sd.add(new Stich(Color.YELLOW));

        sd.add(new Stich(200, y, true));
        sd.add(new Stich(100, y, true));
        y = -70;
        wavelength = 2;
        sd.addAll(satinLineTest(50, y, wavelength, 5));
        sd.addAll(satinLineTest(100, y, wavelength, 10));
        sd.addAll(satinLineTest(150, y, wavelength, 15));
        sd.addAll(satinLineTest(200, y, wavelength, 20));
        sd.addAll(satinLineTest(250, y, wavelength, 25));

        sd.add(new Stich(200, y, true));
        sd.add(new Stich(100, y, true));
        y = -120;
        sd.addAll(text(50, y, "Hello World"));
        sd.addAll(new Plain(xonibo(350, y, 50)).toStichData());
        sd.add(new Stich(StichType.EOF));
    }

    static public Shape line(int x1, int y1, int x2, int y2) {
        return new Line2D.Double(x1, y1, x2, y2);
    }

    static public Shape ellipse(int x1, int y1, int w, int h) {
        return new Ellipse2D.Double(x1, y1, w, h);
    }

    static public StichData satinLineTest(int x1, int y1, float wavelength, float amplitude) {
        return new Satin(line(x1, y1, x1, y1 - 30), wavelength, amplitude).toStichData();
    }

    static public StichData satinCircleTest(int x1, int y1, int diameter, float wavelength, float amplitude) {
        return new Satin(ellipse(x1, y1, diameter, diameter), wavelength, amplitude).toStichData();
    }

    static public StichData plainCircleTest(int x1, int y1, int diameter) {
        Plain p = new Plain(ellipse(x1, y1, diameter, diameter));
        p.setFlatness(2);
        return p.toStichData();
    }

    static public StichData text(int x, int y, String text) {
        Shape shape = new Line2D.Double(x, y, x + 300, y);
        Text o = new Text(shape, new Font("Verdana", Font.PLAIN, 40), text);
        o.setFlatness(2);
        return o.toStichData();
    }

    private VisualMultiStichAssemble() {
    }

}
