package de.xonibo.stickes.format;

import de.xonibo.stickes.StichData;
import java.io.File;
import java.io.InputStream;

public interface StichFileLoad {

    public StichData load(InputStream in) throws Exception;

    public String getExtention();
}
