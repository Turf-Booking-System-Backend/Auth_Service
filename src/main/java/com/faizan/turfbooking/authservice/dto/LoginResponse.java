package com.faizan.turfbooking.authservice.dto;

import lombok.Data;

@Data

public class LoginResponse {

	private String token;
	private String type;
	
	public LoginResponse(String token) {
		this.token = token;
		this.type = "Bearer";
	
	}
}
