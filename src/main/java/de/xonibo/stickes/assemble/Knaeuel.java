package de.xonibo.stickes.assemble;

// non-commercial use only
// von http://www.k-achilles.de/algorithmen/turtlegrafik%20und%20fraktale.pdf
public class Knaeuel extends Turtle {

    public Knaeuel() {
        knaeuel(400, 50, 20, 7);
    }

    public Knaeuel(int n, int strecke, int winkel, int ablenkWinkel) {
        super(0, 0, 0);
        knaeuel(n, strecke, winkel, ablenkWinkel);
    }

    private void
            vSchritt(
                    int strecke,
                    int winkel) {
                move(strecke);
                turn(winkel);
            }

            private void
                    knaeuel(
                            int nrek,
                            int strecke,
                            int winkel,
                            int ablenkWinkel) {
                        if (nrek >= 1) {
                            vSchritt(strecke, winkel);
                            knaeuel(nrek - 1, strecke, winkel + ablenkWinkel, ablenkWinkel);
                        }
                    }
}
