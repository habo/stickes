package de.xonibo.stickes;

import java.awt.Color;
import java.io.Serializable;

public class Stich implements Serializable, StickesSource {

    private StichType type = StichType.POSITION;
    private int x = 0;
    private int y = 0;
    private Color color = null;

    public Stich() {
    }

    public Stich(StichType stichType) {
        type = stichType;
    }

    public Stich(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public Stich(double x, double y, StichType stichType) {
        this.x = (int) x;
        this.y = (int) y;
        type = stichType;
    }
    public Stich(int x, int y, boolean isJump) {
        this.x = x;
        this.y = y;
        type = isJump ? StichType.JUMP : StichType.POSITION;
    }

    public Stich(Color color) {
        type = StichType.COLOR;
        this.color = color;
    }

    public Stich(int red, int green, int blue) {
        type = StichType.COLOR;
        this.color = new Color(red, green, blue);
    }

    public Color getColor() {
        return color;
    }

    public int getX() {
        return x;
    }

    public void flipX() {
        this.x *= -1;
    }

    public void flipY() {
        this.y *= -1;
    }

    public void move(int m, int n) {
        x += m;
        y += n;
    }

    public int getY() {
        return y;
    }

    public boolean isJump() {
        return StichType.JUMP.equals(type);
    }

    public void setJump(boolean isJump) {
        type = isJump ? StichType.JUMP : StichType.POSITION;
    }

    public boolean isColorChange() {
        return StichType.COLOR.equals(type);
    }

    public boolean isEOF() {
        return StichType.EOF.equals(type);
    }

    @Override
    public String toString() {
        if (isColorChange()) {
            return "CC";
        }
        return "(x:" + x + ",y:" + y + "):" + isJump();
    }

    @Override
    public String getSource() {
        // TODO getClass (). getConstructors()
        if (isColorChange()) {
            return "new Stich(StichType.COLOR)";
        }
        if (isEOF()) {
            return "new Stich(StichType.EOF)";
        }
        return "new Stich(" + x + "," + y + "," + isJump() + ")";
    }
}
