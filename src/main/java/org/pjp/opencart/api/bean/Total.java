package org.pjp.opencart.api.bean;

/**
 * Bean to represent a total.
 * @author developer
 *
 */
public class Total {

    private String title;

    private String text;

    /**
     * Default constructor required by Jackson.
     */
    public Total() {
        super();
    }

    /**
     * @param title Title
     * @param text Text
     */
    public Total(String title, String text) {
        super();
        this.title = title;
        this.text = text;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    @Override
    public String toString() {
        return "Total [title=" + title + ", text=" + text + "]";
    }

}
