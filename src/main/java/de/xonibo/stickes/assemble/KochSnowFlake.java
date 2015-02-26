package de.xonibo.stickes.assemble;

public class KochSnowFlake extends KochCurve {

    public KochSnowFlake() {
        super();
    }

    public KochSnowFlake(int complexity) {
        super(complexity);
    }
    
    public KochSnowFlake(int complexity, int width) {
        super(complexity, width);
    }
    
    @Override
    protected void init() {
        size = width / Math.pow(3.0, n);
        koch(n);
        turn(-120);
        koch(n);
        turn(-120);
        koch(n);

    }

}
