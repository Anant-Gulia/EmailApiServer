package com.app.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class GetEmailRequest {
	@JsonProperty(value = "email", required = true)
	private String email;
	@JsonProperty(value = "apiKey", required = true)
	private String apiKey;

	public GetEmailRequest() {
		super();
		// TODO Auto-generated constructor stub
	}

	public GetEmailRequest(String email, String apiKey) {
		super();
		this.email = email;
		this.apiKey = apiKey;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getApiKey() {
		return apiKey;
	}

	public void setApiKey(String apiKey) {
		this.apiKey = apiKey;
	}

}
