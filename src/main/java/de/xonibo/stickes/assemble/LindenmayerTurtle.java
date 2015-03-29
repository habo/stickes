package de.xonibo.stickes.assemble;

import java.awt.Point;
import java.text.ParseException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Stack;

public class LindenmayerTurtle extends Turtle {

    private double lsangle = 90;
    private int lsstep = 10;
    private Map<Character, String> rulemap = new HashMap<>();

    private boolean reversed = false;
    private String axiom;
    final private Stack<Point> stackPosition = new Stack();
    final private Stack<Double> stackAngle = new Stack();
    private int lsiterations;

    public LindenmayerTurtle() {
        // koch kurve by default
        super(0, 0, -90);
        rulemap.put('F', "F+F-F-F+F");
        iterate("-F", 4);
    }

    public LindenmayerTurtle(int iterations, double angle, int stepwidth, String axiom, List<String> newinstructions) {
        super(0, 0, -90);
        rulemap = createMap(newinstructions);
        lsangle = angle;
        lsstep = stepwidth;
        this.axiom = axiom;
        lsiterations = iterations;
        iterate(axiom, iterations);
    }

    public LindenmayerTurtle(int iterations, double angle, int stepwidth, String axiom, String... newinstructions) {
        super(0, 0, -90);
        rulemap = createMap(Arrays.asList(newinstructions));
        lsangle = angle;
        lsstep = stepwidth;
        this.axiom = axiom;
        lsiterations = iterations;
        iterate(axiom, iterations);
    }

    public final Map<Character, String> createMap(List<String> newinstructions) {
        final Map<Character, String> map = new HashMap<>();
        for (String cmd : newinstructions) {
            String[] split = cmd.trim().split("=");
            if (split.length == 2) {
                map.put(split[0].trim().charAt(0), split[1]);
            }
        }
        return map;
    }

    public String getInfo() {
        StringBuilder sb = new StringBuilder();
        sb.append("init: ").append(axiom).append("\n");
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
                case '2':
                case '3':
                case '4':
                case '5':
                case '6':
                case '7':
                case '8':
                case '9':
                    try {
                        move(Integer.parseInt("" + c));
                    } catch (NumberFormatException e) {
                    }
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
            if (rulemap.containsKey(c)) {
                iterate(rulemap.get(c), depth - 1);
            }

        }
    }
}
