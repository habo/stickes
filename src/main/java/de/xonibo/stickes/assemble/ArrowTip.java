package de.xonibo.stickes.assemble;

// non-commercial use only
// von http://www.k-achilles.de/algorithmen/turtlegrafik%20und%20fraktale.pdf
public class ArrowTip extends Turtle {

    public ArrowTip() {
        pfeilSpitze(5, 5, 250);
    }

    public ArrowTip(int n, int vz, int length) {
        pfeilSpitze(n, vz, length);
    }

    private void
            pfeilSpitze(
                    int nrek,
                    int vz,
                    double laenge) {
                if ((nrek > 0) && (laenge > 1)) {
                    turn(60 * vz);
                    pfeilSpitze(nrek - 1, -vz, laenge / 2);
                    turn(-60 * vz);
                    pfeilSpitze(nrek - 1, vz, laenge / 2);
                    turn(-60 * vz);
                    pfeilSpitze(nrek - 1, -vz, laenge / 2);
                    turn(60 * vz);
                } else {
                    move(laenge);
                }
            }

}
