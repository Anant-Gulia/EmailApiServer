package com.app.model;

public class LoginResponse {

	private String apikey;
	private boolean error;
	private String errorMessage;

	public String getApikey() {
		return apikey;
	}

	public void setApikey(String apikey) {
		this.apikey = apikey;
	}

	public boolean isError() {
		return error;
	}

	public void setError(boolean error) {
		this.error = error;
	}

	public String getErrorMessage() {
		return errorMessage;
	}

	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}

	public LoginResponse() {
		super();
	}

	public LoginResponse(String apikey, boolean error, String errorMessage) {
		super();
		this.apikey = apikey;
		this.error = error;
		this.errorMessage = errorMessage;
	}
}
