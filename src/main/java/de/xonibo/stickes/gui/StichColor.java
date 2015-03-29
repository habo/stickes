package de.xonibo.stickes.gui;

import de.xonibo.stickes.Stich;
import java.awt.Color;
import java.util.Random;

public class StichColor {


    private Color currentColor = Color.BLUE;
    private Table currentTable = Table.Standard;
    final private Random r = new Random();

    public Table getCurrentTable() {
        return currentTable;
    }

    public void setCurrentTable(Table currentTable) {
        this.currentTable = currentTable;
    }
    
    public StichColor() {
    }

    public StichColor(Table table) {
        this.currentTable = table;
    }

    public Color getCurrentColor() {
        return currentColor;
    }

    public void setCurrentColor(Color currentColor) {
        this.currentColor = currentColor;
    }

    public void setCurrentColor(Stich stich) {
        this.currentColor = stich.getColor();
    }

    protected Color makeColorGradient(int i, double frequency1, double frequency2, double frequency3,
            int phase1, int phase2, int phase3) {
        return makeColorGradient(i, frequency1, frequency2, frequency3, phase1, phase2, phase3, null, null, null);
    }

    protected Color makeColorGradient(int i, double frequency1, double frequency2, double frequency3,
            int phase1, int phase2, int phase3,
            Integer center, Integer width, Integer len) {
        if (center == null) {
            center = 128;
        }
        if (width == null) {
            width = 127;
        }
        if (len == null) {
            len = 50;
        }

        double red = Math.sin(frequency1 * i + phase1) * width + center;
        double grn = Math.sin(frequency2 * i + phase2) * width + center;
        double blu = Math.sin(frequency3 * i + phase3) * width + center;
        return new Color((int) red, (int) grn, (int) blu);
    }

    Color getCurrentColor(int count) {
        switch (currentTable) {
            case Rainbow:
                currentColor
                        = makeColorGradient(count, .3, .3, .3, 0, 2, 4);
                break;
            case SlowRainbow:
                currentColor
                        = makeColorGradient(count / 10, .3, .3, .3, 0, 2, 4);
                break;
            case BlackNWhite:
                currentColor = count % 2 == 0 ? Color.BLACK : Color.WHITE;
                break;
            case BlackNYellow:
                currentColor = count % 2 == 0 ? Color.BLACK : Color.YELLOW;
                break;
            case GermanFlag: {
                int mod = count % 3;
                if (mod == 0) {
                    currentColor = Color.BLACK;
                    break;
                }
                if (mod == 1) {
                    currentColor = Color.RED;
                    break;
                }
                if (mod == 2) {
                    currentColor = Color.YELLOW;
                    break;
                }
            }
            case JamaicaFlag: {
                int mod = count % 4;
                if (mod == 0) {
                    currentColor = Color.BLACK;
                    break;
                }
                if (mod == 1) {
                    currentColor = Color.RED;
                    break;
                }
                if (mod == 2) {
                    currentColor = Color.YELLOW;
                    break;
                }
                if (mod == 4) {
                    currentColor = Color.GREEN;
                    break;
                }
            }
            case Random:
                currentColor = new Color(r.nextInt());
            case Standard:
            default:
        }
        return currentColor;
    }

    public enum Table {

        Standard, Rainbow, SlowRainbow, Random, BlackNWhite, BlackNYellow, GermanFlag, JamaicaFlag
    }

}
