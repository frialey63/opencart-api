package org.pjp.opencart.api;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import org.pjp.opencart.api.bean.Address;
import org.pjp.opencart.api.bean.Customer;
import org.pjp.opencart.api.bean.Login;
import org.pjp.opencart.api.bean.Result;
import org.pjp.opencart.api.bean.Reward;
import org.pjp.opencart.api.bean.Voucher;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
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
          
        assertTrue(openCart.login(username, key));
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
	}

	@Test
	public void testShippingMethods() {
		fail("Not yet implemented");
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
	}

	@Test
	public void testRewardMaximum() {
        Reward reward = new Reward(100, null);
        reward.setSuccess("success");
        
        MultiValueMap<String, String> map = new LinkedMultiValueMap<String, String>();
        
        // build the request
        HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<MultiValueMap<String, String>>(map, OpenCart.createHeaders());
        
        Mockito.when(restTemplate.postForEntity(openCart.getApi() + "reward/maximum&api_token={apiToken}", request, Reward.class, openCart.login.getApiToken()))
        	.thenReturn(new ResponseEntity<Reward>(reward, HttpStatus.OK));
          
        assertEquals(reward.getMaximum(), openCart.rewardMaximum());
	}

	@Test
	public void testRewardAvailable() {
        Reward reward = new Reward(0, "50");
        reward.setSuccess("success");
        
        MultiValueMap<String, String> map = new LinkedMultiValueMap<String, String>();
        
        // build the request
        HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<MultiValueMap<String, String>>(map, OpenCart.createHeaders());
        
        Mockito.when(restTemplate.postForEntity(openCart.getApi() + "reward/available&api_token={apiToken}", request, Reward.class, openCart.login.getApiToken()))
        	.thenReturn(new ResponseEntity<Reward>(reward, HttpStatus.OK));
          
        assertEquals(reward.getPoints(), openCart.rewardAvailable());
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
	}

	@Test
	public void testPaymentMethods() {
		fail("Not yet implemented");
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
	}

}
