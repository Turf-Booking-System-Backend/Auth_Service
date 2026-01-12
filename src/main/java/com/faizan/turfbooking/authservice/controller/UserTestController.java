package com.faizan.turfbooking.authservice.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/user")	
public class UserTestController {
	
	@PostMapping
	@PreAuthorize("hasRole('USER')")
	public String userTest() {
		
		return" user role test working ";
	}

}
