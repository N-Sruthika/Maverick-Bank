package com.example.mb.config;

import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatusCode;
import org.springframework.web.ErrorResponse;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.example.mb.exception.InvalidIdException;
import com.example.mb.exception.InvalidUsernameException;

@RestControllerAdvice
public class GlobalExceptionHandlerConfig {
	Logger logger =  LoggerFactory.getLogger("GlobalExceptionHandlerConfig"); 
	 

	 @ExceptionHandler(InvalidIdException.class)
	 public ErrorResponse invalidIDExceptionHandler(InvalidIdException e) {
		 logger.error("Invalid ID given " + e.getMessage());
		 return ErrorResponse.create
				 			(e, 
				 			HttpStatusCode.valueOf(400), 
				 			e.getMessage()); 
	 }
	 
	 @ExceptionHandler(InvalidUsernameException.class)
	 public ErrorResponse invalidUsernameExceptionHandler(InvalidUsernameException e) {
		 return ErrorResponse.create
				 			(e, 
				 			HttpStatusCode.valueOf(400), 
				 			e.getMessage()); 
	 }
	 
	 @ExceptionHandler(RuntimeException.class)
	 public ErrorResponse invalidImageExceptionHandler(RuntimeException e) {
		 return ErrorResponse.create
				 			(e, 
				 			HttpStatusCode.valueOf(400), 
				 			e.getMessage()); 
	 }
	 
	 @ExceptionHandler(IOException.class)
	 public ErrorResponse invalidIOExceptionHandler(IOException e) {
		 return ErrorResponse.create
				 			(e, 
				 			HttpStatusCode.valueOf(400), 
				 			e.getMessage()); 
	 }
	 @ExceptionHandler(Exception.class)
	 public ErrorResponse exceptionHandler(Exception e) {
		 return ErrorResponse.create
				 			(e, 
				 			HttpStatusCode.valueOf(400), 
				 			e.getMessage()); 
	 }
}