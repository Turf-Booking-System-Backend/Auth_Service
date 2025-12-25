package com.faizan.turfbooking.authservice.entity;

import java.time.LocalDateTime;

import org.hibernate.annotations.CreationTimestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Entity
@Table(name="user")
@Data
public class User {
	@Id	
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotBlank( message = "Name is required")
	private String name;
	
	//@NotBlank(message = "Email is required")
	@Column(unique=true)
	private String email;
	
	//@NotBlank(message = "password is required")
	@Column(nullable = false)
    private String password;
	
	

	private String role;
	
	@CreationTimestamp // Automatically sets the time when a record is created
    @Column(updatable = false)
    private LocalDateTime createdAt;

	

}
