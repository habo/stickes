package de.xonibo.stickes.format;

import de.xonibo.stickes.Stich;
import de.xonibo.stickes.StichData;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintStream;

public class StickesSourceOutput implements StichFileSave {

    @Override
    public void save(OutputStream out, StichData data) throws IOException {
        PrintStream ps = new PrintStream(out);
        ps.println("StichData data = " + data.getSource() + ";");
        for (Stich stich : data) {
            ps.println("data.add( " + stich.getSource() + " );");
        }
        ps.close();
    }

    @Override
    public String getExtention() {
        return ".stickessourcejava";
    }

    public void save(File f, StichData data) throws Exception {
        save(new FileOutputStream(f), data);
    }
}
