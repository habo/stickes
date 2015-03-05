package de.xonibo.stickes;

import static org.testng.Assert.*;
import org.testng.annotations.Test;

public class StichTest {

    @Test
    public void isOverRange() {
        final Stich first = new Stich(0, 0);
        int max = 120;
        assertFalse(first.isOverRange(new Stich(0, 0), max));
        assertFalse(first.isOverRange(new Stich(40, 30), max));
        assertFalse(first.isOverRange(new Stich(120, 120), max));
        assertTrue(first.isOverRange(new Stich(150, 30), max));
        assertTrue(first.isOverRange(new Stich(40, 300), max));

        assertFalse(first.isOverRange(new Stich(-40, -70), max));
        assertTrue(first.isOverRange(new Stich(-40, -300), max));
    }

}
