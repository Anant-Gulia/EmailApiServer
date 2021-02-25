package com.app.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class LogoutRequest {
	
	@JsonProperty(value = "email", required = true)
	private String email;
	@JsonProperty(value = "apiKey", required = true)
	private String apikey;

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getApikey() {
		return apikey;
	}

	public void setApikey(String apikey) {
		this.apikey = apikey;
	}

	public LogoutRequest(String email, String apikey) {
		super();
		this.email = email;
		this.apikey = apikey;
	}

	public LogoutRequest() {
		super();
	}

}
