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

@SpringBootApplication
public class DemoApplication implements CommandLineRunner {

    private static final String LOCALHOST = "localhost";

	private static final Logger LOGGER = LoggerFactory.getLogger(DemoApplication.class);

    @Value("${opencart.api.username}")
    private String username;

    @Value("${opencart.api.key}")
    private String key;

    public static void main(String[] args) {
        SpringApplication.run(DemoApplication.class, args);
    }

    @Bean
    private RestTemplate restTemplate() {
        return new RestTemplate();
    }

    @Override
    public void run(String... args) throws Exception {
        OpenCart openCart = new OpenCart(LOCALHOST);
        
        openCart.setRestTemplate(restTemplate());

        if (openCart.login(username, key)) {
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
            System.err.println("unable to login with username of " + username);
        }
    }

}
