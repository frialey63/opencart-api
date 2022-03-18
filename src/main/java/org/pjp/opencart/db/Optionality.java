package org.pjp.opencart.db;

import java.util.Map;

/**
 * Interface for products which have options.
 * @author developer
 *
 */
public interface Optionality {

    /**
     * @return The options as a map of option code to option value
     */
    Map<Integer, Integer> getOptions();

}
