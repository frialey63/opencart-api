package org.pjp.opencart.api.bean;

public class Total {

    private String title;

    private String text;

    public Total() {
        super();
    }

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
