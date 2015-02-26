package de.xonibo.stickes.assemble;

import static org.testng.Assert.assertEquals;

public class KochCurveTest {

    @org.testng.annotations.Test
    public void countElements() {
        assertEquals(new KochCurve().getPathSize(), 65);
    }

}
