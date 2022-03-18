package org.pjp.opencart.api.bean.shipping;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Bean to represent the shipment quote.
 * @author developer
 *
 */
public class Quote {

    public class Flat {

        private String code;

        private String title;

        private float cost;

        @JsonProperty("tax_class_id")
        private int taxClassId;

        private String text;

        /**
         * Default constructor required by Jackson.
         */
        public Flat() {
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

        public float getCost() {
            return cost;
        }

        public void setCost(float cost) {
            this.cost = cost;
        }

        public int getTaxClassId() {
            return taxClassId;
        }

        public void setTaxClassId(int taxClassId) {
            this.taxClassId = taxClassId;
        }

        public String getText() {
            return text;
        }

        public void setText(String text) {
            this.text = text;
        }

        @Override
        public String toString() {
            return "Flat [code=" + code + ", title=" + title + ", cost=" + cost + ", taxClassId=" + taxClassId
                    + ", text=" + text + "]";
        }

    }

    private Quote.Flat flat;

    /**
     * Default constructor required by Jackson.
     */
    public Quote() {
        super();
    }

    public Quote.Flat getFlat() {
        return flat;
    }

    public void setFlat(Quote.Flat flat) {
        this.flat = flat;
    }

    @Override
    public String toString() {
        return "Quote [flat=" + flat + "]";
    }

}
