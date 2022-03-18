package org.pjp.opencart.api.bean;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Bean to represent a login.
 * @author developer
 *
 */
public class Login extends Result {

    @JsonProperty("api_token")
    private String apiToken;

    /**
     * Default constructor required by Jackson.
     */
    public Login() {
        super();
    }

    /**
     * @param apiToken API token for the user
     */
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
