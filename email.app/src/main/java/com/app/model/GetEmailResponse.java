package com.app.model;

import java.util.List;

import email.app.entity.EmailInbox;

public class GetEmailResponse {
	private boolean error;
	private String errorMessage;
	private List<EmailInbox> emails;

	public GetEmailResponse() {
		super();
		// TODO Auto-generated constructor stub
	}

	public GetEmailResponse(boolean error, String errorMessage, List<EmailInbox> emails) {
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

	public List<EmailInbox> getEmails() {
		return emails;
	}

	public void setEmails(List<EmailInbox> emails) {
		this.emails = emails;
	}

}
