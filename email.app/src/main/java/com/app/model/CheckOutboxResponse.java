package com.app.model;

import java.util.List;

import email.app.entity.EmailOutbox;

public class CheckOutboxResponse {
	private boolean error;
	private String errorMessage;
	private List<EmailOutbox> emails;

	public CheckOutboxResponse() {
		super();
		// TODO Auto-generated constructor stub
	}

	public CheckOutboxResponse(boolean error, String errorMessage, List<EmailOutbox> emails) {
		super();
		this.error = error;
		this.errorMessage = errorMessage;
		this.emails = emails;
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

	public List<EmailOutbox> getEmails() {
		return emails;
	}

	public void setEmails(List<EmailOutbox> emails) {
		this.emails = emails;
	}

}
