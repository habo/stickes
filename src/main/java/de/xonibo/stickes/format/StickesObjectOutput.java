package de.xonibo.stickes.format;

import de.xonibo.stickes.StichData;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.OutputStream;

public class StickesObjectOutput implements StichFileSave {

    @Override
    public void save(OutputStream out, StichData data) throws IOException {
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(out);
        objectOutputStream.writeObject(data);
        objectOutputStream.close();
    }

    @Override
    public String getExtention() {
        return ".stickes";
    }

    public void save(File f, StichData data) throws Exception {
        save(new FileOutputStream(f), data);
    }

}
