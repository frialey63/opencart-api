package org.pjp.opencart;

import java.io.IOException;
import java.util.Properties;

import org.pjp.opencart.api.Currency;
import org.pjp.opencart.api.OpenCart;
import org.pjp.opencart.api.bean.Address;
import org.pjp.opencart.api.bean.Customer;
import org.pjp.opencart.api.exception.CartException;
import org.pjp.opencart.api.exception.OrderException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PropertiesLoaderUtils;
import org.springframework.web.client.RestTemplate;

/**
 * A driver program for the OpenCart API which generates an order in the wizard style interaction of the web site.
 * @author developer
 *
 */
public class OpenCartUtility {

    private static final String PAYMENT_METHOD = "cod";

    private static final String SHIPPING_METHOD = "flat.flat";

    private static final Address ADDRESS = new Address("EposNow", "EposNow", "Manston Road", "Ramsgate", "UK", "CT12 5DF");

    private static final Customer CUSTOMER = new Customer("EposNow", "EposNow", "info@rafmanston.co.uk", "01843 825224");

    private static final String APPLICATION_PROPERTIES = "/application.properties";

    private static final String LOCALHOST = "localhost";

    private static final Logger LOGGER = LoggerFactory.getLogger(OpenCartUtility.class);

    static {
        Resource resource = new ClassPathResource(APPLICATION_PROPERTIES);
        try {
            Properties props = PropertiesLoaderUtils.loadProperties(resource);

            username = props.getProperty("opencart.api.username");
            key = props.getProperty("opencart.api.key");
        } catch (IOException e) {
            LOGGER.error("failed to read application.properties", e);
        }
    }

    private static String username;

    private static String key;

    /**
     * @param args The program arguments
     * @throws IOException
     */
    public static void main(String[] args) throws IOException {
        if (args.length == 2) {
            int productId = Integer.parseInt(args[0]);
            int quantity = Integer.parseInt(args[1]);

            new OpenCartUtility(productId, quantity);
        } else {
            System.out.println("usage: OpenCartUtility <product_id> <quantity>");
        }
    }

    private OpenCartUtility(int productId, int quantity) {
        super();

        OpenCart openCart = new OpenCart(LOCALHOST);

        openCart.setRestTemplate(restTemplate());

        if (openCart.login(username, key)) {
            System.out.println("successful login with username of " + username);

            System.out.println("----");

            System.out.println(openCart.currency(Currency.GBP) ? "set currency to GBP" : "unable to set currency");
            System.out.println(openCart.customer(CUSTOMER) ? "the customer has been set" : "unable to set the customer");

            System.out.println("----");

            try {
                System.out.println("products = " + openCart.getCart().add(productId, quantity).products());

                System.out.println("----");

                System.out.println(openCart.shippingAddress(ADDRESS) ? "the shipping address has been set" : "unable to set shipping address");
                System.out.println("shipping methods = " + openCart.shippingMethods());
                System.out.println(openCart.shippingMethod(SHIPPING_METHOD) ? "set shipping method to " + SHIPPING_METHOD : "unable to set shipping method");

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

    private RestTemplate restTemplate() {
        return new RestTemplate();
    }
}
