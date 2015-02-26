package de.xonibo.stickes.assemble;

// non-commercial use only
// von http://www.k-achilles.de/algorithmen/turtlegrafik%20und%20fraktale.pdf
public class Hilbert extends Turtle {

    public Hilbert() {
        hilbert(5, 5, 10);
    }

    public Hilbert(int stufe, int length) {
        super(0, 0, 0);
        hilbert(stufe, 1, length);
    }

    private void hilbert(
            int stufe,
            int vorzugsrichtung,
            int len) {
        if (stufe > 0) {
            turn(-vorzugsrichtung * 90);
            hilbert(stufe - 1, -vorzugsrichtung, len);
            move(len);
            turn(vorzugsrichtung * 90);
            hilbert(stufe - 1, vorzugsrichtung, len);
            move(len);
            hilbert(stufe - 1, vorzugsrichtung, len);
            turn(vorzugsrichtung * 90);
            move(len);
            hilbert(stufe - 1, -vorzugsrichtung, len);
            turn(-vorzugsrichtung * 90);
        }
    }
}
