package org.pjp.opencart.api.bean;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Bean to represent a product option.
 * @author developer
 *
 */
public class Option {

    @JsonProperty("product_option_id")
    private String productOptionId;

    @JsonProperty("product_option_value_id")
    private String productOptionValueId;

    private String name;

    private String value;

    private String type;

    /**
     * Default constructor required by Jackson.
     */
    public Option() {
        super();
    }

    public String getProductOptionId() {
        return productOptionId;
    }

    public void setProductOptionId(String productOptionId) {
        this.productOptionId = productOptionId;
    }

    public String getProductOptionValueId() {
        return productOptionValueId;
    }

    public void setProductOptionValueId(String productOptionValueId) {
        this.productOptionValueId = productOptionValueId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "Option [productOptionId=" + productOptionId + ", productOptionValueId=" + productOptionValueId
                + ", name=" + name + ", value=" + value + ", type=" + type + "]";
    }

}
