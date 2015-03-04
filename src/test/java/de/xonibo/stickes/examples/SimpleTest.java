package de.xonibo.stickes.examples;

import de.xonibo.stickes.Stich;
import de.xonibo.stickes.StichData;
import de.xonibo.stickes.StichType;
import de.xonibo.stickes.TestingData;
import de.xonibo.stickes.format.ImagePNG;
import de.xonibo.stickes.format.Tajima;
import java.io.File;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import static org.testng.Assert.*;
import org.testng.annotations.Test;

public class SimpleTest {

    @Test()
    public SimpleTest() {
        StichData sd = new StichData();

        for (int i = 0; i < 100; i++) {
            sd.add(new Stich(30 + (i % 2 == 0 ? +10 : -10), 10 + i));
        }
        sd.add(new Stich(StichType.EOF));

        try {
            CreateExamples.saveDstAndPNG("simple", sd);
        } catch (Exception ex) {
            fail("unexpected exception", ex);
        }
    }

}
