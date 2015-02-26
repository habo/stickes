package de.xonibo.stickes.format;

import de.xonibo.stickes.Stich;
import de.xonibo.stickes.StichData;
import de.xonibo.stickes.StichType;
import java.awt.Color;
import java.awt.Point;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/*
 Ursprünglich unter zlib license vom Projekt Embroidermodder siehe licenses/ZLIB-LICENSE.txt
 https://github.com/Embroidermodder/Embroidermodder/blob/master/libembroidery/emb-pattern.h
 https://github.com/Embroidermodder/Embroidermodder/blob/master/libembroidery/format-pes.h
 https://github.com/Embroidermodder/Embroidermodder/blob/master/libembroidery/format-pec.h
 https://github.com/Embroidermodder/Embroidermodder/blob/master/libembroidery/format-pes.c
 */
// vergleiche Format mit https://gitorious.org/thred/pages/EmbroideryFormats

public class PES implements StichFileLoad{

    static public Color pecThreads[] = {
        new Color(0, 0, 0), //, "Unknown", ""}, /* Index 0 */
        new Color(14, 31, 124),// }, "Prussian Blue", ""}, /* Index 1 */
        new Color(10, 85, 163),// }, "Blue", ""}, /* Index 2 */
        new Color(0, 135, 119),// }, "Teal Green", ""}, /* Index 3 */ /* TODO: Verify RGB value is correct */
        new Color(75, 107, 175),// }, "Cornflower Blue", ""}, /* Index 4 */
        new Color(237, 23, 31),// }, "Red", ""}, /* Index 5 */
        new Color(209, 92, 0),// }, "Reddish Brown", ""}, /* Index 6 */
        new Color(145, 54, 151),// }, "Magenta", ""}, /* Index 7 */
        new Color(228, 154, 203),// }, "Light Lilac", ""}, /* Index 8 */
        new Color(145, 95, 172),// }, "Lilac", ""}, /* Index 9 */
        new Color(158, 214, 125),// }, "Mint Green", ""}, /* Index 10 */ /* TODO: Verify RGB value is correct */
        new Color(232, 169, 0),// }, "Deep Gold", ""}, /* Index 11 */
        new Color(254, 186, 53),// }, "Orange", ""}, /* Index 12 */
        new Color(255, 255, 0),// }, "Yellow", ""}, /* Index 13 */
        new Color(112, 188, 31),// }, "Lime Green", ""}, /* Index 14 */
        new Color(186, 152, 0),// }, "Brass", ""}, /* Index 15 */
        new Color(168, 168, 168),// }, "Silver", ""}, /* Index 16 */
        new Color(125, 111, 0),// }, "Russet Brown", ""}, /* Index 17 */ /* TODO: Verify RGB value is correct */
        new Color(255, 255, 179),// }, "Cream Brown", ""}, /* Index 18 */
        new Color(79, 85, 86),// }, "Pewter", ""}, /* Index 19 */
        new Color(0, 0, 0),// }, "Black", ""}, /* Index 20 */
        new Color(11, 61, 145),// }, "Ultramarine", ""}, /* Index 21 */
        new Color(119, 1, 118),// }, "Royal Purple", ""}, /* Index 22 */
        new Color(41, 49, 51),// }, "Dark Gray", ""}, /* Index 23 */
        new Color(42, 19, 1),// }, "Dark Brown", ""}, /* Index 24 */
        new Color(246, 74, 138),// }, "Deep Rose", ""}, /* Index 25 */
        new Color(178, 118, 36),// }, "Light Brown", ""}, /* Index 26 */
        new Color(252, 187, 197),// }, "Salmon Pink", ""}, /* Index 27 */ /* TODO: Verify RGB value is correct */
        new Color(254, 55, 15),// }, "Vermillion", ""}, /* Index 28 */
        new Color(240, 240, 240),// }, "White", ""}, /* Index 29 */
        new Color(106, 28, 138),// }, "Violet", ""}, /* Index 30 */
        new Color(168, 221, 196),// }, "Seacrest", ""}, /* Index 31 */
        new Color(37, 132, 187),// }, "Sky Blue", ""}, /* Index 32 */
        new Color(254, 179, 67),// }, "Pumpkin", ""}, /* Index 33 */
        new Color(255, 243, 107),// }, "Cream Yellow", ""}, /* Index 34 */
        new Color(208, 166, 96),// }, "Khaki", ""}, /* Index 35 */
        new Color(209, 84, 0),// }, "Clay Brown", ""}, /* Index 36 */
        new Color(102, 186, 73),// }, "Leaf Green", ""}, /* Index 37 */
        new Color(19, 74, 70),// }, "Peacock Blue", ""}, /* Index 38 */
        new Color(135, 135, 135),// }, "Gray", ""}, /* Index 39 */
        new Color(216, 204, 198),// }, "Warm Gray", ""}, /* Index 40 */ /* TODO: Verify RGB value is correct */
        new Color(67, 86, 7),// }, "Dark Olive", ""}, /* Index 41 */
        new Color(253, 217, 222),// }, "Flesh Pink", ""}, /* Index 42 */ /* TODO: Verify RGB value is correct */
        new Color(249, 147, 188),// }, "Pink", ""}, /* Index 43 */
        new Color(0, 56, 34),// }, "Deep Green", ""}, /* Index 44 */
        new Color(178, 175, 212),// }, "Lavender", ""}, /* Index 45 */
        new Color(104, 106, 176),// }, "Wisteria Violet", ""}, /* Index 46 */
        new Color(239, 227, 185),// }, "Beige", ""}, /* Index 47 */
        new Color(247, 56, 102),// }, "Carmine", ""}, /* Index 48 */
        new Color(181, 75, 100),// }, "Amber Red", ""}, /* Index 49 */ /* TODO: Verify RGB value is correct */
        new Color(19, 43, 26),// }, "Olive Green", ""}, /* Index 50 */
        new Color(199, 1, 86),// }, "Dark Fuschia", ""}, /* Index 51 */ /* TODO: Verify RGB value is correct */
        new Color(254, 158, 50),// }, "Tangerine", ""}, /* Index 52 */
        new Color(168, 222, 235),// }, "Light Blue", ""}, /* Index 53 */
        new Color(0, 103, 62),// }, "Emerald Green", ""}, /* Index 54 */ /* TODO: Verify RGB value is correct */
        new Color(78, 41, 144),// }, "Purple", ""}, /* Index 55 */
        new Color(47, 126, 32),// }, "Moss Green", ""}, /* Index 56 */
        new Color(255, 204, 204),// }, "Flesh Pink", ""}, /* Index 57 */ /* TODO: Verify RGB value is correct */ /* TODO: Flesh Pink is Index 42, is this Index incorrect? */
        new Color(255, 217, 17),// }, "Harvest Gold", ""}, /* Index 58 */
        new Color(9, 91, 166),// }, "Electric Blue", ""}, /* Index 59 */
        new Color(240, 249, 112),// }, "Lemon Yellow", ""}, /* Index 60 */
        new Color(227, 243, 91),// }, "Fresh Green", ""}, /* Index 61 */
        new Color(255, 153, 0),// }, "Orange", ""}, /* Index 62 */ /* TODO: Verify RGB value is correct */ /* TODO: Orange is Index 12, is this Index incorrect? */
        new Color(255, 240, 141),// }, "Cream Yellow", ""}, /* Index 63 */ /* TODO: Verify RGB value is correct */ /* TODO: Cream Yellow is Index 34, is this Index incorrect? */
        new Color(255, 200, 200),// }, "Applique", ""} /* Index 64 */
    };

