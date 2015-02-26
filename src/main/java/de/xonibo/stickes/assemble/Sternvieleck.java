package de.xonibo.stickes.assemble;

// non-commercial use only
// von http://www.k-achilles.de/algorithmen/turtlegrafik%20und%20fraktale.pdf
public class Sternvieleck extends Turtle {

    public Sternvieleck() {
        super(0, 0, 0);
        sternVieleck(200, 152);
    }

    public Sternvieleck(int length, int winkel) {
        super(0, 0, 0);
        sternVieleck(length, winkel);
    }

    private void sternVieleck(
            int strecke,
            int ablenkWinkel) {
        int gesamtWinkel = 0;
        do {
            move(strecke);
            turn(ablenkWinkel);
            gesamtWinkel = gesamtWinkel + ablenkWinkel;
        } while (gesamtWinkel % 360 != 0);
    }
}
