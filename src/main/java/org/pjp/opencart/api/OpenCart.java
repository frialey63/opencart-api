package org.pjp.opencart.api;

import java.util.Map;
import java.util.Map.Entry;

import org.pjp.opencart.api.bean.Address;
import org.pjp.opencart.api.bean.Customer;
import org.pjp.opencart.api.bean.Login;
import org.pjp.opencart.api.bean.NewOrder;
import org.pjp.opencart.api.bean.PaymentMethodsWrapper;
import org.pjp.opencart.api.bean.Result;
import org.pjp.opencart.api.bean.Reward;
import org.pjp.opencart.api.bean.ShippingMethodsWrapper;
import org.pjp.opencart.api.bean.ShoppingCart;
import org.pjp.opencart.api.bean.Voucher;
import org.pjp.opencart.api.exception.CartException;
import org.pjp.opencart.api.exception.OrderException;
import org.pjp.opencart.api.exception.StatusException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import com.google.common.annotations.VisibleForTesting;

public class OpenCart {

    public class Cart {

        public Cart add(int productId, int quantity, Map<Integer, Integer> options) throws CartException {
            MultiValueMap<String, String> map = new LinkedMultiValueMap<String, String>();
            map.add("product_id", Integer.toString(productId));
            map.add("quantity", Integer.toString(quantity));

            for (Entry<Integer, Integer> option : options.entrySet()) {
                map.add(String.format("option[%d]", option.getKey()), option.getValue().toString());
            }

            ResponseEntity<Result> response = performPostRequest("cart/add", map, Result.class);

            // check response
            if (response.getStatusCode() == HttpStatus.OK) {
                lastResult = response.getBody();

                if (lastResult.ok()) {
                    return this;
                } else {
                    Object error = response.getBody().getError();
                    LOGGER.info((String) error);
                    throw new CartException(error);
                }
            } else {
                String msg = "cart/add failed with status code " + response.getStatusCode();
                LOGGER.error(msg);
                throw new StatusException(msg);
            }
        }

        public Cart edit(int cartId, int quantity) throws CartException {
            MultiValueMap<String, String> map = new LinkedMultiValueMap<String, String>();
            map.add("key", Integer.toString(cartId));
            map.add("quantity", Integer.toString(quantity));

            ResponseEntity<Result> response = performPostRequest("cart/edit", map, Result.class);

            // check response
            if (response.getStatusCode() == HttpStatus.OK) {
                lastResult = response.getBody();

                if (lastResult.ok()) {
                    return this;
                } else {
                    Object error = response.getBody().getError();
                    LOGGER.info((String) error);
                    throw new CartException(error);
                }
            } else {
                String msg = "cart/edit failed with status code " + response.getStatusCode();
                LOGGER.error(msg);
                throw new StatusException(msg);
            }
        }

        public Cart remove(int cartId) throws CartException {
            MultiValueMap<String, String> map = new LinkedMultiValueMap<String, String>();
            map.add("key", Integer.toString(cartId));

            ResponseEntity<Result> response = performPostRequest("cart/remove", map, Result.class);

            // check response
            if (response.getStatusCode() == HttpStatus.OK) {
                lastResult = response.getBody();

                if (lastResult.ok()) {
                    return this;
                } else {
                    Object error = response.getBody().getError();
                    LOGGER.info((String) error);
                    throw new CartException(error);
                }
            } else {
                String msg = "cart/remove failed with status code " + response.getStatusCode();
                LOGGER.error(msg);
                throw new StatusException(msg);
            }
        }

        public ShoppingCart products() {
            ResponseEntity<ShoppingCart> response = performPostRequest("cart/products", new LinkedMultiValueMap<String, String>(), ShoppingCart.class);

            // check response
            if (response.getStatusCode() == HttpStatus.OK) {
                lastResult = response.getBody();
                return response.getBody();
            } else {
                String msg = "cart/products failed with status code " + response.getStatusCode();
                LOGGER.error(msg);
                throw new StatusException(msg);
            }
        }
    }

    public class Order {

        private int orderId;

        public Order add() throws OrderException {
            ResponseEntity<NewOrder> response = performPostRequest("order/add", new LinkedMultiValueMap<String, String>(), NewOrder.class);

            // check response
            if (response.getStatusCode() == HttpStatus.OK) {
                lastResult = response.getBody();

                if (lastResult.ok()) {
                    orderId = response.getBody().getOrderId();
                    return this;
                } else {
                    Object error = response.getBody().getError();
                    LOGGER.info((String) error);
                    throw new OrderException(error);
                }
            } else {
                String msg = "order/add failed with status code " + response.getStatusCode();
                LOGGER.error(msg);
                throw new StatusException(msg);
            }
        }

