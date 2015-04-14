package de.xonibo.stickes.gui.ls;

import java.io.Serializable;

public class LSEntry implements Serializable {

    final private String name;
    final private int iterations;
    final private int startangle;
    final private int angle;
    final private int stepwidth;
    final private String axiom;
    final private String[] rules;

    public LSEntry(String name, int startangle, int iterations, int angle, int stepwidth, String axiom, String... rules) {
        this.name = name;
        this.iterations = iterations;
        this.angle = angle;
        this.startangle = startangle;
        this.stepwidth = stepwidth;
        this.axiom = axiom;
        this.rules = rules;
    }

    public String getName() {
        return name;
    }

    public int getAngle() {
        return angle;
    }

    public String getAxiom() {
        return axiom;
    }

    public int getStartangle() {
        return startangle;
    }

    public int getIterations() {
        return iterations;
    }

    public int getStepwidth() {
        return stepwidth;
    }

    public String[] getRules() {
        return rules;
    }

    @Override
    public String toString() {
        return name;
    }

}
