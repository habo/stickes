package de.xonibo.stickes.stiches;

import java.awt.Font;
import java.awt.geom.Line2D;
import static org.testng.Assert.*;
import org.testng.annotations.Test;

public class TextTest {
    
    @Test(expectedExceptions = IllegalArgumentException.class)
    public void toStichDataNull(){
        assertNotNull(new Text(null,null, null).toStichData());
    }
    @Test
    public void toStichDataLine(){
        Font f = new Font("Verdana", Font.PLAIN, 10);
        assertNotNull(new Text(new Line2D.Float(),f, null).toStichData());
    }
    
    
}