    private String title;
    private List<Color> colors;
    private Point size;
    private byte[] graphicdata;
    private int stitchNumber = 0;

    public String getTitle() {
        return title;
    }

    public List<Color> getColors() {
        return colors;
    }

    public Point getSize() {
        return size;
    }

    public int getStitchNumber() {
        return stitchNumber;
    }

    public byte[] getGraphicdata() {
        return graphicdata;
    }

    @Override
    public String getExtention() {
        return ".pes";
    }

    void seek(Scanner scanner, int pos) {
        for (int i = 0; i < pos; i++) {
            scanner.nextByte();
        }
    }

    @Override
    public StichData load(InputStream in) throws Exception {
        BufferedInputStream bs = new BufferedInputStream(in, 500 * 1024);
        bs.skip(8);
        long pecstart = read3Int(bs);
        long seek = bs.skip(pecstart - 11); // 8+3 bytes
        if (seek != pecstart - 11) {
            throw new IOException("Seek pos " + seek + " not equals to pecstart: " + pecstart);
        }
        bs.mark(1024 * 1024);
        title = readString(bs, 0, 8);
        colors = readColors(bs, 48);
        size = readSize(bs, 520);
        long graphicstart = readGraphicStart(bs, 514) + 512 + pecstart;
        graphicdata = readGraphic(bs, graphicstart, 228);
        return readPecStitches(bs, 532);
    }

