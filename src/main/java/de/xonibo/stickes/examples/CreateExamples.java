package de.xonibo.stickes.examples;

import de.xonibo.stickes.StichData;
import de.xonibo.stickes.assemble.DragonCurve;
import de.xonibo.stickes.assemble.Knaeuel;
import de.xonibo.stickes.assemble.KochSnowFlake;
import de.xonibo.stickes.awt.Examples;
import de.xonibo.stickes.format.ImagePNG;
import de.xonibo.stickes.format.Tajima;
import de.xonibo.stickes.stiches.Plain;
import java.io.File;
import java.io.FileOutputStream;
import java.util.logging.Logger;

public class CreateExamples {

    public static void saveDstAndPNG(String s, StichData sd) throws Exception {
        final String path = "target/output/examples";
        final Tajima t = new Tajima();
        new File(path).mkdirs();
        final File dst = new File(path, s + ".dst");
        Logger.getGlobal().info(String.format("write %s", dst.getName()));
        t.save(dst, sd);
        final File png = new File(path, s + ".png");
        Logger.getGlobal().info(String.format("write %s", png.getName()));
        new ImagePNG().save(new FileOutputStream(png), t.load(dst));

//        System.out.println(s + " - info: " + sd.getInfo());
//        for (int i = 0; i < Math.min(6, sd.size()-1); i++) {
//            System.out.println("  get" + i + "s: " + sd.get(i));
//        }
//        System.out.println("  get e: " + sd.get(sd.size() - 1));
//        System.out.println("  getix: " + sd.getInitCornerX());
//        System.out.println("  getiy: " + sd.getInitCornerY());
//        System.out.println("  getmx: " + sd.getMaxCornerX());
//        System.out.println("  getmy: " + sd.getMaxCornerY());
    }

    public static void main(String[] a) throws Exception {

        for (Examples.ExampleEnum e : Examples.ExampleEnum.values()) {
            StichData sd = new Plain(e.getShape(null)).toStichData().insertCenterStichAtStart();
            saveDstAndPNG(e.name(), sd);
        }
        for (int i = 5; i < 19; i++) {
            StichData sd = new Plain(new DragonCurve(0, 0, i, 10).getPath()).toStichData().insertCenterStichAtStart();
            saveDstAndPNG("Dragon_l10_dim" + i, sd);
        }
        for (int i = 2; i < 6; i++) {
            StichData sd = new Plain(new KochSnowFlake(i).getPath()).toStichData().insertCenterStichAtStart();
            saveDstAndPNG("Koch_" + i, sd);
        }
        for (int aw = 1; aw < 80; aw++) {
            StichData sd = new Plain(new Knaeuel(1000, 20, 4, aw).getPath()).toStichData();
            saveDstAndPNG("Knaeuel-n1000-strecke20-winkel4-aw" + aw, sd);
        }
    }
}
