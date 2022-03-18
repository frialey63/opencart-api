package org.pjp.opencart.api.bean;

import java.util.Arrays;

/**
 * Bean to represent the shopping cart.
 * @author developer
 *
 */
public class ShoppingCart extends Result {

    private Product[] products;

    private Voucher[] vouchers;

    private Total[] totals;

    /**
     * Default constructor required by Jackson.
     */
    public ShoppingCart() {
        super();
        success = "Success";
    }

    public Product[] getProducts() {
        return products;
    }

    public void setProducts(Product[] products) {
        this.products = products;
    }

    public Voucher[] getVouchers() {
        return vouchers;
    }

    public void setVouchers(Voucher[] vouchers) {
        this.vouchers = vouchers;
    }

    public Total[] getTotals() {
        return totals;
    }

    public void setTotals(Total[] totals) {
        this.totals = totals;
    }

    @Override
    public String toString() {
        return "Cart [products=" + Arrays.toString(products) + ", vouchers=" + Arrays.toString(vouchers) + ", totals="
                + Arrays.toString(totals) + "]";
    }


}
