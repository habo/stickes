package de.xonibo.stickes.format;

import de.xonibo.stickes.Stich;
import de.xonibo.stickes.StichData;
import de.xonibo.stickes.StichType;
import de.xonibo.stickes.TestingData;
import de.xonibo.stickes.stiches.Plain;
import java.awt.Color;
import java.awt.Shape;
import java.awt.geom.GeneralPath;
import java.io.File;
import java.io.IOException;
import java.io.PipedInputStream;
import java.io.PipedOutputStream;
import java.util.List;
import java.util.logging.Logger;
import static org.testng.Assert.*;
import org.testng.annotations.Test;

public class TajimaTest {

    final private Stich initStich = new Stich(0, 0);
    final private String resourcePath = "src/test/resources/";

    final private List<TestingData> data = TestingData.load(resourcePath + "dst-test");
    final private Tajima t = new Tajima();

    @Test
    public void extention() {
        assertEquals(t.getExtention(), ".dst");
    }

    @Test
    public void testingDataAvailable() {
        Logger.getGlobal().warning("dst-test is empty");
    }

    @Test(dependsOnMethods = "testingDataAvailable")
    public void testMultiFilesLoadNrStiches() throws Exception {
        for (TestingData testData : data) {

            final String filename = testData.getFilename();
            if (filename.endsWith(t.getExtention())) {
                StichData d = t.load(new File(resourcePath, filename));
                assertEquals(d.size(), testData.getStiches(), "nr of stiches: " + filename);
            } else {
                assertFalse(true, "fileextention: " + filename);
            }
        }
    }

    @Test(dependsOnMethods = "testingDataAvailable")
    public void testMultiFilesLoadColors() throws Exception {
        for (TestingData testData : data) {

            final String filename = testData.getFilename();
            if (filename.endsWith(t.getExtention())) {
                StichData d = t.load(new File(resourcePath, filename));
                assertEquals(d.getColors().size(), testData.getColors(), "nr of colors: " + filename);
            } else {
                fail(String.format("fileextention %s vs %s", filename, t.getExtention()));
            }
        }
    }

    @Test(dependsOnMethods = "testingDataAvailable")
    public void testMultiFilesLoadJumps() throws Exception {
        for (TestingData testData : data) {

            final String filename = testData.getFilename();
            if (filename.endsWith(t.getExtention())) {
                StichData d = t.load(new File(resourcePath, filename));
                assertEquals(d.getJumps(), testData.getJumps(), "jumps: " + filename);
            } else {
                assertFalse(true, "fileextention: " + filename);
            }
        }
    }

    @Test
    public void testLoadAndSaveBasics() throws IOException {

        StichData sdout = new StichData();
        StichData sdin = assembledSavedAndLoadedStichData(sdout);
        assertEquals(sdin.size(), sdout.size());
        assertEquals(sdin.getJumps(), 2);
        assertEquals(sdin.getColors().size(), 3);

    }

    @Test
    public void testLoadAndSaveTypes() throws IOException {

        StichData sdout = new StichData();
        StichData sdin = assembledSavedAndLoadedStichData(sdout);

        assertTrue(sdin.get(2).isColorChange());
        assertTrue(sdin.get(3).isJump());
        assertFalse(sdin.get(4).isJump());
        assertTrue(sdin.get(7).isJump());
    }

    @Test
    public void testLoadAndSavePositions() throws IOException {

        StichData sdout = new StichData();
        StichData sdin = assembledSavedAndLoadedStichData(sdout);

        assertEquals(sdin.get(3).getX(), 1);
        assertEquals(sdin.get(3).getY(), 3);
        assertEquals(sdin.get(4).getX(), 2);
        assertEquals(sdin.get(4).getY(), 5);

    }

    @Test
    public void testLoadAndSaveFarPositions() throws IOException {

        StichData sdout = new StichData();
        StichData sdin = assembledSavedAndLoadedStichData(sdout);

        assertNotEquals(sdin.get(5).getX(), 203);
        assertNotEquals(sdin.get(5).getY(), 207);
        assertNotEquals(sdin.get(6).getX(), 4);
        assertNotEquals(sdin.get(6).getY(), 8);

    }

