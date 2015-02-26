package de.xonibo.stickes.assemble;

import static org.testng.Assert.assertEquals;

public class SternvieleckTest {

    @org.testng.annotations.Test
    public void countElements() {
        assertEquals(new Sternvieleck().getPathSize(),46);
    }

}
