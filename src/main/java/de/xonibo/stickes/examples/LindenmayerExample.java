package de.xonibo.stickes.examples;

import de.xonibo.stickes.assemble.LindenmayerTurtle;
import de.xonibo.stickes.format.ImagePNG;
import de.xonibo.stickes.stiches.Plain;
import java.io.File;

public class LindenmayerExample {

    // formeln von http://kevs3d.co.uk/dev/lsystems/
    public static void main(String[] args) throws Exception {
        LindenmayerTurtle ls;

        ls = new LindenmayerTurtle(4, 22, 10, "F", "F=C0FF-[C1-F+F+F]+[C2+F-F-F]");
        save("ls-kevs-tree", ls);

        ls = new LindenmayerTurtle();
        save("ls", ls);

        ls = new LindenmayerTurtle(8, 45, 10, "L--F--L--F", "L=+R-F-R+", "R=-L+F+L-");
        save("ls-sierpinski-median", ls);

        // buggy
        ls = new LindenmayerTurtle(4, 90, 10, "F", "F=F+F-F-F-G+F+F+F-F", "G=GGG");
        save("ls-sierpinski-carpet", ls);

        ls = new LindenmayerTurtle(6, 90, 10, "X", "X=-YF+XFX+FY-", "Y=+XF-YFY-FX+");
        save("ls-hilbert", ls);

        // ok
        ls = new LindenmayerTurtle(7, 30, 10, "W", "W=+++X--F--ZFX+", "X=---W++F++YFW-", "Y=+ZFX--F--Z+++", "Z=-YFW++F++Y---");
        save("ls-lace", ls);

        // ok
        ls = new LindenmayerTurtle(12, 90, 10, "FX", "X=X+YF+", "Y=-FX-Y");
        save("ls-dragon", ls);

        // buggy
        ls = new LindenmayerTurtle(4, 72, 8, "F-F-F-F-F", "F=F-F++F+F-F-F");
        save("ls-plesant-error", ls);

        ls = new LindenmayerTurtle(5, 25, 10, "FX", "F=C0FF-[C1-F+F]+[C2+F-F]", "X=C0FF+[C1+F]+[C3-F]");
        save("ls-kevs-wisply-tree", ls);

        ls = new LindenmayerTurtle(5, 27, 10, "F", "F=C0FF[C1-F++F][C2+F--F]C3++F--F");
        save("ls-kevs-pond-weed", ls);

        ls = new LindenmayerTurtle(6, 25, 10, "X", "X=C0F-[C2[X]+C3X]+C1F[C3+FX]-X", "F=FF");
        save("ls-fractal-plant", ls);

        ls = new LindenmayerTurtle(4, 90, 10, "-F", "F=F+F-F-F+F");
        save("ls-koch-curve", ls);

        ls = new LindenmayerTurtle(7, 60, 10, "A", "A=B-A-B", "B=A+B+A");
        save("ls-arrowtip", ls);

        ls = new LindenmayerTurtle(6, 120, 10, "F-G-G", "F=F-G+F+G-F", "G=GG");
        save("ls-koch-curve", ls);

        // buggy
        ls = new LindenmayerTurtle(4, 60, 10, "F++F++F", "F=F-F++F-F", "X=FF");
        save("ls-koch-snowflake", ls);

        //buggy
        ls = new LindenmayerTurtle(5, 36, 10, "[7]++[7]++[7]++[7]++[7]", "6=81++91----71[-81----61]++", "7=+81--91[---61--71]+", "8=-61++71[+++81++91]-", "9=--81++++61[+91++++71]--71", "1=");
        save("ls-penrose-tiling", ls);

        ls = new LindenmayerTurtle(3, 90, 10, "XYXYXYX+XYXYXYX+XYXYXYX+XYXYXYX", "F=","X=FX+FX+FXFY-FY-","Y=+FX+FXFY-FY-FY");
        save("ls-joined-cross", ls);
    }

    public static void save(String name, LindenmayerTurtle ls) throws Exception {
        ImagePNG img = new ImagePNG();
        img.setShowInfo(true);
        img.save(new File("target/output/", name + ".png"), new Plain(ls.getPath()).toStichData());
    }

}
