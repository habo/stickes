package de.xonibo.stickes.assemble;

import java.util.HashMap;
import java.util.Map;

public class LindenmayerTurtle extends Turtle {

    private double lsangle = 60;
    private int lsstep = 10;
    final private Map<Character, String> map = new HashMap<>();

    public LindenmayerTurtle() {
        map.put('F', "F+F-F-F+F");
        iterate("-F", 4);
    }

    public LindenmayerTurtle(int iterations, double angle, int stepwidth, String initrule, String... newinstructions) {
        System.out.println("init: " + initrule);
        for (String cmd : newinstructions) {
            String[] split = cmd.split("=");
            map.put(split[0].trim().charAt(0), split[1]);
            System.out.println("rule: " + cmd);
        }
        System.out.println("angle: " + angle);
        System.out.println("step: " + stepwidth);
        System.out.println("iterations: " + iterations);
        //System.out.println("constants: ");
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
            if (c == 'F') {
                move(lsstep);
            } else if (c == '+') {
                turn(lsangle);
            } else if (c == '-') {
                turn(-lsangle);
            } else if (c == '|') {
                turn(lsangle);
                turn(lsangle);
            }
            // TODO heap set and get
//            else if (c == '[') {
//                int markPathSize = getPathSize();
//                double markAngle = getAngle();
//            } 
//            else if (c == ']') {
//            }
        }
    }
}
