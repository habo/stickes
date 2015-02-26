package de.xonibo.stickes.assemble;

public class KochCurve extends Turtle {

    protected double size;
    protected int width = 512;
    protected int n = 3;

    public KochCurve() {
        init();
    }

    public KochCurve(int complexity) {
        this.n = complexity;
        init();
    }

    public KochCurve(int complexity, int width) {
        this.n = complexity;
        this.width = width;
        init();
    }

    protected void koch(int n) {
        if (n == 0) {
            move(size);
        } else {
            koch(n - 1);
            turn(60);
            koch(n - 1);
            turn(-120);
            koch(n - 1);
            turn(60);
            koch(n - 1);
        }
    }

    protected void init() {
        size = width / Math.pow(3.0, n);
        koch(n);
    }

}