    public byte[] readGraphic(BufferedInputStream bs, long graphicstart, int size) throws IOException {
        bs.skip(graphicstart);
        byte[] gdBs = readBytes(bs, size);
        bs.reset();
        return gdBs;
    }

    public long readGraphicStart(BufferedInputStream bs, long skip) throws IOException {
        bs.skip(skip);
        long graphicstart = read3Int(bs);
        bs.reset();
        return graphicstart;
    }

    public Point readSize(BufferedInputStream bs, long skip) throws IOException {
        bs.skip(skip);
        int x = read2Int(bs);
        int y = read2Int(bs);
        bs.reset();
        return new Point(x, y);
    }

    public List<Color> readColors(BufferedInputStream bs, long skip) throws IOException {
        bs.skip(skip);
        int numColors = readByte(bs) + 1;
        List<Color> listColors = new ArrayList<>();
        for (int x = 0; x < numColors; x++) {
            Color c = pecThreads[bs.read()];
            listColors.add(c);
        }
        bs.reset();
        return listColors;
    }

    private Color getColor(int colornr) {
        if (getColors().isEmpty()) {
            return pecThreads[colornr];
        }
        return getColors().get(colornr - 1);
    }

    private StichData readPecStitches(BufferedInputStream bs, long skip) throws IOException {
        bs.skip(skip);

        StichData sd = new StichData();

        if (!getColors().isEmpty()) {
            sd.add(new Stich(getColor(1)));
        }

        Stich last = new Stich();
        while (bs.available() > 0) {
            int val1 = readByte(bs);
            int val2 = readByte(bs);
            if (val1 == 0xFF && val2 == 0x00) {
                sd.add(new Stich(StichType.EOF));
                break;
            }
            if (val1 == 0xFE && val2 == 0xB0) { // colorchange
                sd.add(new Stich(getColor(readByte(bs))));
                stitchNumber++;
                continue;
            }
            /* High bit set means 12-bit offset, otherwise 7-bit signed delta */
            StichType stitchType = StichType.POSITION;
            if ((val1 & 0x80) != 0) {
                if ((val1 & 0x20) != 0) {
                    stitchType = StichType.TRIM;
                }
                if ((val1 & 0x10) != 0) {
                    stitchType = StichType.JUMP;
                }
                val1 = ((val1 & 0x0F) << 8) + val2;
                /* Signed 12-bit arithmetic */
                if ((val1 & 0x0800) != 0) {
                    val1 -= 0x1000;
                }
                val2 = readByte(bs);
            } else if (val1 >= 0x40) {
                val1 -= 0x80;
            }
            if ((val2 & 0x80) != 0) {
                if ((val2 & 0x20) != 0) {
                    stitchType = StichType.TRIM;
                }
                if ((val2 & 0x10) != 0) {
                    stitchType = StichType.JUMP;
                }
                val2 = ((val2 & 0x0F) << 8) + readByte(bs);
                /* Signed 12-bit arithmetic */
                if ((val2 & 0x800) != 0) {
                    val2 -= 0x1000;
                }
            } else if (val2 >= 0x40) {
                val2 -= 0x80;
            }
            int nx = last.getX() + val1;
            int ny = last.getY() + val2;
            Stich s = new Stich(nx, ny, stitchType);
            sd.add(s);
            last = s;
            stitchNumber++;
        }
        return sd;
    }

    private int read2Int(InputStream in) throws IOException {
        byte[] b = new byte[2];
        in.read(b, 0, 2);
        return (int) (b[1] * 256 + b[0]);
    }

    private long read3Int(InputStream in) throws IOException {
        byte[] b = new byte[3];
        in.read(b, 0, 3);
        return (long) (b[2] * 256 * 256 + b[1] * 256 + b[0]);
    }

    private byte[] readBytes(BufferedInputStream bs, int size) throws IOException {
        byte[] b = new byte[size];
        bs.read(b, 0, size);
        return b;
    }

    private int readByte(InputStream in) throws IOException {
        return in.read();
    }

    private String readString(BufferedInputStream bs, long skip, int length) throws IOException {
        bs.skip(skip);
        byte[] b = new byte[length];
        bs.read(b, 0, length);
        bs.reset();
        return new String(b);
    }

    public StichData load(File f) throws Exception {
        return load(new FileInputStream(f));
    }

}
