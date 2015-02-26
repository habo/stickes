package de.xonibo.stickes.assemble;

// non-commercial use only
// von http://www.k-achilles.de/algorithmen/turtlegrafik%20und%20fraktale.pdf
public class CCurve extends Turtle {

    public CCurve() {
        c(8, 10);
    }

    public CCurve(int n, int length) {
        c(n, length);
    }

    private void c(int nrek,
            double laenge) {
        if (nrek > 0) {
            c(nrek - 1, laenge);
            turn(90);
            c(nrek - 1, laenge);
            turn(-90);
        } else {
            move(laenge);
        }
    }
}
