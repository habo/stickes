package de.xonibo.stickes.assemble;

import static org.testng.AssertJUnit.*;
import org.testng.annotations.Test;

public class TurtleTest {

    @Test
    public void testSetAngle() {
        Turtle instance = new Turtle();
        instance.setAngle(10);
        instance.turn(30);
        assertEquals(instance.getAngle(), 40.0);
        instance.turn(-50);
        assertEquals(instance.getAngle(), -10.0);
    }

    @Test
    public void testGetPath() {
        Turtle instance = new Turtle();
        assertEquals(instance.getPathSize(), 1);
        instance.move(1);
        assertEquals(instance.getPathSize(), 2);
        instance.move(1);
        assertEquals(instance.getPathSize(), 3);
        instance.move(1);
        assertEquals(instance.getPathSize(), 4);
    }
}
