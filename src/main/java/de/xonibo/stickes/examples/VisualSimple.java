package de.xonibo.stickes.examples;

import de.xonibo.stickes.Stich;
import de.xonibo.stickes.StichData;
import de.xonibo.stickes.StichType;
import de.xonibo.stickes.awt.Visual;
import java.io.IOException;

public class VisualSimple {

    public static void main(String[] args) throws IOException {
        StichData sd = new StichData();

        for (int i = 0; i < 100; i++) {
            sd.add(new Stich(30+(i%2==0?+10:-10), 10 + i));
        }
        sd.add(new Stich(StichType.EOF));

        Visual app = new Visual();
        app.load(sd);
        app.initFrame("exampleSmile", 1);
    }

    private VisualSimple() {
    }
}
