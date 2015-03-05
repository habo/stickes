package de.xonibo.stickes.examples;

import de.xonibo.stickes.Stich;
import de.xonibo.stickes.StichData;
import de.xonibo.stickes.StichType;
import static org.testng.Assert.*;
import org.testng.annotations.Test;

public class SimpleTest {

    @Test()
    public void simple1() {
        StichData sd = new StichData();

        sd.add(new Stich(20, 0));
        sd.add(new Stich(40, 50));
        sd.add(new Stich(60, 100));
        sd.add(new Stich(80, 150));
        sd.add(new Stich(100, 200));
        sd.add(new Stich(StichType.EOF));
        assertEquals(sd.size(), 6);
        sd.insertCenterStichAtStart();
        assertEquals(sd.size(), 7);

        sd.addIntermediateStichesIfNessessary(52);
        assertEquals(sd.size(), 8);
        
//        for (Stich s : sd) {
//            System.out.println("stich: "+s);
//        }
        try {
            CreateExamples.saveDstAndPNG("simple1", sd);
        } catch (Exception ex) {
            fail("unexpected exception", ex);
        }
    }

    @Test()
    public void simple2() {
        StichData sd = new StichData();

        for (int i = 0; i < 100; i++) {
            sd.add(new Stich(30 + (i % 2 == 0 ? +10 : -10), 10 + i));
        }
        sd.add(new Stich(StichType.EOF));

        try {
            CreateExamples.saveDstAndPNG("simple2", sd);
        } catch (Exception ex) {
            fail("unexpected exception", ex);
        }
    }
}
