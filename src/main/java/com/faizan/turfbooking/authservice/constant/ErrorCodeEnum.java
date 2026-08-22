package com.faizan.turfbooking.authservice.constant;

import lombok.Getter;

@Getter
public enum ErrorCodeEnum {
	
		GENERIC_ERROR("4001","SOMETHING WENT WRONT!.."),
		WRONG_INPUT("4002","WRONG INPUT!.."),
		EMAIL_VALIDATION_ERROR("4003","Email Already Exists.. Try Another Email.."),
		FIELD_VALIDATION_ERROR("4004","Email or Passwored is Missing, check the fields and try again."),
		PASSWORD_VALIDATION_ERROR("4005","Password Must be Greater than 8 characters "),
		INVALID_EMAIL("4006","Invalid Email. Please Enter Valid Email"),
		EMAIL_NOT_FOUND("4006","Email Not Found"),
		RESOURCE_NOT_FOUND("4040","Invalid url or Resource Not Found"),
		LOGIN_ERROR("4007","Invalid Email or Password.");
	private String errorCode;
	private String errorMessage;
	
	private ErrorCodeEnum(String errorCode, String errorMessage) {
		
		this.errorCode = errorCode;
		this.errorMessage = errorMessage;
	}
}
