package de.xonibo.stickes.assemble;

import static org.testng.Assert.assertEquals;

public class CCurveTest {

    @org.testng.annotations.Test
    public void countElements() {
        assertEquals(new CCurve().getPathSize(),257);
    }

}
