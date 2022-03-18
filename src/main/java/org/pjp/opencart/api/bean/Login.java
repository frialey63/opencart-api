package org.pjp.opencart.api.bean;

import com.fasterxml.jackson.annotation.JsonProperty;

public final class Login extends Result {

    @JsonProperty("api_token")
    private String apiToken;

    public Login(String apiToken) {
		super();
		success = "Success";
		this.apiToken = apiToken;
	}

	public String getApiToken() {
        return apiToken;
    }

    public void setApiToken(String apiToken) {
        this.apiToken = apiToken;
    }

    @Override
    public String toString() {
        return "Login [apiToken=" + apiToken + ", toString()=" + super.toString() + "]";
    }

}
