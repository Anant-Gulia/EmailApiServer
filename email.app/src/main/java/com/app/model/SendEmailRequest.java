package com.app.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class SendEmailRequest {
	@JsonProperty(value = "sendersEmail", required = true)
	private String sendersEmail;
	@JsonProperty(value = "receiversEmail", required = true)
	private String receiversEmail;
	@JsonProperty(value = "apiKey", required = true)
	private String apiKey;
	@JsonProperty(value = "body", required = true)
	private String body;

	public SendEmailRequest() {
		super();
		// TODO Auto-generated constructor stub
	}

	public SendEmailRequest(String sendersEmail, String receiversEmail, String apiKey, String body) {
		super();
		this.sendersEmail = sendersEmail;
		this.receiversEmail = receiversEmail;
		this.apiKey = apiKey;
		this.body = body;
	}

	public String getSendersEmail() {
		return sendersEmail;
	}

	public void setSendersEmail(String sendersEmail) {
		this.sendersEmail = sendersEmail;
	}

	public String getReceiversEmail() {
		return receiversEmail;
	}

	public void setReceiversEmail(String receiversEmail) {
		this.receiversEmail = receiversEmail;
	}

	public String getApiKey() {
		return apiKey;
	}

	public void setApiKey(String apiKey) {
		this.apiKey = apiKey;
	}

	public String getBody() {
		return body;
	}

	public void setBody(String body) {
		this.body = body;
	}

}
