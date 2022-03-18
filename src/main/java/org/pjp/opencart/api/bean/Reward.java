package org.pjp.opencart.api.bean;

/**
 * Bean to represent a reward.
 * @author developer
 *
 */
public class Reward extends Result {

    private int maximum;

    private String points;

    /**
     * Default constructor required by Jackson.
     */
    public Reward() {
        super();
    }

    /**
     * @param maximum Maximum
     * @param points Points
     */
    public Reward(int maximum, String points) {
        super();
        success = "Success";
        this.maximum = maximum;
        this.points = points;
    }

    public int getMaximum() {
        return maximum;
    }

    public void setMaximum(int maximum) {
        this.maximum = maximum;
    }

    public String getPoints() {
        return points;
    }

    public void setPoints(String points) {
        this.points = points;
    }

    @Override
    public String toString() {
        return "Reward [maximum=" + maximum + ", points=" + points + "]";
    }

}
