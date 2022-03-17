package org.pjp.opencart.api;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import org.pjp.opencart.api.bean.Address;
import org.pjp.opencart.api.bean.Customer;
import org.pjp.opencart.api.bean.Login;
import org.pjp.opencart.api.bean.PaymentMethodsWrapper;
import org.pjp.opencart.api.bean.PaymentMethodsWrapper.PaymentMethods;
import org.pjp.opencart.api.bean.Result;
import org.pjp.opencart.api.bean.Reward;
import org.pjp.opencart.api.bean.ShippingMethodsWrapper;
import org.pjp.opencart.api.bean.ShippingMethodsWrapper.ShippingMethods;
import org.pjp.opencart.api.bean.Voucher;
import org.pjp.opencart.api.bean.payment.COD;
import org.pjp.opencart.api.bean.payment.FreeCheckout;
import org.pjp.opencart.api.bean.payment.PPStandard;
import org.pjp.opencart.api.bean.shipping.Flat;
import org.pjp.opencart.api.exception.StatusException;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

public class OpenCartTest {

    private static final String LOCALHOST = "localhost";

    private RestTemplate restTemplate;
    
    private OpenCart openCart;
    
    @Before
    public void before() {
    	restTemplate = Mockito.mock(RestTemplate.class);
    	
        openCart = new OpenCart(LOCALHOST);
        openCart.login = new Login("API-TOKEN");

        openCart.setRestTemplate(restTemplate);
    }
    
    @Test
    public void testLogin() {
        String username = "fred";
        String key = "API-KEY";
        Login login = new Login("API-TOKEN");
        
        MultiValueMap<String, String> map = new LinkedMultiValueMap<String, String>();
        map.add("username", username);
        map.add("key", key);
        
        // build the request
        HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<MultiValueMap<String, String>>(map, OpenCart.createHeaders());
        
        Mockito.when(restTemplate.postForEntity(openCart.getApi() + "login", request, Login.class))
        	.thenReturn(new ResponseEntity<Login>(login, HttpStatus.OK));
          
    	openCart.login = null;	// for login() undo the assignment made in the before()
    	
        assertTrue(openCart.login(username, key));
        assertNotNull(openCart.login);
        assertNotNull(openCart.getCart());
        assertNotNull(openCart.getOrder());
        assertNull(openCart.getLastResult());
	}

    @Test
    public void testNoLogin() {
        String username = "fred";
        String key = "API-KEY";
        
        MultiValueMap<String, String> map = new LinkedMultiValueMap<String, String>();
        map.add("username", username);
        map.add("key", key);
        
        // build the request
        HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<MultiValueMap<String, String>>(map, OpenCart.createHeaders());
        
        Mockito.when(restTemplate.postForEntity(openCart.getApi() + "login", request, Login.class))
        	.thenThrow(RestClientException.class);
          
    	openCart.login = null;	// for login() undo the assignment made in the before()
    	
        assertFalse(openCart.login(username, key));
        assertNull(openCart.login);
        assertNull(openCart.getCart());
        assertNull(openCart.getOrder());
        assertNull(openCart.getLastResult());
	}

    @Test(expected = StatusException.class)
    public void testLoginFailure() {
        String username = "fred";
        String key = "API-KEY";
        Login login = new Login("API-TOKEN");
        
        MultiValueMap<String, String> map = new LinkedMultiValueMap<String, String>();
        map.add("username", username);
        map.add("key", key);
        
        // build the request
        HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<MultiValueMap<String, String>>(map, OpenCart.createHeaders());
        
        Mockito.when(restTemplate.postForEntity(openCart.getApi() + "login", request, Login.class))
        	.thenReturn(new ResponseEntity<Login>(login, HttpStatus.BAD_REQUEST));
          
        openCart.login(username, key);
	}

	@Test
	public void testCurrency() {
        Currency currency = Currency.USD;
        Result result = Result.create("Success", null);
        
        MultiValueMap<String, String> map = new LinkedMultiValueMap<String, String>();
        map.add("currency", currency.name());
        
        // build the request
        HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<MultiValueMap<String, String>>(map, OpenCart.createHeaders());
        
        Mockito.when(restTemplate.postForEntity(openCart.getApi() + "currency&api_token={apiToken}", request, Result.class, openCart.login.getApiToken()))
        	.thenReturn(new ResponseEntity<Result>(result, HttpStatus.OK));
          
        assertTrue(openCart.currency(currency));
        assertNotNull(openCart.getLastResult());
	}

