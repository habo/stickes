package de.xonibo.stickes.assemble;

import static org.testng.Assert.assertEquals;

public class BinTreeTest {

    @org.testng.annotations.Test
    public void countElements() {
        assertEquals( new BinTree().getPathSize(),511);
    }

}
