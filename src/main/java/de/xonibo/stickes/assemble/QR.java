package de.xonibo.stickes.assemble;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;
import java.io.ByteArrayOutputStream;
import net.glxn.qrgen.javase.QRCode;

public class QR {

    public static void main(String[] a) throws Exception {
        new QR().create("ehhloo");
    }

    public void create(String text) throws Exception{
        BitMatrix m = new QRCodeWriter().encode(text, BarcodeFormat.QR_CODE, 0, 0);

        for (int i = 0; i < m.getHeight(); i++) {
            for (int j = 0; j < m.getWidth(); j++) {
                System.out.print(m.get(j, i)?"\u2588":" ");
            }
            System.out.println("");
        }
    }

}
