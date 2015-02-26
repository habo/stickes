package de.xonibo.stickes;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

public class TestingData {

    private final String filename;
    private final int stiches;
    private final int colors;
    private final int jumps;

    // TODO viel zu viel code, ist kein wirklicher test
    static public List<TestingData> load(String testfilename) {
        File file = new File(testfilename);
        Scanner scanner = null;
        try {
            scanner = new Scanner(file);
        } catch (FileNotFoundException ex) {
            Logger.getLogger(TestingData.class.getName()).log(Level.SEVERE, null, ex);
        }
        if (scanner == null) {
            return null;
        }
        ArrayList<TestingData> list = new ArrayList<>();
        while (scanner.hasNext()) {
            String line = scanner.nextLine().replaceAll("#.*", "").trim();
            if (line.isEmpty()) {
                continue;
            }
            String[] split = line.split(",");
            try {
                int mystiches = Integer.parseInt(split[1].trim());
                int mycolors = Integer.parseInt(split[2].trim());
                int myjumps = Integer.parseInt(split[3].trim());
                list.add(new TestingData(split[0].trim(), mystiches, mycolors, myjumps));
            } catch (ArrayIndexOutOfBoundsException | NumberFormatException ae) {
            Logger.getLogger(TestingData.class.getName()).log(Level.SEVERE, null, ae);
            }
        }
        return list;
    }

    public TestingData(String Filename, int stiches, int Colors, int jumps) {
        this.filename = Filename;
        this.stiches = stiches;
        this.colors = Colors;
        this.jumps = jumps;
    }

    public int getColors() {
        return colors;
    }

    public String getFilename() {
        return filename;
    }

    public int getStiches() {
        return stiches;
    }

    public int getJumps() {
        return jumps;
    }

}
