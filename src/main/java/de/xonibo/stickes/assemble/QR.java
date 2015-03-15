package de.xonibo.stickes.assemble;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;
import de.xonibo.stickes.Stich;
import de.xonibo.stickes.StichData;
import de.xonibo.stickes.format.ImagePNG;
import java.io.File;

public class QR {

    public static StichData toStichData(BitMatrix matrix, int dim, int f) {
        StichData sd = new StichData();
        sd.add(new Stich(0, 0));
        for (int j = 0; j < matrix.getWidth(); j++) {
            for (int i = 0; i < matrix.getHeight(); i++) {
                if (matrix.get(j, i)) {
                    sd.addAll(QR.square(j * dim * f, i * dim * f, dim, f));
                }
            }
        }
        return sd;
    }

    public static StichData square(int startx, int starty, int dim, int f) {
        StichData sd = new StichData();
        sd.add(new Stich(startx, starty, true));
        for (int i = 0; i < dim * f; i += f) {
            sd.add(new Stich(startx + i, starty));
            sd.add(new Stich(startx + i, starty + (dim * f)));
        }
        return sd;
    }

    public static BitMatrix createBitMatrix(String text) throws Exception {
        return new QRCodeWriter().encode(text, BarcodeFormat.QR_CODE, 0, 0);
    }

    private int stichweite = 5;
    private int dimension = 10;
    private String text = "Stickes";

    public QR() {
    }
    
    public QR(String text, int dim, int stichweite) {
        this.text = text;
        this.dimension = dim;
        this.stichweite = stichweite;
    }

    public StichData toStichData() throws Exception {
        BitMatrix matrix = createBitMatrix(text);
        return toStichData(matrix, dimension, stichweite);
    }

}
