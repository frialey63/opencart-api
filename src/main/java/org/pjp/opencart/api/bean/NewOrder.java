package org.pjp.opencart.api.bean;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Bean to represent a new order.
 * @author developer
 *
 */
public class NewOrder extends Result {

    @JsonProperty("order_id")
    private int orderId;

    /**
     * Not the rock group.
     */
    public NewOrder() {
        super();
    }

    /**
     * @param orderId ID for the new order
     */
    public NewOrder(int orderId) {
        super();
        success = "Success";
        this.orderId = orderId;
    }

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    @Override
    public String toString() {
        return "NewOrder [orderId=" + orderId + "]";
    }

}
