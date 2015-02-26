package de.xonibo.stickes.stiches;

import java.awt.geom.Line2D;
import static org.testng.Assert.*;
import org.testng.annotations.Test;

public class SatinTest {

    @Test
    public void toStichDataNull() {
        assertNotNull(new Satin(null).toStichData());
    }

    @Test
    public void toStichDataLine() {
        assertNotNull(new Satin(new Line2D.Float()).toStichData());
    }
}
