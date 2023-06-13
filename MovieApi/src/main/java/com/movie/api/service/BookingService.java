package com.movie.api.service;

import com.movie.api.dto.BookingDto;
import com.movie.api.dto.TransactionDto;
import com.movie.api.exception.AdminNotFoundException;
import com.movie.api.exception.BookingException;
import com.movie.api.exception.MovieNotFoundException;
import com.movie.api.exception.ScreenSeatNotFoundException;
import com.movie.api.exception.UserNotFoundException;

public interface BookingService {
	public BookingDto bookYourMovie(BookingDto bookingDto,Integer screenSeatId,Integer movieId, Integer userId) 
			throws BookingException,UserNotFoundException, MovieNotFoundException,ScreenSeatNotFoundException; 

	
	public TransactionDto payBookingAmount(Integer bookingId,Integer userWalletId,Integer adminWalletId,TransactionDto transactionDto) 
			throws
	BookingException,UserNotFoundException,AdminNotFoundException;


}
