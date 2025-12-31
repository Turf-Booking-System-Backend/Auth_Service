package com.faizan.turfbooking.authservice.service;

import com.faizan.turfbooking.authservice.dto.CreateUserRequest;
import com.faizan.turfbooking.authservice.dto.CreateUserResponse;
import com.faizan.turfbooking.authservice.dto.LoginRequest;

public interface UserService {
	public CreateUserResponse createUser(CreateUserRequest request);

	public String loginUser(LoginRequest request);
	

}
