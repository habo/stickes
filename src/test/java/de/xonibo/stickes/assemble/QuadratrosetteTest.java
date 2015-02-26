package de.xonibo.stickes.assemble;

import static org.testng.Assert.assertEquals;

public class QuadratrosetteTest {

    @org.testng.annotations.Test
    public void countElements() {
        assertEquals(new Quadratrosette().getPathSize(),289);
    }
}
