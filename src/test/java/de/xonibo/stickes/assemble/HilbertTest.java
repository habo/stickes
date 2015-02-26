package de.xonibo.stickes.assemble;

import static org.testng.Assert.assertEquals;

public class HilbertTest {

    @org.testng.annotations.Test
    public void countElements() {
        assertEquals(new Hilbert().getPathSize(),1024);
    }

}
