package com.movie.api.exception;

public class WalletNotFoundException extends Exception {

	public WalletNotFoundException() {
		
	}
	
	public WalletNotFoundException(String message) {
		super(message);
	}
}
