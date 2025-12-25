package com.faizan.turfbooking.authservice.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.faizan.turfbooking.authservice.dto.CreateUserRequest;
import com.faizan.turfbooking.authservice.dto.CreateUserResponse;
import com.faizan.turfbooking.authservice.service.UserService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/api")
@Slf4j
@RequiredArgsConstructor
public class HealthController {
	
	private final UserService userService;
	

	@GetMapping("/health")
	public String health() {
		
		return"auth service is running";
	}
	
	
	
	
	@PostMapping("/users")
	public CreateUserResponse createUser(@Valid @RequestBody CreateUserRequest request) {
		log.info("successfullly running ");
		CreateUserResponse response=	userService.createUser(request);
		log.info("created data {}", response);
		return response;
	}	
	


}