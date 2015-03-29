package de.xonibo.stickes.format;

import de.xonibo.stickes.StichData;
import de.xonibo.stickes.gui.Painter;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import javax.imageio.ImageIO;

public abstract class Image implements StichFileSave {

    private final String formatname;
    private Color backgroundColor = Color.LIGHT_GRAY;
    private Color textColor = Color.BLACK;
    private boolean showInfo = false;

    public void setShowInfo(boolean showInfo) {
        this.showInfo = showInfo;
    }

    public boolean isShowInfo() {
        return showInfo;
    }

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
        if (isShowInfo()) {
            g.setFont(new Font("Verdana", Font.BOLD, 10));
            g.setColor(textColor);
            g.drawString(data.getInfo(), 0, 10);
        }

        Painter pain = new Painter();
        pain.drawStichData(g, norm, 0, 0, 5, norm.size(), 1);
        ImageIO.write(im, formatname.toUpperCase(), out);
    }

    public void save(File f, StichData data) throws Exception {
        save(new FileOutputStream(f), data);
    }

    @Override
    public String getExtention() {
        return "." + formatname.toLowerCase();
    }

}
