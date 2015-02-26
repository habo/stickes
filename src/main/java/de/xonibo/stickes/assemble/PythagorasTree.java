package de.xonibo.stickes.assemble;

// non-commercial use only
// von http://www.k-achilles.de/algorithmen/turtlegrafik%20und%20fraktale.pdf
public class PythagorasTree extends Turtle {

    public PythagorasTree() {
        super(0, 0, 180);
        pythBaum(8, 100);
    }

    public PythagorasTree(int n, double length) {
        super(0, 0, 180);
        pythBaum(n, length);
    }

    private void
            pythBaum(
                    int nrek,
                    double laenge) {
                double alpha = 36.86989765, beta = 53.13010235;
                if (nrek == 0) {
                    move(laenge);
                } else {
                    move(laenge);
                    turn(90);
                    move(laenge);
                    turn(90);
                    move(laenge);
                    turn(90);
                    move(laenge);
                    turn(180);
                    move(laenge);
                    turn(-beta);
                    if (nrek > 0) {
                        pythBaum(nrek - 1, 0.8 * laenge);
                    } else {
                        move(0.8 * laenge);
                    }
                    turn(-90);
                    if (nrek > 0) {
                        pythBaum(nrek - 1, 0.6 * laenge);
                    } else {
                        move(0.6 * laenge);
                    }
                    turn(-alpha);
                    move(laenge);
                    turn(90);
                }
            }
}
