package de.xonibo.stickes;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class StichData extends ArrayList<Stich> implements StickesSource {

    static public Color createRandomColor() {
        Random r = new Random();
        return new Color(r.nextInt(0xff), r.nextInt(0xff), r.nextInt(0xff));
    }
    List<Color> colorInternal = new ArrayList<>();

    public String getInfo() {
        final int size = size();
        final int x = getMaxCornerX() / 10;
        final int y = getMaxCornerY() / 10;
        return String.format("Stiches: %d, x: %dmm, y: %dmm", size, x, y);
    }

    public StichData normalize() {
        int nx = Math.min(getInitCornerX(), 0);
        int ny = Math.min(getInitCornerY(), 0);
        StichData sd = (StichData) this.clone();
        for (Stich stich : sd) {
            stich.move(-nx, -ny);
        }
        return sd;
    }

    public StichData centerStart() {
        normalize();
        int x = getMaxCornerX() / 2;
        int y = getMaxCornerY() / 2;
        Stich s = new Stich(x, y, true);
        add(0, s);
        return this;
    }

    @Override
    public boolean add(Stich e) {
        add(this.size(), e);
        return true;
    }

    @Override
    public void add(int index, Stich e) {
        if (e.isColorChange()) {
            colorInternal.add(e.getColor());
        }
        super.add(index, e);
    }

    public List<Color> getColors() {
        return colorInternal;
    }

    public int getInitCornerX() {
        int x = 0;
        for (Stich stich : this) {
            if (stich.getX() < x) {
                x = stich.getX();
            }
        }
        return x;
    }

    public int getInitCornerY() {
        int y = 0;
        for (Stich stich : this) {
            if (stich.getY() < y) {
                y = stich.getY();
            }
        }
        return y;
    }

    public int getMaxCornerX() {
        int x = 0;
        for (Stich stich : this) {
            if (stich.getX() > x) {
                x = stich.getX();
            }
        }
        return x;
    }

    public int getMaxCornerY() {
        int y = 0;
        for (Stich stich : this) {
            if (stich.getY() > y) {
                y = stich.getY();
            }
        }
        return y;
    }

    public int getJumps() {
        int count = 0;
        for (Stich stich : this) {
            if (stich.isJump()) {
                count++;
            }
        }
        return count;
    }

    @Override
    public String getSource() {
        //TODO getClass().getConstructors()[0]
        return "new StichData()";

    }

}