    private StichData assembledSavedAndLoadedStichData(StichData sdout) throws IOException {
        sdout.add(new Stich(Color.red));
        sdout.add(new Stich(Color.blue));
        sdout.add(new Stich(Color.green));
        sdout.add(new Stich(1, 3, true));
        sdout.add(new Stich(2, 5));
        sdout.add(new Stich(203, 207));
        sdout.add(new Stich(4, 8));
        sdout.add(new Stich(5, 1, true));
        sdout.add(new Stich(StichType.EOF));
        PipedInputStream in = new PipedInputStream(100000);
        PipedOutputStream out = new PipedOutputStream(in);
        t.save(out, sdout);
        StichData sdin = t.load(in);
        return sdin;
    }

    @Test
    public void encodeDecodeX() {

        for (int i = 0; i < 100; i++) {
            byte[] b = t.encode(new Stich(i, 0), initStich);
            Stich decoded = t.decode(b, initStich);
            assertEquals(decoded.getX(), i, "given " + i);
        }
    }

    @Test
    public void encodeDecodeY() {

        for (int i = 0; i < 100; i++) {
            byte[] b = t.encode(new Stich(0, i), initStich);
            Stich decoded = t.decode(b, initStich);
            assertEquals(decoded.getY(), i, "given " + i);
        }
    }

    @Test
    public void encodeDecodeColorChange() {

        byte[] b = t.encode(new Stich(Color.yellow), initStich);
        Stich decoded = t.decode(b, initStich);
        assertTrue(decoded.isColorChange());
    }

    @Test
    public void encodeDecodeEOF() {

        byte[] b = t.encode(new Stich(StichType.EOF), initStich);
        Stich decoded = t.decode(b, initStich);
        assertTrue(decoded.isEOF());
    }

    @Test
    public void intermediateStiches() throws IOException {
        Shape s = getLineShape(0, 0);
        StichData sd = new Plain(s).toStichData();
        PipedInputStream in = new PipedInputStream(100000);
        PipedOutputStream out = new PipedOutputStream(in);
        t.save(out, sd);
        assertTrue(true);
    }

    @Test
    public void checkFirstStich() {
        StichData sd = new StichData();
        sd.add(new Stich(100, 300));
        t.checkFirstStich(sd);
        Stich first = sd.get(0);
        assertEquals(first.getX(), 50);
        assertEquals(first.getY(), 150);
    }

    @Test
    public void insertCenterStich() {
        {
            StichData sd = new StichData();
            // startstich irgendwo
            sd.add(new Stich(12, 32));
            // irgendwo noch ein anderer stich, aber innerhalb der maximalwerte
            sd.add(new Stich(10, 14));
            // sollte einen stich am anfang einfügen, der mittig liegt
            t.insertCenterStich(sd);
            Stich first = sd.get(0);

            // size 3, da kein stich weiterer stich hinzugefügt wurde
            assertEquals(sd.size(), 3);
            assertEquals(first.getX(), 6);
            assertEquals(first.getY(), 16);
        }
        {
            StichData sd = new StichData();
            // startstich irgendwo
            sd.add(new Stich(120, 320));
            // irgendwo noch ein anderer stich, aber innerhalb der maximalwerte
            sd.add(new Stich(101, 304));
            // sollte einen stich am anfang einfügen, der mittig liegt
            t.insertCenterStich(sd);
            Stich first = sd.get(0);

            // size 4, da ein intermediate benötigt wurde
            assertEquals(sd.size(), 4);
            assertEquals(first.getX(), 60);
            assertEquals(first.getY(), 160);
        }
    }

    @Test
    public void isOverRange() {
        final Stich first = new Stich(0, 0);
        assertFalse(t.isOverRange(first, new Stich(0, 0)));
        assertFalse(t.isOverRange(first, new Stich(40, 30)));
        assertFalse(t.isOverRange(first, new Stich(120, 120)));
        assertTrue(t.isOverRange(first, new Stich(150, 30)));
        assertTrue(t.isOverRange(first, new Stich(40, 300)));

        assertFalse(t.isOverRange(first, new Stich(-40, -70)));
        assertTrue(t.isOverRange(first, new Stich(-40, -300)));
    }

    @Test
    public void insertIntermediateStiches() {
        final Stich first = new Stich(10, 10);
        final Stich next = new Stich(400, 500);
        StichData sd = t.insertIntermediateStiches(first, next);
        assertEquals(sd.size(), 7);
    }

    private Shape getLineShape(int x, int y) {
        GeneralPath path = new GeneralPath();
        path.moveTo(x, y);
        for (int i = 0; i < 300; i += 25) {
            path.lineTo(x, y);
            path.lineTo(x + i, y);
            y = y + 10;
        }
        return path;
    }

}
