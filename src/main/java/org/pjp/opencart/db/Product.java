package org.pjp.opencart.db;

public class Product {

    private final int id;

    private final String name;

    public Product(int id, String name) {
        super();
        this.name = name;
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return "Product [id=" + id + ", name=" + name + "]";
    }

}