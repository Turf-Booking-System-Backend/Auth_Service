package com.faizan.turfbooking.authservice.exceptinon;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.faizan.turfbooking.authservice.dto.ErrorResponse;

import jakarta.validation.ConstraintViolationException;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
@ControllerAdvice
public class GlobalExceptionHandler {
	@ExceptionHandler(AuthException.class)
	public ResponseEntity<ErrorResponse> authExceptionHandler(AuthException ex) {
		
		ErrorResponse  message = new ErrorResponse(
				ex.getErrorCode(),
				ex.getErrorMessage()
				);
		
		log.info("error handled ");
		
		return new ResponseEntity<>(message, ex.getHttpStatus());
		
	
		
	}	  // dto validation 
	
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<ErrorResponse> handleMethodArgumentNotValid(
	        MethodArgumentNotValidException ex) {

	    String message = ex.getBindingResult()
	            .getFieldErrors()
	            .get(0)
	            .getDefaultMessage();

	    return new ResponseEntity<>(
	            new ErrorResponse("4000", message),
	            HttpStatus.BAD_REQUEST
	    );
	}

	
//	   url/ params validation error 
	@ExceptionHandler(ConstraintViolationException.class)
	public ResponseEntity<ErrorResponse> handleConstraintViolation(ConstraintViolationException ex) {
		
		String message = ex.getConstraintViolations().iterator().next().getMessage();
		
		ErrorResponse response= new ErrorResponse("4000", message);	
		log.info("error handled ");
		
		return new ResponseEntity<>(response,HttpStatus.BAD_REQUEST);
				
				
	}
	
	
	// handle generic exception
	@ExceptionHandler(Exception.class)
	public ResponseEntity<ErrorResponse> genericExceptionHandler(Exception ex) {
		
		ErrorResponse response = new ErrorResponse(
				"4000",
				"generic : some went wrong");
		
		log.info(" generic error handled ");
		
		return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
				
		
	}
	
	

}
