package de.xonibo.stickes.assemble;

import static org.testng.Assert.assertEquals;

public class SiebenkreisTest {

    @org.testng.annotations.Test
    public void countElements() {
        assertEquals(new Siebenkreis().getPathSize(), 2521);
    }

}
