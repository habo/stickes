package de.xonibo.stickes.assemble;

public class SierpinskiTriangle extends Turtle {

    public SierpinskiTriangle() {
        startSierpinski(3, 100);
    }

    public SierpinskiTriangle(int level, int size) {
        startSierpinski(level, size);
    }

    private void drawSierpinski(double size, int level) {
        // triangle
        if (level == 0) {
            move(size);
            turn(120);
            move(size);
            turn(120);
            move(size);
            turn(-240);
            // we are back at start
        } else {
            // draw 3 smaler triagles
            drawSierpinski(size / 2, level - 1);
            move(size / 2);
            drawSierpinski(size / 2, level - 1);
            back(size / 2);
            turn(60);
            move(size / 2);
            turn(-60);
            drawSierpinski(size / 2, level - 1);
            turn(60);
            back(size / 2);
            turn(-60);
        }
    }

    private void startSierpinski(int level, int size) {
        drawSierpinski(size, level);
    }
}
