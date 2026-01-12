package com.faizan.turfbooking.authservice.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/vendor")	
public class VendorTestController {

	@PostMapping
	@PreAuthorize("hasRole('VENDOR')")
	public String vedorTest() {
		return " vendor test working ";
	}
}
