package org.pjp.opencart.api;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.fail;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.mockito.Mockito;
import org.pjp.opencart.api.bean.Login;
import org.pjp.opencart.api.bean.NewOrder;
import org.pjp.opencart.api.exception.OrderException;
import org.pjp.opencart.api.exception.StatusException;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

public class OrderTest {

    private static final String LOCALHOST = "localhost";

    private RestTemplate restTemplate;

    private OpenCart openCart;

    @Before
    public void before() {
        restTemplate = Mockito.mock(RestTemplate.class);

        openCart = new OpenCart(LOCALHOST);
        openCart.login = new Login("API-TOKEN");
        openCart.order = openCart.new Order();

        openCart.setRestTemplate(restTemplate);
    }

    @Test
    public void testAdd() throws OrderException {
        NewOrder newOrder = new NewOrder(123);

        // build the request
        HttpEntity<MultiValueMap<String, String>> request =
                new HttpEntity<MultiValueMap<String, String>>((MultiValueMap<String, String>) new LinkedMultiValueMap<String, String>(), OpenCart.createHeaders());

        Mockito.when(restTemplate.postForEntity(openCart.getApi() + "order/add&api_token={apiToken}", request, NewOrder.class, openCart.login.getApiToken()))
            .thenReturn(new ResponseEntity<NewOrder>(newOrder, HttpStatus.OK));

        assertEquals(openCart.getOrder(), openCart.getOrder().add());
        assertNotNull(openCart.getLastResult());
        assertEquals(newOrder.getOrderId(), openCart.getOrder().getOrderId());
    }

    @Test(expected = OrderException.class)
    public void testAddError() throws OrderException {
        NewOrder newOrder = new NewOrder();
        newOrder.setError("Error");

        // build the request
        HttpEntity<MultiValueMap<String, String>> request =
                new HttpEntity<MultiValueMap<String, String>>((MultiValueMap<String, String>) new LinkedMultiValueMap<String, String>(), OpenCart.createHeaders());

        Mockito.when(restTemplate.postForEntity(openCart.getApi() + "order/add&api_token={apiToken}", request, NewOrder.class, openCart.login.getApiToken()))
            .thenReturn(new ResponseEntity<NewOrder>(newOrder, HttpStatus.OK));

        openCart.getOrder().add();
    }

    @Test(expected = StatusException.class)
    public void testAddFailure() throws OrderException {
        NewOrder newOrder = new NewOrder();

        // build the request
        HttpEntity<MultiValueMap<String, String>> request =
                new HttpEntity<MultiValueMap<String, String>>((MultiValueMap<String, String>) new LinkedMultiValueMap<String, String>(), OpenCart.createHeaders());

        Mockito.when(restTemplate.postForEntity(openCart.getApi() + "order/add&api_token={apiToken}", request, NewOrder.class, openCart.login.getApiToken()))
            .thenReturn(new ResponseEntity<NewOrder>(newOrder, HttpStatus.BAD_REQUEST));

        openCart.getOrder().add();
    }

    @Ignore
    @Test
    public void testEdit() {
        fail("Not yet implemented");
    }

    @Ignore
    @Test
    public void testDelete() {
        fail("Not yet implemented");
    }

    @Ignore
    @Test
    public void testInfo() {
        fail("Not yet implemented");
    }

    @Ignore
    @Test
    public void testHistory() {
        fail("Not yet implemented");
    }

}
