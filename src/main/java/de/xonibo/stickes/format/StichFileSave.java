package de.xonibo.stickes.format;

import de.xonibo.stickes.StichData;
import java.io.File;
import java.io.OutputStream;

public interface StichFileSave {

    public void save(OutputStream out, StichData data) throws Exception;

    public String getExtention();
}
