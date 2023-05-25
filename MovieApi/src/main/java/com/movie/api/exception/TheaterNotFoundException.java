package com.movie.api.exception;

public class TheaterNotFoundException extends Exception {

	
	public TheaterNotFoundException() {
		
	}
	public TheaterNotFoundException(String message) {
		super(message);
	}
}
