package com.ishaque.coaching.microservices.controlller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.ishaque.coaching.microservices.exception.UserNotFoundException;


@ControllerAdvice
public class ExceptionController {

	// For UI Pages
	@ExceptionHandler(UserNotFoundException.class)
	public String userNotFoundException(UserNotFoundException ex) {
		return "error";
	}


}