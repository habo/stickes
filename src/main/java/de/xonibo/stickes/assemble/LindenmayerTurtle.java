package de.xonibo.stickes.assemble;

import java.awt.Point;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

public class LindenmayerTurtle extends Turtle {

    private double lsangle = 90;
    private int lsstep = 10;
    final private Map<Character, String> map = new HashMap<>();

    private boolean reversed = false;
    Stack<Point> stackPosition = new Stack();
    Stack<Double> stackAngle = new Stack();

    public LindenmayerTurtle() {
        super(0, 0, -90);
        map.put('F', "F+F-F-F+F");
        iterate("-F", 4);
    }

    public LindenmayerTurtle(int iterations, double angle, int stepwidth, String initrule, String... newinstructions) {
        super(0, 0, -90);
        System.out.println("init: " + initrule);
        for (String cmd : newinstructions) {
            String[] split = cmd.split("=");
            map.put(split[0].trim().charAt(0), split.length==1?"":split[1]);
            System.out.println("rule: " + cmd);
        }
        System.out.println("angle: " + angle);
        System.out.println("step: " + stepwidth);
        System.out.println("iterations: " + iterations);
        System.out.println("constants: "); // TODO
        System.out.println("");
        lsangle = angle;
        lsstep = stepwidth;
        iterate(initrule, iterations);
    }

    private void iterate(String rule, int depth) {
        if (depth == 0) {
            return;
        }
        for (int i = 0; i < rule.length(); i++) {
            char c = rule.charAt(i);

            if (map.containsKey(c)) {
                iterate(map.get(c), depth - 1);
            }
            
            switch (c) {
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
