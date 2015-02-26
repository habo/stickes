package de.xonibo.stickes.assemble;

import static org.testng.Assert.*;
import org.testng.annotations.Test;

public class ArrowTipTest {
    
    @Test
    public void countElements() {
        assertEquals(new ArrowTip().getPathSize(),244);
    }
    
}
