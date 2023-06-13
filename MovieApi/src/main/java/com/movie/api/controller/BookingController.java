package com.movie.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.movie.api.dto.BookingDto;
import com.movie.api.dto.TransactionDto;
import com.movie.api.exception.AdminNotFoundException;
import com.movie.api.exception.BookingException;
import com.movie.api.exception.MovieNotFoundException;
import com.movie.api.exception.ScreenSeatNotFoundException;
import com.movie.api.exception.UserNotFoundException;
import com.movie.api.service.BookingService;

import io.swagger.v3.oas.annotations.parameters.RequestBody;

@RestController
@RequestMapping("/booking")
public class BookingController {

	@Autowired
	private BookingService bookingService;

	@PostMapping("/movie/{movieId}/screenSeat/{seatId}/User/{userId}")
	public ResponseEntity<BookingDto> bookMovie(@RequestBody BookingDto bookingDto,
			@PathVariable("movieId") Integer movieId, @PathVariable("seatId") Integer seatId,
			@PathVariable("userId") Integer userId)
			throws BookingException, UserNotFoundException, MovieNotFoundException, ScreenSeatNotFoundException {
		return new ResponseEntity<BookingDto>(bookingService.bookYourMovie(bookingDto, seatId, movieId, userId),
				HttpStatus.CREATED);
	}

	
	@PostMapping("/payBookingAmount/{bookingId}/{userWalletId}/{adminWalletId}")
	  public ResponseEntity<TransactionDto> payBookingAmount(
	      @PathVariable Integer bookingId,
	      @PathVariable Integer userWalletId,
	      @PathVariable Integer adminWalletId,
	      @RequestBody TransactionDto transactionDto
	  ) throws BookingException, UserNotFoundException, AdminNotFoundException {
	      return new ResponseEntity<>(
	          bookingService.payBookingAmount(bookingId, userWalletId, adminWalletId, transactionDto),
	          HttpStatus.OK
	      );
	  }

}
