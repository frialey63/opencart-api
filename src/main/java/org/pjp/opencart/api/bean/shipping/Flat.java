package org.pjp.opencart.api.bean.shipping;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Flat {

    private String title;

    private Quote quote;

    @JsonProperty("sort_order")
    private int sortOrder;

    private boolean error;

    public Flat() {
        super();
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Quote getQuote() {
        return quote;
    }

    public void setQuote(Quote quote) {
        this.quote = quote;
    }

    public int getSortOrder() {
        return sortOrder;
    }

    public void setSortOrder(int sortOrder) {
        this.sortOrder = sortOrder;
    }

    public boolean getError() {
        return error;
    }

    public void setError(boolean error) {
        this.error = error;
    }

    @Override
    public String toString() {
        return "Flat [title=" + title + ", quote=" + quote + ", sortOrder=" + sortOrder + ", error=" + error + "]";
    }

}