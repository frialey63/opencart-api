package org.pjp.opencart.api;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import org.pjp.opencart.api.bean.Login;
import org.pjp.opencart.api.bean.Option;
import org.pjp.opencart.api.bean.Product;
import org.pjp.opencart.api.bean.Result;
import org.pjp.opencart.api.bean.ShoppingCart;
import org.pjp.opencart.api.bean.Total;
import org.pjp.opencart.api.bean.Voucher;
import org.pjp.opencart.api.exception.CartException;
import org.pjp.opencart.api.exception.StatusException;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

public class CartTest {

    private static final String LOCALHOST = "localhost";

    private RestTemplate restTemplate;

    private OpenCart openCart;

    @Before
    public void before() {
        restTemplate = Mockito.mock(RestTemplate.class);

        openCart = new OpenCart(LOCALHOST);
        openCart.login = new Login("API-TOKEN");
        openCart.cart = openCart.new Cart();

        openCart.setRestTemplate(restTemplate);
    }

    @Test
    public void testAdd() throws CartException {
        int productId = 30;
        int quantity = 1;
        Result result = Result.create("Success", null);

        MultiValueMap<String, String> map = new LinkedMultiValueMap<String, String>();
        map.add("product_id", Integer.toString(productId));
        map.add("quantity", Integer.toString(quantity));

        // build the request
        HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<MultiValueMap<String, String>>(map, OpenCart.createHeaders());

        Mockito.when(restTemplate.postForEntity(openCart.getApi() + "cart/add&api_token={apiToken}", request, Result.class, openCart.login.getApiToken()))
            .thenReturn(new ResponseEntity<Result>(result, HttpStatus.OK));

        assertEquals(openCart.getCart(), openCart.getCart().add(productId, quantity));
        assertNotNull(openCart.getLastResult());
    }

    @Test(expected = CartException.class)
    public void testAddError() throws CartException {
        int productId = 30;
        int quantity = 1;
        Result result = Result.create(null, "Error");

        MultiValueMap<String, String> map = new LinkedMultiValueMap<String, String>();
        map.add("product_id", Integer.toString(productId));
        map.add("quantity", Integer.toString(quantity));

        // build the request
        HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<MultiValueMap<String, String>>(map, OpenCart.createHeaders());

        Mockito.when(restTemplate.postForEntity(openCart.getApi() + "cart/add&api_token={apiToken}", request, Result.class, openCart.login.getApiToken()))
            .thenReturn(new ResponseEntity<Result>(result, HttpStatus.OK));

        openCart.getCart().add(productId, quantity);
    }

    @Test(expected = StatusException.class)
    public void testAddFailure() throws CartException {
        int productId = 30;
        int quantity = 1;
        Result result = Result.create(null, "Error");

        MultiValueMap<String, String> map = new LinkedMultiValueMap<String, String>();
        map.add("product_id", Integer.toString(productId));
        map.add("quantity", Integer.toString(quantity));

        // build the request
        HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<MultiValueMap<String, String>>(map, OpenCart.createHeaders());

        Mockito.when(restTemplate.postForEntity(openCart.getApi() + "cart/add&api_token={apiToken}", request, Result.class, openCart.login.getApiToken()))
            .thenReturn(new ResponseEntity<Result>(result, HttpStatus.BAD_REQUEST));

        openCart.getCart().add(productId, quantity);
    }

    @Test
    public void testEdit() throws CartException {
        int cartId = 123;
        int quantity = 1;
        Result result = Result.create("Success", null);

        MultiValueMap<String, String> map = new LinkedMultiValueMap<String, String>();
        map.add("key", Integer.toString(cartId));
        map.add("quantity", Integer.toString(quantity));

        // build the request
        HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<MultiValueMap<String, String>>(map, OpenCart.createHeaders());

        Mockito.when(restTemplate.postForEntity(openCart.getApi() + "cart/edit&api_token={apiToken}", request, Result.class, openCart.login.getApiToken()))
            .thenReturn(new ResponseEntity<Result>(result, HttpStatus.OK));

        assertEquals(openCart.getCart(), openCart.getCart().edit(cartId, quantity));
        assertNotNull(openCart.getLastResult());
    }

    @Test
    public void testRemove() throws CartException {
        int cartId = 123;
        Result result = Result.create("Success", null);

        MultiValueMap<String, String> map = new LinkedMultiValueMap<String, String>();
        map.add("key", Integer.toString(cartId));

        // build the request
        HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<MultiValueMap<String, String>>(map, OpenCart.createHeaders());

        Mockito.when(restTemplate.postForEntity(openCart.getApi() + "cart/remove&api_token={apiToken}", request, Result.class, openCart.login.getApiToken()))
            .thenReturn(new ResponseEntity<Result>(result, HttpStatus.OK));

        assertEquals(openCart.getCart(), openCart.getCart().remove(cartId));
        assertNotNull(openCart.getLastResult());
    }

    @Test
    public void testProducts() {
        Product[] products = {new Product("123", "30", "Canon EOS 5D", "EOS 5D", new Option[0], 2, true, 0, "100.00", "200.00", 50) };
        Voucher[] vouchers = {new Voucher("Fred Bloggs", "fred.bloggs@gmail.com", "Ronnie Scott", "ronnie.scott@hotmail.com", 100, "VOU-7177") };
        Total[] totals = {new Total("VAT", "17.50") };

        ShoppingCart shoppingCart = new ShoppingCart();
        shoppingCart.setProducts(products);
        shoppingCart.setVouchers(vouchers);
        shoppingCart.setTotals(totals);

        // build the request
        HttpEntity<MultiValueMap<String, String>> request =
                new HttpEntity<MultiValueMap<String, String>>((MultiValueMap<String, String>) new LinkedMultiValueMap<String, String>(), OpenCart.createHeaders());

        Mockito.when(restTemplate.postForEntity(openCart.getApi() + "cart/products&api_token={apiToken}", request, ShoppingCart.class, openCart.login.getApiToken()))
            .thenReturn(new ResponseEntity<ShoppingCart>(shoppingCart, HttpStatus.OK));

        assertEquals(shoppingCart, openCart.getCart().products());
        assertNotNull(openCart.getLastResult());
    }

}
