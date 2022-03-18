package org.pjp.opencart.api.bean;

import java.util.Arrays;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Product {

    @JsonProperty("cart_id")
    private String cartId;

    @JsonProperty("product_id")
    private String productId;

    private String name;

    private String model;

    private Option[] option;

    private int quantity;

    private boolean stock;

    private int shipping;

    private String price;

    private String total;

    private int reward;

    public Product() {
        super();
    }

    // CHECKSTYLE:OFF number of parameters
    public Product(String cartId, String productId, String name, String model, Option[] option, int quantity, boolean stock, int shipping, String price, String total, int reward) {
    // CHECKSTYLE:ON
        super();
        this.cartId = cartId;
        this.productId = productId;
        this.name = name;
        this.model = model;
        this.option = option;
        this.quantity = quantity;
        this.stock = stock;
        this.shipping = shipping;
        this.price = price;
        this.total = total;
        this.reward = reward;
    }

    public String getCartId() {
        return cartId;
    }

    public void setCartId(String cartId) {
        this.cartId = cartId;
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public Option[] getOption() {
        return option;
    }

    public void setOption(Option[] option) {
        this.option = option;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public boolean isStock() {
        return stock;
    }

    public void setStock(boolean stock) {
        this.stock = stock;
    }

    public int getShipping() {
        return shipping;
    }

    public void setShipping(int shipping) {
        this.shipping = shipping;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getTotal() {
        return total;
    }

    public void setTotal(String total) {
        this.total = total;
    }

    public int getReward() {
        return reward;
    }

    public void setReward(int reward) {
        this.reward = reward;
    }

    @Override
    public String toString() {
        return "Product [cartId=" + cartId + ", productId=" + productId + ", name=" + name + ", model=" + model
                + ", option=" + Arrays.toString(option) + ", quantity=" + quantity + ", stock=" + stock + ", shipping=" + shipping
                + ", price=" + price + ", total=" + total + ", reward=" + reward + "]";
    }

}