        // TODO this API does not appear to be implemented
        public Order edit() throws OrderException {
            ResponseEntity<Result> response = performPostRequest("order/edit", new LinkedMultiValueMap<String, String>(), Result.class);

            // check response
            if (response.getStatusCode() == HttpStatus.OK) {
                lastResult = response.getBody();

                if (lastResult.ok()) {
                    return this;
                } else {
                    Object error = response.getBody().getError();
                    LOGGER.info((String) error);
                    throw new OrderException(error);
                }
            } else {
                String msg = "order/edit failed with status code " + response.getStatusCode();
                LOGGER.error(msg);
                throw new StatusException(msg);
           }
        }

        // TODO this API does not appear to be implemented
        public Order delete() throws OrderException {
            ResponseEntity<Result> response = performPostRequest("order/delete", new LinkedMultiValueMap<String, String>(), Result.class);

            // check response
            if (response.getStatusCode() == HttpStatus.OK) {
                lastResult = response.getBody();

                if (lastResult.ok()) {
                    return this;
                } else {
                    Object error = response.getBody().getError();
                    LOGGER.info((String) error);
                    throw new OrderException(error);
                }
            } else {
                String msg = "order/delete failed with status code " + response.getStatusCode();
                LOGGER.error(msg);
                throw new StatusException(msg);
            }
        }

        // TODO this API does not appear to be implemented
        public Order info() throws OrderException {
            ResponseEntity<Result> response = performPostRequest("order/info", new LinkedMultiValueMap<String, String>(), Result.class);

            // check response
            if (response.getStatusCode() == HttpStatus.OK) {
                lastResult = response.getBody();

                if (lastResult.ok()) {
                    return this;
                } else {
                    Object error = response.getBody().getError();
                    LOGGER.info((String) error);
                    throw new OrderException(error);
                }
            } else {
                String msg = "order/info failed with status code " + response.getStatusCode();
                LOGGER.error(msg);
                throw new StatusException(msg);
            }
        }

        // TODO this API does not appear to be implemented
        public Order history() throws OrderException {
            ResponseEntity<Result> response = performPostRequest("order/history", new LinkedMultiValueMap<String, String>(), Result.class);

            // check response
            if (response.getStatusCode() == HttpStatus.OK) {
                lastResult = response.getBody();

                if (lastResult.ok()) {
                    return this;
                } else {
                    Object error = response.getBody().getError();
                    LOGGER.info((String) error);
                    throw new OrderException(error);
                }
            } else {
                String msg = "order/history failed with status code " + response.getStatusCode();
                LOGGER.error(msg);
                throw new StatusException(msg);
            }
        }

        @Override
        public String toString() {
            return "Order [orderId=" + orderId + "]";
        }

    }

    @VisibleForTesting
	static HttpHeaders createHeaders() {
		HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
		return headers;
	}

    private static final Logger LOGGER = LoggerFactory.getLogger(OpenCart.class);

    private final String host;

    @VisibleForTesting
    Login login;

    private Result lastResult;

    private Cart cart;

    private Order order;

    private RestTemplate restTemplate;

    public OpenCart(String host) {
        super();
        this.host = host;
    }

    public void setRestTemplate(RestTemplate restTemplate) {
		this.restTemplate = restTemplate;
	}

	public boolean login(String username, String key) {
        String url = getApi() + "login";

        // request body form parameters
        MultiValueMap<String, String> map = new LinkedMultiValueMap<String, String>();
        map.add("username", username);
        map.add("key", key);

        // build the request
        HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<MultiValueMap<String, String>>(map, createHeaders());

        try {
            // send POST request
            ResponseEntity<Login> response = restTemplate.postForEntity(url, request, Login.class);

            // check response
            if (response.getStatusCode() == HttpStatus.OK) {
                login = response.getBody();
                cart = new Cart();
                order = new Order();
                return true;
            } else {
                LOGGER.error("login failed with status code " + response.getStatusCode());
                throw new StatusException("login failed with status code " + response.getStatusCode());
            }
        } catch (RestClientException e) {
            LOGGER.debug("login failed");
        }

        return false;
    }

    public boolean currency(Currency currency) {
        MultiValueMap<String, String> map = new LinkedMultiValueMap<String, String>();
        map.add("currency", currency.name());

        ResponseEntity<Result> response = performPostRequest("currency", map, Result.class);

        // check response
        if (response.getStatusCode() == HttpStatus.OK) {
            lastResult = response.getBody();
            return lastResult.ok();
        } else {
            LOGGER.error("currency failed with status code " + response.getStatusCode());
            throw new StatusException("currency failed with status code " + response.getStatusCode());
        }
    }

