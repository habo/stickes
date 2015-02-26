package de.xonibo.stickes.assemble;

import static org.testng.Assert.assertEquals;

public class KnaeuelTest {

    @org.testng.annotations.Test
    public void countElements() {
        assertEquals(new Knaeuel().getPathSize(), 401);
    }

}
