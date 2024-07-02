package com.divya.ecommerce.handler;

import java.util.HashMap;

import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.divya.ecommerce.customer.CustomerNotFoundException;

@RestControllerAdvice
public class GlobalExceptionHandler {
	
	@ExceptionHandler(CustomerNotFoundException.class)
	public ResponseEntity<String> handle(CustomerNotFoundException exp)
	{
		return ResponseEntity
			   .status(HttpStatus.NOT_FOUND)
			   .body(exp.getMsg());
	}
    
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<ErrorResponse> handle(MethodArgumentNotValidException exp)
	{
		var errors= new HashMap<String,String>();
		
		exp.getBindingResult().getAllErrors()
		.forEach(error->{
		   var fieldname= ((FieldError)error).getField();
		   var msg= error.getDefaultMessage();
		   errors.put(fieldname,msg);
		});
		return ResponseEntity
			   .status(HttpStatus.NOT_FOUND)
			   .body(new ErrorResponse(errors));
	}
}
