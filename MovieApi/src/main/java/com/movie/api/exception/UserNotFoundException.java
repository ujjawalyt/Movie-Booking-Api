package com.movie.api.exception;

public class UserNotFoundException extends Exception {

	public UserNotFoundException() {
		
	}
	
	public UserNotFoundException(String message) {
		super(message);
	}
}
