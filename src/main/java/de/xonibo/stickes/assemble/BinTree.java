package de.xonibo.stickes.assemble;

// non-commercial use only
// von http://www.k-achilles.de/algorithmen/turtlegrafik%20und%20fraktale.pdf
public class BinTree extends Turtle {

    public BinTree() {
        super(0, 0, -90);
        binBaum(8, 80, 60);
    }

    public BinTree(int n, int winkel, int length) {
        super(0, 0, -90);
        binBaum(n, winkel, length);
    }

    private
            void
            binBaum(
                    int nrek,
                    int winkel,
                    double laenge) {
                if (nrek > 0) {
                    move(laenge);
                    turn(winkel / 2);
                    binBaum(nrek - 1, winkel, laenge / 1.4);
                    turn(-winkel);
                    binBaum(nrek - 1, winkel, laenge / 1.4);
                    turn(winkel / 2);
                    move(-laenge);
                }
            }
}
