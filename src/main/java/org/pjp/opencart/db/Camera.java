package org.pjp.opencart.db;

import java.awt.Color;
import java.util.HashMap;
import java.util.Map;

public class Camera extends Product implements Optionality {

    private final Color colour;

    public Camera(int id, String name, Color colour) {
        super(id, name);
        this.colour = colour;
    }

    @Override
    public Map<Integer, Integer> getOptions() {
        Map<Integer, Integer> options = new HashMap<>();

        // CHECKSTYLE:OFF magic numbers

        if (Color.RED.equals(colour)) {
            options.put(226, 15);
        } else if (Color.BLUE.equals(colour)) {
            options.put(226, 16);
        }

        // CHECKSTYLE:ON

        return options;
    }

}
