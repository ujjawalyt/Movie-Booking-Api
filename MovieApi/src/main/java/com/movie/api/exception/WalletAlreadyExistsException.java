package com.movie.api.exception;

public class WalletAlreadyExistsException extends Exception {

	public WalletAlreadyExistsException() {
		
	}
	public WalletAlreadyExistsException(String message) {
		super(message);
	}
}
