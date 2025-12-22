package com.faizan.turfbooking.authservice.service;

import com.faizan.turfbooking.authservice.dto.CreateUserRequest;
import com.faizan.turfbooking.authservice.dto.CreateUserResponse;

public interface UserService {
	public CreateUserResponse createUser(CreateUserRequest request);

}
