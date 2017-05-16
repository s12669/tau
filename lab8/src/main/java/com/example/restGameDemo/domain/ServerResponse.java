package com.example.restGameDemo.domain;

import org.springframework.http.HttpStatus;

public class ServerResponse {

	private HttpStatus status;
	private String description;
	
	public HttpStatus getStatus() {
		return status;
	}
	public void setStatus(HttpStatus status) {
		this.status = status;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	
	public ServerResponse(HttpStatus status, String description) {
		super();
		this.status = status;
		this.description = description;
	}
	
	
}