    public boolean coupon(int coupon) {
        MultiValueMap<String, String> map = new LinkedMultiValueMap<String, String>();
        map.add("coupon", Integer.toString(coupon));

        ResponseEntity<Result> response = performPostRequest("coupon", map, Result.class);

        // check response
        if (response.getStatusCode() == HttpStatus.OK) {
            lastResult = response.getBody();
            return lastResult.ok();
        } else {
            LOGGER.error("coupon failed with status code " + response.getStatusCode());
            throw new StatusException("coupon failed with status code " + response.getStatusCode());
        }
    }

    public boolean customer(Customer customer) {
        MultiValueMap<String, String> map = new LinkedMultiValueMap<String, String>();
        map.add("firstname", customer.getFirstname());
        map.add("lastname", customer.getLastname());
        map.add("email", customer.getEmail());
        map.add("telephone", customer.getTelephone());

        ResponseEntity<Result> response = performPostRequest("customer", map, Result.class);

        // check response
        if (response.getStatusCode() == HttpStatus.OK) {
            lastResult = response.getBody();
            return lastResult.ok();
        } else {
            LOGGER.error("customer failed with status code " + response.getStatusCode());
            throw new StatusException("customer failed with status code " + response.getStatusCode());
        }
    }

    public boolean voucher(String voucher) {
        MultiValueMap<String, String> map = new LinkedMultiValueMap<String, String>();
        map.add("voucher", voucher);

        ResponseEntity<Result> response = performPostRequest("voucher", map, Result.class);

        // check response
        if (response.getStatusCode() == HttpStatus.OK) {
            lastResult = response.getBody();
            return lastResult.ok();
        } else {
            LOGGER.error("voucher failed with status code " + response.getStatusCode());
            throw new StatusException("voucher failed with status code " + response.getStatusCode());
        }
    }

    public boolean addVoucher(Voucher voucher) {
        MultiValueMap<String, String> map = new LinkedMultiValueMap<String, String>();
        map.add("from_name", voucher.getFromName());
        map.add("from_email", voucher.getFromEmail());
        map.add("to_name", voucher.getToName());
        map.add("to_email", voucher.getToEmail());
        map.add("amount", Float.toString(voucher.getAmount()));
        map.add("code", voucher.getCode());

        ResponseEntity<Result> response = performPostRequest("voucher/add", map, Result.class);

        // check response
        if (response.getStatusCode() == HttpStatus.OK) {
            lastResult = response.getBody();
            return lastResult.ok();
        } else {
            LOGGER.error("voucher/add failed with status code " + response.getStatusCode());
            throw new StatusException("voucher/add failed with status code " + response.getStatusCode());
        }
    }

    public boolean shippingAddress(Address address) {
        MultiValueMap<String, String> map = new LinkedMultiValueMap<String, String>();
        map.add("firstname", address.getFirstname());
        map.add("lastname", address.getLastname());
        map.add("address_1", address.getAddress1());
        map.add("city", address.getCity());
        map.add("country_id", address.getCountryId());
        map.add("zone_id", address.getZoneId());

        try {
            ResponseEntity<Result> response = performPostRequest("shipping/address", map, Result.class);

            // check response
            if (response.getStatusCode() == HttpStatus.OK) {
                lastResult = response.getBody();
                return lastResult.ok();
            } else {
                LOGGER.error("shipping/address failed with status code " + response.getStatusCode());
                throw new StatusException("shipping/address failed with status code " + response.getStatusCode());
            }
        } catch (RestClientException e) {
            LOGGER.info("not logged in or shipping/address requires a product in the the cart");
        }

        return false;
    }

    public ShippingMethodsWrapper shippingMethods() {
        ResponseEntity<ShippingMethodsWrapper> response = performPostRequest("shipping/methods", new LinkedMultiValueMap<String, String>(), ShippingMethodsWrapper.class);

        // check response
        if (response.getStatusCode() == HttpStatus.OK) {
            lastResult = response.getBody();
            return response.getBody();
        } else {
            LOGGER.error("shipping/methods failed with status code " + response.getStatusCode());
            throw new StatusException("shipping/methods failed with status code " + response.getStatusCode());
        }
    }

