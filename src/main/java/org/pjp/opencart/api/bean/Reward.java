package org.pjp.opencart.api.bean;

public class Reward extends Result {

    private int maximum;

    private String points;

    public Reward() {
        super();
    }

    public Reward(int maximum, String points) {
		super();
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
