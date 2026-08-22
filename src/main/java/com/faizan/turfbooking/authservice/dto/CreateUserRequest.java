package com.faizan.turfbooking.authservice.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class CreateUserRequest {
	
	@NotBlank(message = "Name is Required")
	private String name;
	
	@NotBlank(message = "Email is Required")
	@Pattern(regexp = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$",
	message = "Invalid email format, Temporary Email is Not Accepted")
	private String email;
	
	
	@NotBlank(message = "Password is Required")
	@Size(min = 8, message = "Password must be at least 8 characters long")
	@Pattern(regexp= "^\\S+$", message = "Password cannot contain spaces")
	private String password;
	

}
