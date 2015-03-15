package de.xonibo.stickes.examples;

import com.google.zxing.common.BitMatrix;
import de.xonibo.stickes.StichData;
import de.xonibo.stickes.assemble.QR;
import static de.xonibo.stickes.assemble.QR.createBitMatrix;
import de.xonibo.stickes.format.ImagePNG;
import java.io.File;

public class QRCodeExample {

    public static void main(String[] a) throws Exception {
        QR qr = new QR();

        System.out.println(QRCodeExample.printBitMatrix("EhLoo0 w0RlD"));

        {
            StichData sd = QR.square(0, 0, 10, 5).normalize();
            new ImagePNG().save(new File("target/output/", "qr-square.png"), sd);
        }
        {
            BitMatrix matrix = new BitMatrix(3, 3);
            matrix.set(0, 0);
            matrix.set(1, 0);
            matrix.set(2, 0);

            matrix.set(0, 2);
            matrix.set(1, 2);
            matrix.set(2, 2);

            matrix.set(0, 1);
            matrix.set(2, 1);
            StichData sd = QR.toStichData(matrix, 10, 5);
            new ImagePNG().save(new File("target/output/", "qr-matrix.png"), sd);
        }
        {
            BitMatrix matrix = new BitMatrix(5, 5);
            for (int x = 0; x < matrix.getWidth(); x++) {
                for (int y = 0; y < matrix.getWidth(); y++) {
                    if ((x + y) % 2 == 0) {
                        matrix.set(x, y);
                    }
                }
            }
            StichData sd = QR.toStichData(matrix, 10, 5);
            new ImagePNG().save(new File("target/output/", "qr-chess.png"), sd);
        }
        {
            StichData sd = qr.toStichData();
            new ImagePNG().save(new File("target/output/", "qr-full.png"), sd.normalize());
        }
    }

    static public String printBitMatrix(String text) throws Exception {
        BitMatrix m = QR.createBitMatrix(text);
        StringBuilder s = new StringBuilder();
        for (int i = 0; i < m.getHeight(); i++) {
            for (int j = 0; j < m.getWidth(); j++) {
                s.append(m.get(j, i) ? "\u2588" : " ");
            }
            s.append("\n");
        }
        return s.toString();
    }

}
