package com.faizan.turfbooking.authservice.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/api/admin")
@RestController
public class AdminTestController {
	
	@PostMapping
	@PreAuthorize("hasRole('ADMIN')")
	public String admin() {
		return "admin working ";
	}

}