    public boolean shippingMethod(String code) {
        MultiValueMap<String, String> map = new LinkedMultiValueMap<String, String>();
        map.add("shipping_method", code);

        ResponseEntity<Result> response = performPostRequest("shipping/method", map, Result.class);

        // check response
        if (response.getStatusCode() == HttpStatus.OK) {
            lastResult = response.getBody();
            return lastResult.ok();
        } else {
            LOGGER.error("shipping/method failed with status code " + response.getStatusCode());
            throw new StatusException("shipping/method failed with status code " + response.getStatusCode());
        }
    }

    public boolean reward(int points) {
        MultiValueMap<String, String> map = new LinkedMultiValueMap<String, String>();
        map.add("reward", Integer.toString(points));

        ResponseEntity<Result> response = performPostRequest("reward", map, Result.class);

        // check response
        if (response.getStatusCode() == HttpStatus.OK) {
            lastResult = response.getBody();
            return lastResult.ok();
        } else {
            LOGGER.error("reward failed with status code " + response.getStatusCode());
            throw new StatusException("reward failed with status code " + response.getStatusCode());
        }
    }

    public int rewardMaximum() {
        ResponseEntity<Reward> response = performPostRequest("reward/maximum", new LinkedMultiValueMap<String, String>(), Reward.class);

        // check response
        if (response.getStatusCode() == HttpStatus.OK) {
            lastResult = response.getBody();
            return response.getBody().getMaximum();
        } else {
            LOGGER.error("reward/maximum failed with status code " + response.getStatusCode());
            throw new StatusException("reward/maximum failed with status code " + response.getStatusCode());
        }
    }

    public String rewardAvailable() {
        ResponseEntity<Reward> response = performPostRequest("reward/available", new LinkedMultiValueMap<String, String>(), Reward.class);

        // check response
        if (response.getStatusCode() == HttpStatus.OK) {
            lastResult = response.getBody();
            return response.getBody().getPoints();
        } else {
            LOGGER.error("reward/available failed with status code " + response.getStatusCode());
            throw new StatusException("reward/available failed with status code " + response.getStatusCode());
        }
    }

    public boolean paymentAddress(Address address) {
        MultiValueMap<String, String> map = new LinkedMultiValueMap<String, String>();
        map.add("firstname", address.getFirstname());
        map.add("lastname", address.getLastname());
        map.add("address_1", address.getAddress1());
        map.add("city", address.getCity());
        map.add("country_id", address.getCountryId());
        map.add("zone_id", address.getZoneId());

        try {
            ResponseEntity<Result> response = performPostRequest("payment/address", map, Result.class);

            // check response
            if (response.getStatusCode() == HttpStatus.OK) {
                lastResult = response.getBody();
                return lastResult.ok();
            } else {
                LOGGER.error("payment/address failed with status code " + response.getStatusCode());
                throw new StatusException("payment/address failed with status code " + response.getStatusCode());
            }
        } catch (RestClientException e) {
            LOGGER.info("not logged in or payment/address requires a product in the the cart");
        }

        return false;
    }

    public PaymentMethodsWrapper paymentMethods() {
        ResponseEntity<PaymentMethodsWrapper> response = performPostRequest("payment/methods", new LinkedMultiValueMap<String, String>(), PaymentMethodsWrapper.class);

        // check response
        if (response.getStatusCode() == HttpStatus.OK) {
            lastResult = response.getBody();
            return response.getBody();
        } else {
            LOGGER.error("payment/methods failed with status code " + response.getStatusCode());
            throw new StatusException("payment/methods failed with status code " + response.getStatusCode());
        }
    }

    public boolean paymentMethod(String code) {
        MultiValueMap<String, String> map = new LinkedMultiValueMap<String, String>();
        map.add("payment_method", code);

        ResponseEntity<Result> response = performPostRequest("payment/method", map, Result.class);

        // check response
        if (response.getStatusCode() == HttpStatus.OK) {
            lastResult = response.getBody();
            return lastResult.ok();
        } else {
            LOGGER.error("payment/method failed with status code " + response.getStatusCode());
            throw new StatusException("payment/method failed with status code " + response.getStatusCode());
        }
    }

    private <T> ResponseEntity<T> performPostRequest(String route, MultiValueMap<String, String> map, Class<T> clazz) {
        String url = getApi() + route + "&api_token={apiToken}";

        // create headers and build the request
        HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<MultiValueMap<String, String>>(map, createHeaders());

        // send POST request
        return restTemplate.postForEntity(url, request, clazz, login.getApiToken());
    }

    public Cart getCart() {
        return cart;
    }

    public Order getOrder() {
        return order;
    }

    public Result getLastResult() {
        return lastResult;
    }

    @VisibleForTesting
    String getApi() {
        return String.format("http://%s/index.php?route=api/", host);
    }

}
