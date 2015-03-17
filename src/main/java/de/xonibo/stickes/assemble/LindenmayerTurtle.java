package de.xonibo.stickes.assemble;

import java.awt.Point;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

public class LindenmayerTurtle extends Turtle {

    private double lsangle = 90;
    private int lsstep = 10;
    final private Map<Character, String> rulemap = new HashMap<>();

    private boolean reversed = false;
    private String lsrule;
    final private Stack<Point> stackPosition = new Stack();
    final private Stack<Double> stackAngle = new Stack();
    private int lsiterations;

    public LindenmayerTurtle() {
        super(0, 0, -90);
        rulemap.put('F', "F+F-F-F+F");
        iterate("-F", 4);
    }

    public LindenmayerTurtle(int iterations, double angle, int stepwidth, String initrule, String... newinstructions) {
        super(0, 0, -90);
        for (String cmd : newinstructions) {
            String[] split = cmd.split("=");
            rulemap.put(split[0].trim().charAt(0), split.length == 1 ? "" : split[1]);
        }
        lsangle = angle;
        lsstep = stepwidth;
        lsrule = initrule;
        lsiterations = iterations;
        iterate(initrule, iterations);
    }

    public String getInfo() {
        StringBuilder sb = new StringBuilder();
        sb.append("init: ").append(lsrule).append("\n");
        for (Character key : rulemap.keySet()) {
            sb.append("rule: ").append(key).append("->").append(rulemap.get(key)).append("\n");
        }
        sb.append("angle: ").append(angle).append("\n");
        sb.append("step: ").append(lsstep).append("\n");
        sb.append("iterations: ").append(lsiterations).append("\n");
        return sb.toString();
    }

    private void iterate(String rule, int depth) {
        if (depth == 0) {
            return;
        }
        for (int i = 0; i < rule.length(); i++) {
            char c = rule.charAt(i);

            if (rulemap.containsKey(c)) {
                iterate(rulemap.get(c), depth - 1);
            }

            switch (c) { // these chars are also known as constants
                case 'C':
                    i++;
                    // TODO set color
                    break;
                case ';':
                    return;
                case 'f':
                    jump(lsstep);
                    break;
                case 'A':
                case 'B':
                case 'F':
                case 'G':
                    move(lsstep);
                    break;
                case '1':
                    move(1);
                    break;
                case '2':
                    move(2);
                    break;
                case '3':
                    move(3);
                    break;
                case '4':
                    move(4);
                    break;
                case '5':
                    move(5);
                    break;
                case '6':
                    move(6);
                    break;
                case '7':
                    move(7);
                    break;
                case '8':
                    move(8);
                    break;
                case '9':
                    move(9);
                    break;
                case '!':
                    reversed = !reversed;
                    break;
                case '+':
                    turn(reversed ? -lsangle : lsangle);
                    break;
                case '-':
                    turn(reversed ? lsangle : -lsangle);
                    break;
                case '|':
                    turn(180);
                    break;
                case '[':
                    stackPosition.push(getPoint());
                    stackAngle.push(getAngle());
                    break;
                case ']':
                    setAngle(stackAngle.pop());
                    jump(stackPosition.pop());
                    break;
                case '>':
                    // next color
                    break;
                case '<':
                    // previous color
                    break;
            }
        }
    }
}
