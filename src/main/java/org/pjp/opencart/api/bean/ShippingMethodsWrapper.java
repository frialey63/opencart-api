package org.pjp.opencart.api.bean;

import org.pjp.opencart.api.bean.shipping.Flat;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ShippingMethodsWrapper extends Result {

    public static class ShippingMethods {

        private Flat flat;

        public ShippingMethods() {
            super();
        }

        public Flat getFlat() {
            return flat;
        }

        public void setFlat(Flat flat) {
            this.flat = flat;
        }

        @Override
        public String toString() {
            return "ShippingMethods [flat=" + flat + "]";
        }
    }

    @JsonProperty("shipping_methods")
    private ShippingMethods shippingMethods;

    public ShippingMethodsWrapper() {
        super();
    }

    public ShippingMethods getShippingMethods() {
        return shippingMethods;
    }

    public void setMethods(ShippingMethods shippingMethods) {
        this.shippingMethods = shippingMethods;
    }

    @Override
    public String toString() {
        return "ShippingMethods [shippingMethods=" + shippingMethods + "]";
    }

}