	@Test
	public void testCurrencyError() {
        Currency currency = Currency.USD;
        Result result = Result.create(null, "Error");
        
        MultiValueMap<String, String> map = new LinkedMultiValueMap<String, String>();
        map.add("currency", currency.name());
        
        // build the request
        HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<MultiValueMap<String, String>>(map, OpenCart.createHeaders());
        
        Mockito.when(restTemplate.postForEntity(openCart.getApi() + "currency&api_token={apiToken}", request, Result.class, openCart.login.getApiToken()))
        	.thenReturn(new ResponseEntity<Result>(result, HttpStatus.OK));
          
        assertFalse(openCart.currency(currency));
        assertNotNull(openCart.getLastResult());
	}

	@Test(expected = StatusException.class)
	public void testCurrencyFailure() {
        Currency currency = Currency.USD;
        Result result = Result.create(null, "Error");
        
        MultiValueMap<String, String> map = new LinkedMultiValueMap<String, String>();
        map.add("currency", currency.name());
        
        // build the request
        HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<MultiValueMap<String, String>>(map, OpenCart.createHeaders());
        
        Mockito.when(restTemplate.postForEntity(openCart.getApi() + "currency&api_token={apiToken}", request, Result.class, openCart.login.getApiToken()))
        	.thenReturn(new ResponseEntity<Result>(result, HttpStatus.BAD_REQUEST));
        
        openCart.currency(currency);
	}

	@Test
	public void testCoupon() {
        int coupon = 2222;
        Result result = Result.create("Success", null);
        
        MultiValueMap<String, String> map = new LinkedMultiValueMap<String, String>();
        map.add("coupon", Integer.toString(coupon));
        
        // build the request
        HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<MultiValueMap<String, String>>(map, OpenCart.createHeaders());
        
        Mockito.when(restTemplate.postForEntity(openCart.getApi() + "coupon&api_token={apiToken}", request, Result.class, openCart.login.getApiToken()))
        	.thenReturn(new ResponseEntity<Result>(result, HttpStatus.OK));
          
        assertTrue(openCart.coupon(coupon));
        assertNotNull(openCart.getLastResult());
	}

	@Test
	public void testCustomer() {
        Customer customer = new Customer("Fred", "Bloggs", "fred.bloggs@gmail.com", "999");
        Result result = Result.create("Success", null);
        
        MultiValueMap<String, String> map = new LinkedMultiValueMap<String, String>();
        map.add("firstname", customer.getFirstname());
        map.add("lastname", customer.getLastname());
        map.add("email", customer.getEmail());
        map.add("telephone", customer.getTelephone());
        
        // build the request
        HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<MultiValueMap<String, String>>(map, OpenCart.createHeaders());
        
        Mockito.when(restTemplate.postForEntity(openCart.getApi() + "customer&api_token={apiToken}", request, Result.class, openCart.login.getApiToken()))
        	.thenReturn(new ResponseEntity<Result>(result, HttpStatus.OK));
          
        assertTrue(openCart.customer(customer));
        assertNotNull(openCart.getLastResult());
	}

	@Test
	public void testVoucher() {
		String voucher = "VOU-7271";
        Result result = Result.create("Success", null);
        
        MultiValueMap<String, String> map = new LinkedMultiValueMap<String, String>();
        map.add("voucher", voucher);
        
        // build the request
        HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<MultiValueMap<String, String>>(map, OpenCart.createHeaders());
        
        Mockito.when(restTemplate.postForEntity(openCart.getApi() + "voucher&api_token={apiToken}", request, Result.class, openCart.login.getApiToken()))
        	.thenReturn(new ResponseEntity<Result>(result, HttpStatus.OK));
          
        assertTrue(openCart.voucher(voucher));
        assertNotNull(openCart.getLastResult());
	}

