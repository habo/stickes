package de.xonibo.stickes.assemble;

import static org.testng.Assert.assertEquals;

public class DragonCurveTest {
    
      @org.testng.annotations.Test
    public void countElements() {
        assertEquals(new DragonCurve().getPathSize(),131073);
    }
    
}
