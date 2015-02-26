package de.xonibo.stickes.assemble;

import static org.testng.AssertJUnit.*;
import org.testng.annotations.Test;

public class PythagorasTreeTest {

    @Test
    public void countElements() {
        assertEquals(new PythagorasTree().getPathSize(),1787);
    }

}
