package com.faizan.turfbooking.authservice.service;

import com.faizan.turfbooking.authservice.dto.CreateUserRequest;
import com.faizan.turfbooking.authservice.dto.CreateUserResponse;
import com.faizan.turfbooking.authservice.dto.LoginRequest;
import com.faizan.turfbooking.authservice.dto.LoginResponse;

public interface UserService {
	public CreateUserResponse createUser(CreateUserRequest request);

	public LoginResponse loginUser(LoginRequest request);
	

}
