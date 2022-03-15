package org.pjp.opencart.api;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import org.pjp.opencart.api.bean.Login;
import org.pjp.opencart.api.bean.Result;
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
		fail("Not yet implemented");
	}

	@Test
	public void testCurrency() {
		fail("Not yet implemented");
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
		fail("Not yet implemented");
	}

	@Test
	public void testVoucher() {
		fail("Not yet implemented");
	}

	@Test
	public void testAddVoucher() {
		fail("Not yet implemented");
	}

	@Test
	public void testShippingAddress() {
		fail("Not yet implemented");
	}

	@Test
	public void testShippingMethods() {
		fail("Not yet implemented");
	}

	@Test
	public void testShippingMethod() {
		fail("Not yet implemented");
	}

	@Test
	public void testReward() {
		fail("Not yet implemented");
	}

	@Test
	public void testRewardMaximum() {
		fail("Not yet implemented");
	}

	@Test
	public void testRewardAvailable() {
		fail("Not yet implemented");
	}

	@Test
	public void testPaymentAddress() {
		fail("Not yet implemented");
	}

	@Test
	public void testPaymentMethods() {
		fail("Not yet implemented");
	}

	@Test
	public void testPaymentMethod() {
		fail("Not yet implemented");
	}

}
