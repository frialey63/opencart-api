package org.pjp.opencart.db;

import java.awt.Color;
import java.util.HashMap;
import java.util.Map;

public final class ProductDatabase {

    public static ProductDatabase getInstance() {
        return INSTANCE;
    }

    private static final ProductDatabase INSTANCE = new ProductDatabase();

    private final Map<String, Product> map = new HashMap<>();

    {
        // CHECKSTYLE:OFF magic numbers

        // In the sample database product 30 is Canon EOS 5D and it has option code 226 with values 15 (Red) or 16 (Blue)
        Camera camera = new Camera(30, "Canon EOS 5D", Color.RED);
        map.put(camera.getName(), camera);

        Product product = new Product(40, "iPhone");
        map.put(product.getName(), product);

        // CHECKSTYLE:ON

    }

    public Product findByName(String name) {
        return map.get(name);
    }
}
