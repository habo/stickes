package de.xonibo.stickes.examples;

import de.xonibo.stickes.assemble.LindenmayerTurtle;
import de.xonibo.stickes.format.ImagePNG;
import de.xonibo.stickes.stiches.Plain;
import java.io.File;

public class LindenmayerExample {

    // formeln von http://kevs3d.co.uk/dev/lsystems/
    public static void main(String[] args) throws Exception {
        LindenmayerTurtle ls = new LindenmayerTurtle();

        // simple
        new ImagePNG().save(new File("target/output/", "ls.png"), new Plain(ls.getPath()).toStichData().normalize());

        ls = new LindenmayerTurtle(8, 45, 10, "L--F--L--F", "L=+R-F-R+", "R=-L+F+L-");
        new ImagePNG().save(new File("target/output/", "ls-sierpinski-median.png"), new Plain(ls.getPath()).toStichData());

        // buggy
        ls = new LindenmayerTurtle(4, 90, 10, "F", "F=F+F-F-F-G+F+F+F-F", "G=GGG");
        new ImagePNG().save(new File("target/output/", "ls-sierpinski-carpet.png"), new Plain(ls.getPath()).toStichData());

        // todo hilbert
//...
        // ok
        ls = new LindenmayerTurtle(7, 30, 10, "W", "W=+++X--F--ZFX+", "X=---W++F++YFW-", "Y=+ZFX--F--Z+++", "Z=-YFW++F++Y---");
        new ImagePNG().save(new File("target/output/", "ls-lace.png"), new Plain(ls.getPath()).toStichData().normalize());

        // ok
        ls = new LindenmayerTurtle(12, 90, 10, "FX", "X=X+YF+", "Y=-FX-Y");
        new ImagePNG().save(new File("target/output/", "ls-dragon.png"), new Plain(ls.getPath()).toStichData().normalize());

        // buggy
        ls = new LindenmayerTurtle(4, 72, 8, "F-F-F-F-F", "F=F-F++F+F-F-F");
        new ImagePNG().save(new File("target/output/", "ls-plesant-error.png"), new Plain(ls.getPath()).toStichData().normalize());

        // TODO stack missing
        ls = new LindenmayerTurtle(4, 22, 10, "F", "F=C0FF-[C1-F+F+F]+[C2+F-F-F]");
        new ImagePNG().save(new File("target/output/", "ls-kevs-tree.png"), new Plain(ls.getPath()).toStichData().normalize());

        // TODO constant missing
//        ls = new LindenmayerTurtle(5, 36, 10,"", "" );
//        new ImagePNG().save(new File("target/output/", "ls-penrose-tiling.png"), new Plain(ls.getPath()).toStichData().normalize());
        
        ls = new LindenmayerTurtle(4, 90, 10, "F", "F=F+F-F-F+F");
        new ImagePNG().save(new File("target/output/", "ls-koch-curve.png"), new Plain(ls.getPath()).toStichData().normalize());

        // buggy F missing
        ls = new LindenmayerTurtle(7, 60, 10, "A", "A=B-A-B", "B=A+B+A");
        new ImagePNG().save(new File("target/output/", "ls-arrowtip.png"), new Plain(ls.getPath()).toStichData().normalize());

        ls = new LindenmayerTurtle(6, 120, 10, "F-G-G", "F=F-G+F+G-F", "G=GG");
        new ImagePNG().save(new File("target/output/", "ls-koch-curve.png"), new Plain(ls.getPath()).toStichData().normalize());

        // TODO add constant function
//        ls = new LindenmayerTurtle("F++F++F", "F=F-F++F-F","X=FF"}, 4, 60, 10);
//        new ImagePNG().save(new File("target/output/", "ls-koch-snowflake.png"), new Plain(ls.getPath()).toStichData().normalize());
    }

}
