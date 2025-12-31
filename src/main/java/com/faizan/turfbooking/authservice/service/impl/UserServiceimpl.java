package com.faizan.turfbooking.authservice.service.impl;


import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.faizan.turfbooking.authservice.constant.ErrorCodeEnum;
import com.faizan.turfbooking.authservice.dto.CreateUserRequest;
import com.faizan.turfbooking.authservice.dto.CreateUserResponse;
import com.faizan.turfbooking.authservice.dto.LoginRequest;
import com.faizan.turfbooking.authservice.entity.User;
import com.faizan.turfbooking.authservice.enums.Role;
import com.faizan.turfbooking.authservice.exceptinon.AuthException;
import com.faizan.turfbooking.authservice.repository.UserRepository;
import com.faizan.turfbooking.authservice.service.UserService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
@RequiredArgsConstructor
public class UserServiceimpl implements UserService {
	
	private final ModelMapper modelMapper;
	
	private final BCryptPasswordEncoder passwordEncoder;
	
	private final UserRepository userRepository;
	
	public CreateUserResponse createUser(CreateUserRequest request) {
		
		
//		 Validation
//		 i can also use Jakarta Bean Validation.
		log.info("Creating user: {}", request);
//		if(request.getEmail()==null|| request.getEmail().trim().isEmpty() || 
//			request.getPassword()==null|| request.getPassword().trim().isEmpty()) {
//
//			throw new AuthException(
//					ErrorCodeEnum.FIELD_VALIDATION_ERROR.getErrorCode(),
//					ErrorCodeEnum.FIELD_VALIDATION_ERROR.getErrorMessage(),
//					HttpStatus.BAD_REQUEST);
//	}		
		
		// check if email already exists		
		if(userRepository.existsByEmail(request.getEmail())) {
			log.info("Email already exists");
		
			throw new AuthException(
					ErrorCodeEnum.EMAIL_VALIDATION_ERROR.getErrorCode(),
					ErrorCodeEnum.EMAIL_VALIDATION_ERROR.getErrorMessage(),
					HttpStatus.CONFLICT);
	}
			
	
		
		// 1. Convert incoming JSON to a Database object
		User user = modelMapper.map(request, User.class);
		
		// password encryption
		user.setPassword(passwordEncoder.encode(request.getPassword()));
		
		user.setRole(Role.USER);
		
		// 2. Save it This is when the ID is created
		User savedUser = userRepository.save(user); 

		// 3. NOW map to the response so the ID is included!
		CreateUserResponse response = modelMapper.map(savedUser, CreateUserResponse.class);
		
		log.info("user created successfully");
		return response;
		
		
			
	}
	
	
	public String loginUser(LoginRequest request) {
		
		log.info("login user info {}", request);
		
		// can also done using jakarta validation
		if(request.getEmail()==null || request.getEmail().trim().isEmpty()
				||request.getPassword()==null || request.getPassword().trim().isEmpty()) {

			throw new AuthException(
					ErrorCodeEnum.FIELD_VALIDATION_ERROR.getErrorCode(),
					ErrorCodeEnum.FIELD_VALIDATION_ERROR.getErrorMessage(),
					HttpStatus.BAD_REQUEST);
		}
		
		User user = userRepository.findByEmail(request.getEmail())
				.orElseThrow(()-> new AuthException(
					
						ErrorCodeEnum.LOGIN_ERROR.getErrorCode(),
						ErrorCodeEnum.LOGIN_ERROR.getErrorMessage(),
						HttpStatus.UNAUTHORIZED
				));
						
				
		if(!passwordEncoder.matches(request.getPassword(), user.getPassword())) {
			 throw new AuthException(
				 	ErrorCodeEnum.LOGIN_ERROR.getErrorCode(),
					ErrorCodeEnum.LOGIN_ERROR.getErrorMessage(),
					HttpStatus.UNAUTHORIZED
			 );
		}	
				
		return "login successfull";
	}

}
