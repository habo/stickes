package de.xonibo.stickes.assemble;

// non-commercial use only
// von http://www.k-achilles.de/algorithmen/turtlegrafik%20und%20fraktale.pdf
public class Quadratrosette extends Turtle {

    public Quadratrosette() {
        super(0, 0, 0);
        quadratRosette(5, 100);
    }

    public Quadratrosette(int winkel, int length) {
        super(0, 0, 0);
        quadratRosette(winkel, length);
    }

    private void
            quadrat(
                    int strecke) {
                for (int k = 1; k <= 4; k++) {
                    move(strecke);
                    turn(90);
                }
            }

            private void
                    quadratRosette(
                            int winkel, int strecke) {
                        if (winkel == 0) {
                            return;
                        }
                        for (int k = 1; k <= 360 / winkel; k++) {
                            quadrat(strecke);
                            turn(winkel);
                        }
                    }

}
