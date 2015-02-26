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
}
