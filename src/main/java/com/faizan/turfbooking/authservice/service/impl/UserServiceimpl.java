package com.faizan.turfbooking.authservice.service.impl;


import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import com.faizan.turfbooking.authservice.dto.CreateUserRequest;
import com.faizan.turfbooking.authservice.dto.CreateUserResponse;
import com.faizan.turfbooking.authservice.entity.User;
import com.faizan.turfbooking.authservice.repository.UserRepository;
import com.faizan.turfbooking.authservice.service.UserService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
@RequiredArgsConstructor
public class UserServiceimpl implements UserService {
	
	private final ModelMapper modelMapper;
	
	private final UserRepository userRepository;
	
	public CreateUserResponse createUser(CreateUserRequest request) {
		
		
		// 1. Convert incoming JSON to a Database object
		User user = modelMapper.map(request, User.class); 

		// 2. Save it (This is when the ID is created)
		User savedUser = userRepository.save(user); 

		// 3. NOW you map to the response so the ID is included!
		CreateUserResponse response = modelMapper.map(savedUser, CreateUserResponse.class);
		
		log.info("user created successfully");
		return response;
		
		
		
	}

}
