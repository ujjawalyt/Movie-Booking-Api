package com.movie.api.exception;

import java.time.LocalDate;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

@ControllerAdvice
public class GlobalExceptionHandler {

	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<MyErrorDetails> myMANVExceptionHandler(MethodArgumentNotValidException me){
		
		MyErrorDetails error = new MyErrorDetails();
		error.setTimestamp(LocalDate.now());
		error.setMessage("Validation Error..!");
		error.setDescription(me.getBindingResult().getFieldError().getDefaultMessage());
		
		return new ResponseEntity<MyErrorDetails>(error,HttpStatus.BAD_REQUEST);
		
	}
	
	@ExceptionHandler(AdminNotFoundException.class)
	public ResponseEntity<MyErrorDetails> myADNFExceptionHandler(AdminNotFoundException ad ,WebRequest wr){
		
		MyErrorDetails error = new MyErrorDetails();
		error.setTimestamp(LocalDate.now());
		error.setMessage(ad.getMessage());
		error.setDescription(wr.getDescription(false));
		
		return new ResponseEntity<MyErrorDetails>(error,HttpStatus.BAD_REQUEST);
		
	}
	
	
	@ExceptionHandler(RoleNotFoundException.class)
	public ResponseEntity<MyErrorDetails> myRNFEExceptionHandler( RoleNotFoundException ro ,WebRequest wr){
		
		MyErrorDetails error = new MyErrorDetails();
		error.setTimestamp(LocalDate.now());
		error.setMessage(ro.getMessage());
		error.setDescription(wr.getDescription(false));
		
		return new ResponseEntity<MyErrorDetails>(error,HttpStatus.BAD_REQUEST);
		
	}
	
	@ExceptionHandler(UserNotFoundException.class)
	public ResponseEntity<MyErrorDetails> myRNFEExceptionHandler(UserNotFoundException un ,WebRequest wr){
		
		MyErrorDetails error = new MyErrorDetails();
		error.setTimestamp(LocalDate.now());
		error.setMessage(un.getMessage());
		error.setDescription(wr.getDescription(false));
		
		return new ResponseEntity<MyErrorDetails>(error,HttpStatus.BAD_REQUEST);
		
	}
	
	@ExceptionHandler(ManagerNotFoundException.class)
	public ResponseEntity<MyErrorDetails> myMNFExceptionHandler(ManagerNotFoundException mnfe ,WebRequest wr){
		
		MyErrorDetails error = new MyErrorDetails();
		error.setTimestamp(LocalDate.now());
		error.setMessage(mnfe.getMessage());
		error.setDescription(wr.getDescription(false));
		
		return new ResponseEntity<MyErrorDetails>(error,HttpStatus.BAD_REQUEST);
		
	}
	
	
	@ExceptionHandler(TheaterNotFoundException.class)
	public ResponseEntity<MyErrorDetails> myTNFExceptionHandler(TheaterNotFoundException tnfe ,WebRequest wr){
		
		MyErrorDetails error = new MyErrorDetails();
		error.setTimestamp(LocalDate.now());
		error.setMessage(tnfe.getMessage());
		error.setDescription(wr.getDescription(false));
		
		return new ResponseEntity<MyErrorDetails>(error,HttpStatus.BAD_REQUEST);
		
	}
	
}
