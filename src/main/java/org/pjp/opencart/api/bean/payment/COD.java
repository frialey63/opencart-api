package org.pjp.opencart.api.bean.payment;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Bean to represent the COD payment.
 * @author developer
 *
 */
public class COD {

    private String code;

    private String title;

    private String terms;

    @JsonProperty("sort_order")
    private String sortOrder;

    /**
     * Default constructor required by Jackson.
     */
   public COD() {
        super();
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTerms() {
        return terms;
    }

    public void setTerms(String terms) {
        this.terms = terms;
    }

    public String getSortOrder() {
        return sortOrder;
    }

    public void setSortOrder(String sortOrder) {
        this.sortOrder = sortOrder;
    }

    @Override
    public String toString() {
        return "COD [code=" + code + ", title=" + title + ", terms=" + terms + ", sortOrder=" + sortOrder + "]";
    }

}