package de.xonibo.stickes.gui.ls;

public class LSEntry {

    final private String name;
    final private int iterations;
    final private int angle;
    final private int stepwidth;
    final private String axiom;
    final private String[] rules;

    public LSEntry(String name, int iterations, int angle, int stepwidth, String axiom, String... rules) {
        this.name = name;
        this.iterations = iterations;
        this.angle = angle;
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
