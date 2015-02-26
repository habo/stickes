package de.xonibo.stickes.assemble;

import static org.testng.Assert.*;
import org.testng.annotations.Test;

public class KochSnowFlakeTest {

    @org.testng.annotations.Test
    public void countElements() {
        assertEquals(new KochSnowFlake().getPathSize(), 193);
    }

}
