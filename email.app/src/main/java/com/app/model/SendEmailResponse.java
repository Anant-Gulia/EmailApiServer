package com.app.model;

public class SendEmailResponse {
	private boolean error;
	private String errorMessage;

	public SendEmailResponse() {
		super();
		// TODO Auto-generated constructor stub
	}

	public SendEmailResponse(boolean error, String errorMessage) {
		super();
		this.error = error;
		this.errorMessage = errorMessage;
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

}
