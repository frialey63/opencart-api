package org.pjp.opencart;

import org.pjp.opencart.api.Currency;
import org.pjp.opencart.api.OpenCart;
import org.pjp.opencart.api.bean.Address;
import org.pjp.opencart.api.bean.Customer;
import org.pjp.opencart.api.bean.Voucher;
import org.pjp.opencart.api.exception.CartException;
import org.pjp.opencart.api.exception.OrderException;
import org.pjp.opencart.db.Camera;
import org.pjp.opencart.db.ProductDatabase;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

/**
 * A driver program for the OpenCart API which generates an order in the wizard style interaction of the web site.
 * @author developer
 *
 */
@SpringBootApplication
public class DemoApplication implements CommandLineRunner {

    private static final String PAYMENT_METHOD = "cod";

    private static final String SHIPPING_METHOD = "flat.flat";

    private static final Address ADDRESS = new Address("Fred", "Bloggs", "123 The Street", "London", "UK", "NW6");

    private static final String VOUCHER = "VOU-7271";

    private static final Customer CUSTOMER = new Customer("Fred", "Bloggs", "fred.bloggs@gmail.com", "999");

    private static final Voucher NEW_VOUCHER = new Voucher("Fred Bloggs", "fred.bloggs@gmail.com", "Ronnie Scott", "ronnie.scott@hotmail.com", 100, "VOU-7177");

    private static final int COUPON = 2222;

    private static final int REWARD = 10;

    private static final String LOCALHOST = "localhost";

    private static final Logger LOGGER = LoggerFactory.getLogger(DemoApplication.class);

    @Value("${opencart.api.username}")
    private String username;

    @Value("${opencart.api.key}")
    private String key;

    /**
     * @param args program arguments
     */
    public static void main(String[] args) {
        SpringApplication.run(DemoApplication.class, args);
    }

    /**
     * @return The RestTemplate
     */
    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }

    @Override
    public void run(String... args) throws Exception {
        OpenCart openCart = new OpenCart(LOCALHOST);

        openCart.setRestTemplate(restTemplate());

        if (openCart.login(username, key)) {
            System.out.println("successful login with username of " + username);

            System.out.println("----");

            System.out.println(openCart.currency(Currency.USD) ? "set currency to USD" : "unable to set currency");
            System.out.println(openCart.customer(CUSTOMER) ? "the customer has been set" : "unable to set the customer");
            System.out.println(openCart.coupon(COUPON) ? "applied the coupon " + COUPON : "unable to apply coupon");

            System.out.println("----");

            System.out.println(openCart.voucher(VOUCHER) ? "applied existing voucher " + VOUCHER : "unable to apply existing voucher");
            System.out.println(openCart.addVoucher(NEW_VOUCHER) ? "added the new voucher " + NEW_VOUCHER.getCode() : "unable to add new voucher");

            System.out.println("----");

            ProductDatabase instance = ProductDatabase.getInstance();
            Camera camera = (Camera) instance.findByName("Canon EOS 5D");

            try {
                System.out.println("products = " + openCart.getCart().add(camera.getId(), 1, camera.getOptions()).products());

                System.out.println("----");

                System.out.println(openCart.shippingAddress(ADDRESS) ? "the shipping address has been set" : "unable to set shipping address");
                System.out.println("shipping methods = " + openCart.shippingMethods());
                System.out.println(openCart.shippingMethod(SHIPPING_METHOD) ? "set shipping method to " + SHIPPING_METHOD : "unable to set shipping method");

                System.out.println("----");

                System.out.println("reward maximum = " + openCart.rewardMaximum());
                System.out.println("reward available = " + openCart.rewardAvailable());
                System.out.println(openCart.reward(REWARD) ? "utilised a reward of " + REWARD : "unable to utilise a reward");

                System.out.println("----");

                System.out.println(openCart.paymentAddress(ADDRESS) ? "the payment address has been set" : "unable to set payment address");
                System.out.println("payment methods = " + openCart.paymentMethods());
                System.out.println(openCart.paymentMethod(PAYMENT_METHOD) ? "set payment method to " + PAYMENT_METHOD : "unable to set payment method");

                System.out.println("----");

                try {
                    System.out.println(openCart.getOrder().add());
                } catch (OrderException e) {
                    LOGGER.error("", e);
                }
            } catch (CartException e) {
                LOGGER.error("", e);
            }

        } else {
            System.err.println("unable to login with username of " + username);
        }
    }

}
