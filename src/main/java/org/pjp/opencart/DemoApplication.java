package org.pjp.opencart;

import org.pjp.opencart.api.Currency;
import org.pjp.opencart.api.OpenCart;
import org.pjp.opencart.api.bean.Customer;
import org.pjp.opencart.api.bean.Address;
import org.pjp.opencart.api.bean.Voucher;
import org.pjp.opencart.api.exception.CartException;
import org.pjp.opencart.api.exception.OrderException;
import org.pjp.opencart.db.Camera;
import org.pjp.opencart.db.ProductDatabase;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class DemoApplication implements CommandLineRunner {

    private static final String USERNAME = "eclipse";

    private static final String KEY = "8uIXHOvT5PjAR4oBFN7cXORsEkWOOO3ZdiSAa2qtzClKo0CB5B8iInED3XkdNCaz6Afd4oPpSGT4tzXYXNVP5A7VOhCFQMZKpt4NDEUpuSVEUctdSiZ2KELV1D433yTuxxdT6BSyFMtyCuCzp5dYCnv6My61QB1zv1GW7qj3fhPbpdAbiYI5nUrc5sbUNszVy5paoWxXNrlH1OeBOjirocDjbw3JCb45ghiF2uWTGqPrqYUd4XDtAJUTicjt2H1S";

    private static final Logger LOGGER = LoggerFactory.getLogger(DemoApplication.class);

    public static void main(String[] args) {
        SpringApplication.run(DemoApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        OpenCart openCart = new OpenCart("localhost");

        if (openCart.login(USERNAME, KEY)) {
            System.out.println("currency USD = " + openCart.currency(Currency.USD));

            System.out.println("coupon = " + openCart.coupon(2222));

            System.out.println("customer = " + openCart.customer(new Customer("Fred", "Bloggs", "fred.bloggs@gmail.com", "999")));

            System.out.println("voucher = " + openCart.voucher("VOU-7271"));
            System.out.println("voucher/add = " + openCart.addVoucher(new Voucher("Fred Bloggs", "fred.bloggs@gmail.com", "Ronnie Scott", "ronnie.scott@hotmail.com", 100, "VOU-7177")));

            ProductDatabase instance = ProductDatabase.getInstance();
            Camera camera = (Camera) instance.findByName("Canon EOS 5D");

            try {
                System.out.println(openCart.getCart().add(camera.getId(), 1, camera.getOptions()).products());

                System.out.println("shippingAddress = " + openCart.shippingAddress(new Address("Fred", "Bloggs", "123 The Street", "London", "UK", "NW6")));
                System.out.println(openCart.shippingMethods());
                System.out.println("shippingMethod = " + openCart.shippingMethod("flat.flat"));

                System.out.println("reward = " + openCart.reward(10));
                System.out.println("reward/maximum = " + openCart.rewardMaximum());
                System.out.println("reward/available = " + openCart.rewardAvailable());

                System.out.println("paymentAddress = " + openCart.paymentAddress(new Address("Fred", "Bloggs", "123 The Street", "London", "UK", "NW6")));
                System.out.println(openCart.paymentMethods());
                System.out.println("paymentMethod = " + openCart.paymentMethod("cod"));

                try {
                    System.out.println(openCart.getOrder().add());
                } catch (OrderException e) {
                    LOGGER.error("", e);
                }
            } catch (CartException e) {
                LOGGER.error("", e);
            }

        } else {
            System.err.println("unable to login with username of " + USERNAME);
        }
    }

}
