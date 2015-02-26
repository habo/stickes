package de.xonibo.stickes.examples;

import de.xonibo.stickes.Stich;
import de.xonibo.stickes.StichData;
import de.xonibo.stickes.StichType;
import de.xonibo.stickes.stiches.Plain;
import de.xonibo.stickes.stiches.Satin;
import de.xonibo.stickes.awt.Visual;
import java.awt.geom.GeneralPath;
import java.io.IOException;

public class VisualPath {

    public static void main(String[] args) throws IOException {
        StichData sd = new StichData();
        GeneralPath result1 = new GeneralPath();
        result1.moveTo(200, 300);
        result1.lineTo(100, 100);
        result1.curveTo(200, 50, 300, 50, 400, 100);
        result1.closePath();

        GeneralPath result2 = new GeneralPath();
        result2.moveTo(150, 100);
        result2.lineTo(250, 200);

        sd.addAll(new Satin(result1,2, 20).toStichData());
        sd.addAll(new Plain(result2).toStichData());

        sd.add(new Stich(StichType.EOF));

        Visual app = new Visual();
        app.load(sd);
        app.initFrame("exampleGeneralPath", 1);
    }

    private VisualPath() {
    }
}