	@Test
	public void testAddVoucher() {
		Voucher voucher = new Voucher("Fred Bloggs", "fred.bloggs@gmail.com", "Ronnie Scott", "ronnie.scott@hotmail.com", 100, "VOU-7177");
        Result result = Result.create("Success", null);
        
        MultiValueMap<String, String> map = new LinkedMultiValueMap<String, String>();
        map.add("from_name", voucher.getFromName());
        map.add("from_email", voucher.getFromEmail());
        map.add("to_name", voucher.getToName());
        map.add("to_email", voucher.getToEmail());
        map.add("amount", Float.toString(voucher.getAmount()));
        map.add("code", voucher.getCode());
        
        // build the request
        HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<MultiValueMap<String, String>>(map, OpenCart.createHeaders());
        
        Mockito.when(restTemplate.postForEntity(openCart.getApi() + "voucher/add&api_token={apiToken}", request, Result.class, openCart.login.getApiToken()))
        	.thenReturn(new ResponseEntity<Result>(result, HttpStatus.OK));
          
        assertTrue(openCart.addVoucher(voucher));
        assertNotNull(openCart.getLastResult());
        assertNotNull(openCart.getLastResult());
	}

	@Test
	public void testShippingAddress() {
		Address address = new Address("Fred", "Bloggs", "123 The Street", "London", "UK", "NW6");
        Result result = Result.create("Success", null);
        
        MultiValueMap<String, String> map = new LinkedMultiValueMap<String, String>();
        map.add("firstname", address.getFirstname());
        map.add("lastname", address.getLastname());
        map.add("address_1", address.getAddress1());
        map.add("city", address.getCity());
        map.add("country_id", address.getCountryId());
        map.add("zone_id", address.getZoneId());
        
        // build the request
        HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<MultiValueMap<String, String>>(map, OpenCart.createHeaders());
        
        Mockito.when(restTemplate.postForEntity(openCart.getApi() + "shipping/address&api_token={apiToken}", request, Result.class, openCart.login.getApiToken()))
        	.thenReturn(new ResponseEntity<Result>(result, HttpStatus.OK));
          
        assertTrue(openCart.shippingAddress(address));
        assertNotNull(openCart.getLastResult());
	}

	@Test
	public void testShippingMethods() {
		ShippingMethods shippingMethods = new ShippingMethods();
		shippingMethods.setFlat(new Flat());

		ShippingMethodsWrapper expected = new ShippingMethodsWrapper();
		expected.setShippingMethods(shippingMethods);
        
        // build the request
        HttpEntity<MultiValueMap<String, String>> request = 
        		new HttpEntity<MultiValueMap<String, String>>((MultiValueMap<String, String>) new LinkedMultiValueMap<String, String>(), OpenCart.createHeaders());
        
        Mockito.when(restTemplate.postForEntity(openCart.getApi() + "shipping/methods&api_token={apiToken}", request, ShippingMethodsWrapper.class, openCart.login.getApiToken()))
        	.thenReturn(new ResponseEntity<ShippingMethodsWrapper>(expected, HttpStatus.OK));
          
        assertEquals(expected, openCart.shippingMethods());
        assertNotNull(openCart.getLastResult());
	}

	@Test
	public void testShippingMethod() {
        String shippingMethod = "flat.flat";
        Result result = Result.create("Success", null);
        
        MultiValueMap<String, String> map = new LinkedMultiValueMap<String, String>();
        map.add("shipping_method", shippingMethod);
        
        // build the request
        HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<MultiValueMap<String, String>>(map, OpenCart.createHeaders());
        
        Mockito.when(restTemplate.postForEntity(openCart.getApi() + "shipping/method&api_token={apiToken}", request, Result.class, openCart.login.getApiToken()))
        	.thenReturn(new ResponseEntity<Result>(result, HttpStatus.OK));
          
        assertTrue(openCart.shippingMethod(shippingMethod));
        assertNotNull(openCart.getLastResult());
	}

	@Test
	public void testReward() {
        int reward = 10;
        Result result = Result.create("Success", null);
        
        MultiValueMap<String, String> map = new LinkedMultiValueMap<String, String>();
        map.add("reward", Integer.toString(reward));
        
        // build the request
        HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<MultiValueMap<String, String>>(map, OpenCart.createHeaders());
        
        Mockito.when(restTemplate.postForEntity(openCart.getApi() + "reward&api_token={apiToken}", request, Result.class, openCart.login.getApiToken()))
        	.thenReturn(new ResponseEntity<Result>(result, HttpStatus.OK));
          
        assertTrue(openCart.reward(reward));
        assertNotNull(openCart.getLastResult());
	}

