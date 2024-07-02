package com.divya.ecommerce.handler;

import java.util.HashMap;

import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.divya.ecommerce.exception.ProductPurchaseException;

import jakarta.persistence.EntityNotFoundException;

@RestControllerAdvice
public class GlobalExceptionHandler {
	
	@ExceptionHandler(ProductPurchaseException.class)
	public ResponseEntity<String> handle(ProductPurchaseException exp)
	{
		return ResponseEntity
			   .status(HttpStatus.NOT_FOUND)
			   .body(exp.getMessage());
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
	
	@ExceptionHandler(EntityNotFoundException.class)
	public ResponseEntity<String> handle(EntityNotFoundException exp)
	{
		return ResponseEntity
			   .status(HttpStatus.NOT_FOUND)
			   .body(exp.getMessage());
	}
}
