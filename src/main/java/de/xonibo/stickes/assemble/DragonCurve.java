package de.xonibo.stickes.assemble;

public class DragonCurve extends Turtle {

    private double step = 10;
    private int dim = 17;

    public DragonCurve() {
        run(dim, -1);
    }

    public DragonCurve(int x, int y, int dimension, double step) {
        this.dim = dimension;
        this.step = step;
        run(dim, -1);
    }

    private void run(int dimension, int drehrichtung) {
        if (dimension == 0) {
            move(step);
        } else {
            run(dimension - 1, 1);
            turn(drehrichtung * 90);
            run(dimension - 1, -1);
        }
    }

}
