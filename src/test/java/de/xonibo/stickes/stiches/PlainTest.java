package de.xonibo.stickes.stiches;

import java.awt.geom.Line2D;
import org.testng.annotations.Test;
import static org.testng.Assert.*;
import org.testng.annotations.Test;

public class PlainTest {

    @Test
    public void toStichDataNull() {
        assertNotNull(new Plain(null).toStichData());
    }

    @Test
    public void toStichDataLine() {
        assertNotNull(new Plain(new Line2D.Float()).toStichData());
    }

}
