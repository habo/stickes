package de.xonibo.stickes.format;

/* 
 Copyright: GPL V2 http://www.gnu.org/copyleft/gpl.html
 Originally Created by Jackson Yee (jackson.yee@gmail.com) 2006
 File Format: http://www.achatina.de/sewing/main/TECHNICL.HTM
 */
    // siehe auch https://community.kde.org/Projects/Liberty/File_Formats/Tajima_Ternary#Tajima_Ternary_DST
import de.xonibo.stickes.Stich;
import de.xonibo.stickes.StichData;
import de.xonibo.stickes.StichType;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

public class Tajima implements StichFileLoad,StichFileSave{

    static byte[] STICHEOF = {0, 0, (byte) 0xF3};
    static byte[] STICHCOLORCHANGE = {0, 0, (byte) 0xC3};
    static int maxDistance = 121;

    private void update(StichData data) {
        Stich first = data.get(0);
        if (first.getX() != 0 && first.getY() != 0) {
            // TODO prüfen ob erster Centerstich so einsetzbar ist und in auch anderen Formaten benötigt wird
            Stich insertStich = new Stich(data.getMaxCornerX() / 2, data.getMaxCornerY() / 2, true);
            data.add(0, insertStich);
        }
    }

    boolean verifyRange(StichData data) {
        Stich previous = null;
        for (Stich stich : data) {
            if (previous != null && isOverRange(stich, previous)) {
                return false;
            }
            previous = stich;
        }
        return true;
    }

    @Override
    public void save(OutputStream out, StichData data) throws IOException, IllegalStateException {
//        update(data);
        String label = "";
        int stichcount = data.size();
        int colorcount = data.getColors().size() + 1;
        int maxXextend = data.getInitCornerX();
        int minXextend = 0; // NOSONAR
        int maxYextend = 0;// NOSONAR
        int minYextendI = 0;// NOSONAR
        int needleEndPointX = 0; // NOSONAR
        int needleEndPointY = 0;// NOSONAR
        int previousfileneedleendpointX = 0;// NOSONAR
        int previousfileneedleendpointY = 0;// NOSONAR
        // previousFile has asterix if single
        String previousFile = "******"; // NOSONAR
        String format = "LA:%16s\rST:%07d\rCO:%03d\r+X:%05d\r-X:%05d\r+Y:%05d\r-Y:%05d\rAX:-%5d\rAY:-%5d\rMX:+%5d\rMY:+%5d\rPD:%6s\r";
        String text = String.format(format, label, stichcount, colorcount, maxXextend, minXextend, maxYextend, minYextendI, needleEndPointX, needleEndPointY, previousfileneedleendpointX, previousfileneedleendpointY, previousFile);
        // 512 bytes leer
        out.write(text.getBytes());
        // end of data header
        out.write(0x1a); // NOSONAR
        for (int i = 0; i < 0x200 - text.length() - 1; i++) {// NOSONAR
            out.write(0x20);// NOSONAR
        }

        Stich previousStich = new Stich();
        for (Stich s : data) {
            out.write(encode(s, previousStich));
            if (!s.isColorChange()) {
                previousStich = s;
            }
        }
        if (!previousStich.isEOF()) {
            out.write(encode(new Stich(StichType.EOF), null));
        }
        // 0x1a ist bei SS auch so und funktioniert nur dann
        out.write(0x1a);// NOSONAR
        out.close();
    }

    public StichData load(File f) throws IOException {
        return load(new FileInputStream(f));
    }
    @Override
    public StichData load(InputStream in) throws IOException {
        StichData data = new StichData();

        in.skip(0x200); // NOSONAR
        byte[] b = new byte[3];// NOSONAR

        Stich previousStich = new Stich();

        while (in.available() > 0) {
            in.read(b, 0, 3);// NOSONAR
            if (in.available() < 3 || b[0] == 0 && b[1] == 0 && b[2] == 0xf3) {// NOSONAR
                data.add(new Stich(StichType.EOF));
                return data;
            }
            Stich current = decode(b, previousStich);
            data.add(current);
            if (!current.isColorChange()) {
                previousStich = current;
            }
        }
        return data;
    }

