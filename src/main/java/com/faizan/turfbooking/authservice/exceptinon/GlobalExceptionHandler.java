package com.faizan.turfbooking.authservice.exceptinon;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.NoHandlerFoundException;

import com.faizan.turfbooking.authservice.constant.ErrorCodeEnum;
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
	// resource not found excepiton 
	@ExceptionHandler(NoHandlerFoundException.class)
	
	public ResponseEntity<ErrorResponse> handleNotFount(NoHandlerFoundException ex) {
		
		log.info("error handle 404 url not found ");
		
		ErrorResponse response = new ErrorResponse(
				ErrorCodeEnum.RESOURCE_NOT_FOUND.getErrorCode(),
				ErrorCodeEnum.RESOURCE_NOT_FOUND.getErrorMessage());
		
		return new ResponseEntity<>(response,HttpStatus.NOT_FOUND);
		
	}
	
	// handle generic exception
	@ExceptionHandler(Exception.class)
	public ResponseEntity<ErrorResponse> genericExceptionHandler(Exception ex) throws Exception{
		
		 // VERY IMPORTANT: let Spring Security handle auth errors
	    if (ex instanceof org.springframework.security.access.AccessDeniedException
	        || ex instanceof org.springframework.security.core.AuthenticationException) {
	        throw ex;
	    }

		
		ErrorResponse response = new ErrorResponse(
				"5000",
				"generic : some went wrong");
		
		log.info(" generic error handled ");
		
		return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
				
		
	}
	
	

}
