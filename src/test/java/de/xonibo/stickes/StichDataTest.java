package de.xonibo.stickes;

import java.awt.Color;
import java.io.IOException;
import static org.testng.Assert.*;
import org.testng.annotations.Test;

public class StichDataTest {

    @Test
    public void testAddList() {
        StichData a = new StichData();
        StichData b = new StichData();

        a.add(new Stich(1, 1));
        a.add(new Stich(5, 5));

        b.add(new Stich(2, 2));
        b.add(new Stich(3, 4));

        a.addAll(b);
        assertEquals(a.size(), 4);

    }

    @Test
    public void testStichDataColor() throws IOException {
        StichData sd = new StichData();
        sd.add(new Stich(Color.yellow));
        sd.add(new Stich(Color.blue));
        sd.add(new Stich(Color.green));
        assertEquals(sd.getColors().size(), 3);
    }

    @Test
    public void testStichDataStiches() throws IOException {
        StichData sd = new StichData();
        sd.add(new Stich(1, 1));
        sd.add(new Stich(3, 4));
        assertEquals(sd.size(), 2);
    }

    @Test
    public void testStichDataJumps() throws IOException {
        StichData sd = new StichData();
        sd.add(new Stich(1, 2, 4));
        sd.add(new Stich(3, 4, true));
        sd.add(new Stich(4, 5, 6));
        sd.add(new Stich(13, 44, true));
        assertEquals(sd.getJumps(), 2);
    }

    @Test
    public void testGetMax() {
        {
            StichData sd = new StichData();
            sd.add(new Stich(1, 2));
            sd.add(new Stich(3, 4));
            assertEquals(sd.getMaxCornerX(), 3);
            assertEquals(sd.getMaxCornerY(), 4);
        }
        {
            StichData sd = new StichData();
            sd.add(new Stich(-1, 2));
            sd.add(new Stich(3, -4));
            assertEquals(sd.getMaxCornerX(), 3);
            assertEquals(sd.getMaxCornerY(), 2);
        }
    }

    @Test
    public void testGetInit() {
        {
            StichData sd = new StichData();
            sd.add(new Stich(1, 2));
            sd.add(new Stich(3, 4));
            assertEquals(sd.getInitCornerX(), 0);
            assertEquals(sd.getInitCornerY(), 0);
        }
        {
            StichData sd = new StichData();
            sd.add(new Stich(-1, 2));
            sd.add(new Stich(3, -4));
            assertEquals(sd.getInitCornerX(), -1);
            assertEquals(sd.getInitCornerY(), -4);
        }
    }

    @Test
    public void insertCenterStich() {
        {
            StichData sd = new StichData();
            // startstich irgendwo
            sd.add(new Stich(12, 32));
            // irgendwo noch ein anderer stich, aber innerhalb der maximalwerte
            sd.add(new Stich(10, 14));
            // sollte einen stich am anfang einfügen, der mittig liegt
            sd.insertCenterStichAtStart();
            
            Stich first = sd.get(0);

            // size 3, da kein stich weiterer stich hinzugefügt wurde
            assertEquals(sd.size(), 3);
            assertEquals(first.getX(), 6);
            assertEquals(first.getY(), 16);
        }
        {
            StichData sd = new StichData();
            // startstich irgendwo
            sd.add(new Stich(120, 320));
            // irgendwo noch ein anderer stich, aber innerhalb der maximalwerte
            sd.add(new Stich(101, 304));
            // sollte einen stich am anfang einfügen, der mittig liegt
            sd.insertCenterStichAtStart();
            Stich first = sd.get(0);

            // size 4, da ein intermediate benötigt wurde
            assertEquals(sd.size(), 3);
            assertEquals(first.getX(), 60);
            assertEquals(first.getY(), 160);
        }
    }

    @Test
    public void intermediateStiches1() {
        final Stich first = new Stich(10, 10);
        final Stich next = new Stich(400, 500);
        StichData sd = StichData.createIntermediateStiches(first, next, 120);
        assertEquals(sd.size(), 7);
    }

    @Test
    public void intermediateStichesX() {
        final Stich first = new Stich(0, 0);
        final Stich next = new Stich(100, 0);
        StichData sd = StichData.createIntermediateStiches(first, next, 10);
        assertEquals(sd.size(), 15);
    }

    @Test
    public void intermediateStichesY() {
        final Stich first = new Stich(0, 0);
        final Stich next = new Stich(0, 200);
        StichData sd = StichData.createIntermediateStiches(first, next, 10);
        assertEquals(sd.size(), 31);
    }

    @Test
    public void intermediateStichesXY() {
        final Stich first = new Stich(0, 0);
        final Stich next = new Stich(100, 200);
        StichData sd = StichData.createIntermediateStiches(first, next, 10);
        assertEquals(sd.size(), 31);
        assertEquals(sd.get(0).getX(), 3);
        assertEquals(sd.get(4).getX(), 15);
        assertEquals(sd.get(8).getX(), 28);
        assertEquals(sd.get(19).getX(), 62);

        assertEquals(sd.get(0).getY(), 6);
        assertEquals(sd.get(4).getY(), 31);
        assertEquals(sd.get(8).getY(), 56);
        assertEquals(sd.get(19).getY(), 125);
    }
}
