package de.xonibo.stickes.format;

import de.xonibo.stickes.StichData;
import de.xonibo.stickes.awt.Painter;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.OutputStream;
import javax.imageio.ImageIO;

public abstract class Image implements StichFileSave {

    private final String formatname;
    private Color backgroundColor = Color.LIGHT_GRAY;

    
    public void setBackgroundColor(Color backgroundColor) {
        this.backgroundColor = backgroundColor;
    }

    public Image(String format) {
        formatname = format;
    }

    @Override
    public void save(OutputStream out, StichData data) throws Exception {
        StichData norm = data.normalize();
        BufferedImage im = new BufferedImage(norm.getMaxCornerX(), norm.getMaxCornerY(), BufferedImage.TYPE_INT_RGB);
        Graphics g = im.getGraphics();
        g.setColor(backgroundColor);
        g.fillRect(0, 0, im.getWidth(), im.getHeight());
        Painter pain = new Painter();
        pain.drawStichData(g, norm, 0, 0, 5, norm.size(), 1);
        ImageIO.write(im, formatname.toUpperCase(), out);
    }

    @Override
    public String getExtention() {
        return "." + formatname.toLowerCase();
    }

}
