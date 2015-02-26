package de.xonibo.stickes.assemble;

// non-commercial use only
// von http://www.k-achilles.de/algorithmen/turtlegrafik%20und%20fraktale.pdf
public class Zacken extends Turtle {

    public Zacken() {
        super(0, 0, 0);
        zackenkurve(500, 3);
    }

    public Zacken(double length, int stufe) {
        super(0, 0, 0);
        zackenkurve(length, stufe);
    }

    private void zackenkurve(double laenge, int stufe) {
        double teillaenge = laenge / 4.0;
        if (stufe > 0) {
            zackenkurve(teillaenge, stufe - 1);
            turn(60);
            zackenkurve(teillaenge, stufe - 1);
            turn(-120);
            zackenkurve(teillaenge, stufe - 1);
            zackenkurve(teillaenge, stufe - 1);
            turn(120);
            zackenkurve(teillaenge, stufe - 1);
            turn(-60);
            zackenkurve(teillaenge, stufe - 1);
        } else {
            move(laenge);
        }
    }

}
