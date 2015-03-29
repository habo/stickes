package de.xonibo.stickes.gui;

import java.util.logging.Level;
import java.util.logging.Logger;

public class Play implements Runnable {

    static private boolean running = false;

    public static boolean isRunning() {
        return running;
    }

    public static synchronized void call(Visual v) {
        if (!isRunning()) {
            new Thread(new Play(v)).start();
        } else {
            running = false;
        }
    }
    private final Visual visual;

    private Play(Visual v) {
        this.visual = v;
        running = true;
    }

    @Override
    public void run() {
        doButton();
        if (visual.stichesCurrent == visual.stichesMax) {
            visual.stichesCurrent = 0;
        }
        for (int i = visual.stichesCurrent; i < visual.stichesMax; i++) {
            visual.playslider.setValue(i);
            visual.stichesCurrent = i;
            if (!running) {
                doButton();
                return;
            }
            try {
                Thread.sleep(50);
            } catch (InterruptedException ex) {
                running = false;
                Logger.getLogger(Play.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        running = false;
        doButton();
    }

    private void doButton() {
        visual.playbutton.setText(isRunning() ? "Stop" : "Play");
    }

}
