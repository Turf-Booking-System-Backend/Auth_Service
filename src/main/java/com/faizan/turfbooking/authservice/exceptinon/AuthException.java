package com.faizan.turfbooking.authservice.exceptinon;

import org.springframework.http.HttpStatus;

import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class AuthException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private final String errorCode;
	private final String errorMessage;
	private final HttpStatus httpStatus;
	
	public AuthException(String errorCode, String errorMessage, HttpStatus httpStatus) {
		super(errorMessage);
		this.errorCode = errorCode;
		this.errorMessage = errorMessage;
		this.httpStatus = httpStatus;
		
	}
}