	@Test
	public void testRewardMaximum() {
        Reward reward = new Reward(100, null);
        reward.setSuccess("success");
        
        // build the request
        HttpEntity<MultiValueMap<String, String>> request = 
        		new HttpEntity<MultiValueMap<String, String>>((MultiValueMap<String, String>) new LinkedMultiValueMap<String, String>(), OpenCart.createHeaders());
        
        Mockito.when(restTemplate.postForEntity(openCart.getApi() + "reward/maximum&api_token={apiToken}", request, Reward.class, openCart.login.getApiToken()))
        	.thenReturn(new ResponseEntity<Reward>(reward, HttpStatus.OK));
          
        assertEquals(reward.getMaximum(), openCart.rewardMaximum());
        assertNotNull(openCart.getLastResult());
	}

	@Test
	public void testRewardAvailable() {
        Reward reward = new Reward(0, "50");
        reward.setSuccess("success");
        
        // build the request
        HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<MultiValueMap<String, String>>((MultiValueMap<String, String>) new LinkedMultiValueMap<String, String>(), OpenCart.createHeaders());
        
        Mockito.when(restTemplate.postForEntity(openCart.getApi() + "reward/available&api_token={apiToken}", request, Reward.class, openCart.login.getApiToken()))
        	.thenReturn(new ResponseEntity<Reward>(reward, HttpStatus.OK));
          
        assertEquals(reward.getPoints(), openCart.rewardAvailable());
        assertNotNull(openCart.getLastResult());
	}

	@Test
	public void testPaymentAddress() {
		Address address = new Address("Fred", "Bloggs", "123 The Street", "London", "UK", "NW6");
        Result result = Result.create("Success", null);
        
        MultiValueMap<String, String> map = new LinkedMultiValueMap<String, String>();
        map.add("firstname", address.getFirstname());
        map.add("lastname", address.getLastname());
        map.add("address_1", address.getAddress1());
        map.add("city", address.getCity());
        map.add("country_id", address.getCountryId());
        map.add("zone_id", address.getZoneId());
        
        // build the request
        HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<MultiValueMap<String, String>>(map, OpenCart.createHeaders());
        
        Mockito.when(restTemplate.postForEntity(openCart.getApi() + "payment/address&api_token={apiToken}", request, Result.class, openCart.login.getApiToken()))
        	.thenReturn(new ResponseEntity<Result>(result, HttpStatus.OK));
          
        assertTrue(openCart.paymentAddress(address));
        assertNotNull(openCart.getLastResult());
	}

	@Test
	public void testPaymentMethods() {
		PaymentMethods paymentMethods = new PaymentMethods();
		paymentMethods.setCod(new COD());
		paymentMethods.setFreeCheckout(new FreeCheckout());
		paymentMethods.setPpStandard(new PPStandard());

		PaymentMethodsWrapper expected = new PaymentMethodsWrapper();
		expected.setPaymentMethods(paymentMethods );
        
        // build the request
        HttpEntity<MultiValueMap<String, String>> request = 
        		new HttpEntity<MultiValueMap<String, String>>((MultiValueMap<String, String>) new LinkedMultiValueMap<String, String>(), OpenCart.createHeaders());
        
        Mockito.when(restTemplate.postForEntity(openCart.getApi() + "payment/methods&api_token={apiToken}", request, PaymentMethodsWrapper.class, openCart.login.getApiToken()))
        	.thenReturn(new ResponseEntity<PaymentMethodsWrapper>(expected, HttpStatus.OK));
          
        assertEquals(expected, openCart.paymentMethods());
        assertNotNull(openCart.getLastResult());
	}

	@Test
	public void testPaymentMethod() {
        String paymentMethod = "cod";
        Result result = Result.create("Success", null);
        
        MultiValueMap<String, String> map = new LinkedMultiValueMap<String, String>();
        map.add("payment_method", paymentMethod);
        
        // build the request
        HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<MultiValueMap<String, String>>(map, OpenCart.createHeaders());
        
        Mockito.when(restTemplate.postForEntity(openCart.getApi() + "payment/method&api_token={apiToken}", request, Result.class, openCart.login.getApiToken()))
        	.thenReturn(new ResponseEntity<Result>(result, HttpStatus.OK));
          
        assertTrue(openCart.paymentMethod(paymentMethod));
        assertNotNull(openCart.getLastResult());
	}

}
