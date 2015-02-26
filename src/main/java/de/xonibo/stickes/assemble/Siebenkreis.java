package de.xonibo.stickes.assemble;

// non-commercial use only
// von http://www.k-achilles.de/algorithmen/turtlegrafik%20und%20fraktale.pdf
public class Siebenkreis extends Turtle {

    public Siebenkreis() {
        super(0, 0, 0);
        siebenkreis(5);
    }

    public Siebenkreis(int length) {
        super(0, 0, 0);
        siebenkreis(length);
    }

    private void
            linksBogen(
                    int strecke,
                    int winkel) {
                for (int k = 1; k <= winkel; k++) {
                    move(strecke);
                    turn(1);
                }
            }

            private void
                    siebenkreis(
                            int strecke) {
                        for (int k = 1; k <= 6; k++) {
                            linksBogen(strecke, 60);
                            turn(-60);
                            linksBogen(strecke, 360);
                            turn(60);
                        }
                    }
}
