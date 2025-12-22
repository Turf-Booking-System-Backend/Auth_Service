package com.faizan.turfbooking.authservice.dto;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import lombok.Data;

@Data

@JsonPropertyOrder({ "id", "name", "email" })
public class CreateUserResponse {
	private Long id;
	private String name;
	private String email;
	
	

}
