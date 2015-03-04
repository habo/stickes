package de.xonibo.stickes.format;

import de.xonibo.stickes.StichData;
import de.xonibo.stickes.TestingData;
import java.io.File;
import java.util.List;
import java.util.logging.Logger;
import static org.testng.Assert.*;
import org.testng.annotations.Test;

public class PESTest {

    final private String resourcePath = "src/test/resources/";

    final private List<TestingData> data = TestingData.load(resourcePath + "pes-test");
    final private PES t = new PES();

    @Test
    public void extention() {
        assertEquals(t.getExtention(), ".pes");
    }

    @Test
    public void testingDataAvailable() {
        Logger.getGlobal().warning("pes-test is empty");
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
                fail(String.format("fileextention %s vs %s", filename,t.getExtention()));
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

}