    protected byte[] encode(Stich stich, Stich previousstich) {
        if (stich.isEOF()) {
            return STICHEOF;
        }
        if (stich.isColorChange()) {
            return STICHCOLORCHANGE;
        }
        byte[] b = {0, 0, 0};
        int x = stich.getX();
        int y = stich.getY();

        int dx = x - previousstich.getX();
        int dy = y - previousstich.getY();

        if (dx > 40) {// NOSONAR
            b[2] |= 0x04;// NOSONAR
            dx -= 81;// NOSONAR
        }

        if (dx < -40) {// NOSONAR
            b[2] |= 0x08;// NOSONAR
            dx += 81;// NOSONAR
        }

        if (dy > 40) {// NOSONAR
            b[2] |= 0x10;// NOSONAR
            dy -= 81;// NOSONAR
        }

        if (dy < -40) {// NOSONAR
            b[2] |= 0x20;// NOSONAR
            dy += 81;// NOSONAR
        }

        if (dx > 13) {// NOSONAR
            b[1] |= 0x04;// NOSONAR
            dx -= 27;// NOSONAR
        }

        if (dx < -13) {// NOSONAR
            b[1] |= 0x08;// NOSONAR
            dx += 27;// NOSONAR
        }

        if (dy > 13) {// NOSONAR
            b[1] |= 0x10;// NOSONAR
            dy -= 27;// NOSONAR
        }

        if (dy < -13) {// NOSONAR
            b[1] |= 0x20;// NOSONAR
            dy += 27;// NOSONAR
        }

        if (dx > 4) {// NOSONAR
            b[0] |= 0x04;// NOSONAR
            dx -= 9;// NOSONAR
        }

        if (dx < -4) {// NOSONAR
            b[0] |= 0x08;// NOSONAR
            dx += 9;// NOSONAR
        }

        if (dy > 4) {// NOSONAR
            b[0] |= 0x10;// NOSONAR
            dy -= 9;// NOSONAR
        }

        if (dy < -4) {// NOSONAR
            b[0] |= 0x20;// NOSONAR
            dy += 9;// NOSONAR
        }

        if (dx > 1) {// NOSONAR
            b[1] |= 0x01;// NOSONAR
            dx -= 3;// NOSONAR
        }

        if (dx < -1) {// NOSONAR
            b[1] |= 0x02;// NOSONAR
            dx += 3;// NOSONAR
        }

        if (dy > 1) {// NOSONAR
            b[1] |= 0x40;// NOSONAR
            dy -= 3;// NOSONAR
        }

        if (dy < -1) {// NOSONAR
            b[1] |= 0x80;// NOSONAR
            dy += 3;// NOSONAR
        }

        if (dx > 0) {// NOSONAR
            b[0] |= 0x01;// NOSONAR
            dx -= 1;// NOSONAR
        }

        if (dx < 0) {// NOSONAR
            b[0] |= 0x02;// NOSONAR
            dx += 1;// NOSONAR
        }

        if (dy > 0) {// NOSONAR
            b[0] |= 0x40;// NOSONAR
            dy -= 1;// NOSONAR
        }

        if (dy < 0) {// NOSONAR
            b[0] |= 0x80;// NOSONAR
            dy += 1;// NOSONAR
        }

        if (stich.isJump()) {
            b[2] |= 0x83;// NOSONAR
        } else {
            b[2] |= 0x03;// NOSONAR
        }
        return b;

    }

    protected Stich decode(byte[] b, Stich previousStich) {
        if (b[0] == STICHEOF[0] && b[1] == STICHEOF[1] && b[2] == STICHEOF[2]) {
            return new Stich(StichType.EOF);
        }

        int x = previousStich.getX();
        int y = previousStich.getY();
        if ((b[0] & 0x01) != 0) {// NOSONAR
            x += 1;// NOSONAR
        }
        if ((b[0] & 0x02) != 0) {// NOSONAR
            x -= 1;// NOSONAR
        }
        if ((b[0] & 0x04) != 0) {// NOSONAR
            x += 9;// NOSONAR
        }

        if ((b[0] & 0x08) != 0) {// NOSONAR
            x -= 9;// NOSONAR
        }

        if ((b[0] & 0x80) != 0) {// NOSONAR x
            y -= 1;// NOSONAR
        }

        if ((b[0] & 0x40) != 0) {// NOSONAR
            y += 1;// NOSONAR
        }

        if ((b[0] & 0x20) != 0) {// NOSONAR
            y -= 9;// NOSONAR
        }

        if ((b[0] & 0x10) != 0) {// NOSONAR
            y += 9;// NOSONAR
        }

        if ((b[1] & 0x01) != 0) {// NOSONAR
            x += 3;// NOSONAR
        }

        if ((b[1] & 0x02) != 0) {// NOSONAR
            x -= 3;// NOSONAR
        }

        if ((b[1] & 0x04) != 0) {// NOSONAR
            x += 27;// NOSONAR
        }

        if ((b[1] & 0x08) != 0) {// NOSONAR
            x -= 27;// NOSONAR
        }

        if ((b[1] & 0x80) != 0) {// NOSONAR
            y -= 3;// NOSONAR
        }

        if ((b[1] & 0x40) != 0) {// NOSONAR
            y += 3;// NOSONAR
        }

        if ((b[1] & 0x20) != 0) {// NOSONAR
            y -= 27;// NOSONAR
        }

        if ((b[1] & 0x10) != 0) {// NOSONAR
            y += 27;// NOSONAR
        }

        if ((b[2] & 0x04) != 0) {// NOSONAR
            x += 81;// NOSONAR
        }

        if ((b[2] & 0x08) != 0) {// NOSONAR
            x -= 81;// NOSONAR
        }

        if ((b[2] & 0x20) != 0) {// NOSONAR
            y -= 81;// NOSONAR
        }

        if ((b[2] & 0x10) != 0) {// NOSONAR
            y += 81;// NOSONAR
        }

        // Color change
        if ((b[2] & 0xC0) == 192) {// NOSONAR
            return new Stich(StichData.createRandomColor());
        }
        boolean isJump = (b[2] & 0x80) != 0;// NOSONAR
        return new Stich(x, y, isJump);
    }

    @Override
    public String getExtention() {
        return ".dst";
    }

    private boolean isOverRange(Stich current, Stich last) {
        int dx = current.getX() - last.getX();
        int dy = current.getY() - last.getY();
        if (dx > maxDistance || dy > maxDistance) {
            return true;
        }
        return false;
    }

    public void save(File f, StichData data) throws Exception {
        save(new FileOutputStream(f), data);
    }

}
