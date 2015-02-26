package de.xonibo.stickes.assemble;

import static org.testng.Assert.assertEquals;

public class ZackenTest {

    @org.testng.annotations.Test
    public void countElements() {
        assertEquals(new Zacken().getPathSize(),217);
    }

}
