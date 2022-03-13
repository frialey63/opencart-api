package org.pjp.opencart.api.bean;

import com.fasterxml.jackson.annotation.JsonProperty;

public class NewOrder extends Result {

    @JsonProperty("order_id")
    private int orderId;

    public NewOrder() {
        super();
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
