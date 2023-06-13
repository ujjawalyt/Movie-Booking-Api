package com.movie.api.service;

import java.time.LocalDateTime;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.movie.api.dto.BookingDto;
import com.movie.api.dto.TransactionDto;
import com.movie.api.exception.AdminNotFoundException;
import com.movie.api.exception.BookingException;
import com.movie.api.exception.MovieNotFoundException;
import com.movie.api.exception.ScreenSeatNotFoundException;
import com.movie.api.exception.UserNotFoundException;
import com.movie.api.model.Booking;
import com.movie.api.model.Movie;
import com.movie.api.model.MovieCompanyWallet;
import com.movie.api.model.ScreenSeats;
import com.movie.api.model.Transactions;
import com.movie.api.model.User;
import com.movie.api.model.UserWallet;
import com.movie.api.repository.BookingRepo;
import com.movie.api.repository.CompanyWalletRepo;
import com.movie.api.repository.MovieRepo;
import com.movie.api.repository.ScreenSeatsRepo;
import com.movie.api.repository.TransactionsRepo;
import com.movie.api.repository.UserRepo;
import com.movie.api.repository.UserWalletRepo;

@Service
public class BookingServiceImpl  implements BookingService{


	@Autowired
	private MovieRepo movieRepo;

	@Autowired
	private ModelMapper modelMapper;

	@Autowired
	private UserRepo userRepo;
	@Autowired 
	private UserWalletRepo userWalletRepo;
	@Autowired
	private CompanyWalletRepo companyWalletRepo;
	@Autowired
	private ScreenSeatsRepo screenSeatsRepo;
	@Autowired
	private BookingRepo bookingRepo;
	@Autowired
	TransactionsRepo transactionsRepo;
	
	
	
	
	@Override
	public BookingDto bookYourMovie(BookingDto bookingDto, Integer screenSeatId, Integer movieId, Integer userId)
			throws BookingException, UserNotFoundException ,
			MovieNotFoundException,ScreenSeatNotFoundException{
		
		Optional<User> isUserPresent = userRepo.findById(userId);
		if (isUserPresent.isEmpty()) {
			throw new UserNotFoundException("User is not Present");
		}
		Optional<Movie> isMoviePresent = movieRepo.findById(movieId);
		if (isMoviePresent.isEmpty()) {
			throw new MovieNotFoundException("Movie is not present");
		}
		Optional<ScreenSeats> isScreenSeatPresent = screenSeatsRepo.findById(screenSeatId);
		if (isScreenSeatPresent.isEmpty()) {
			throw new ScreenSeatNotFoundException("Screen is not present");
		}

		int availableSeats = isScreenSeatPresent.get().getAvalSeats();
		int requestedSeats = bookingDto.getNoOfSeats();
		if (availableSeats < requestedSeats) {
			throw new BookingException("Not enough available seats");
		}
		Double bookingAmount = isScreenSeatPresent.get().getPrice();

		Double finalAmount = bookingAmount * requestedSeats;

		System.out.println(finalAmount);

		Booking booking = modelMapper.map(bookingDto, Booking.class);
		booking.setUser(isUserPresent.get());
		booking.setMovie(isMoviePresent.get());
		booking.setTheater(isMoviePresent.get().getTheaterName());
		booking.setTime(isMoviePresent.get().getShows());
		booking.setScreen(isScreenSeatPresent.get().getScreen());
		booking.setBookingAmount(finalAmount);
		booking.setCategory(isScreenSeatPresent.get().getCategory());
		booking.setBookingStatus("Pending");

		ScreenSeats screenSeats = isScreenSeatPresent.get();
//		screenSeats.setAvalSeats(availableSeats - requestedSeats);
		booking.setScreenSeats(screenSeats);
		screenSeatsRepo.save(screenSeats);
		Booking saved = bookingRepo.save(booking);
		return modelMapper.map(saved, BookingDto.class);
	}



	@Override
	public TransactionDto payBookingAmount(Integer bookingId, Integer userWalletId, Integer adminWalletId,
	                                       TransactionDto transactionDto) throws BookingException, UserNotFoundException, AdminNotFoundException {

	    Booking booking = bookingRepo.findById(bookingId)
	            .orElseThrow(() -> new BookingException("Wrong Booking ID entered."));

	    UserWallet userWallet = userWalletRepo.findById(userWalletId)
	            .orElseThrow(() -> new UserNotFoundException("Wrong information of user"));

	    MovieCompanyWallet companyWallet = companyWalletRepo.findById(adminWalletId)
	            .orElseThrow(() -> new AdminNotFoundException("Wrong information of admin"));

	    double bookingAmount = booking.getBookingAmount();
	    int noOfSeats = booking.getNoOfSeats();

	    if (!booking.getBookingStatus().equalsIgnoreCase("Pending")) {
	        throw new BookingException("Invalid booking status.");
	    }

	    if (userWallet.getBalance() < bookingAmount) {
	        throw new BookingException("Insufficient balance in user's wallet.");
	    }

	    double amount = userWallet.getBalance() - bookingAmount;
	    companyWallet.setBalance(companyWallet.getBalance() + amount);
	    userWallet.setBalance(userWallet.getBalance() - amount);
	    booking.setBookingStatus("Booked!!!");
	    ScreenSeats screenSeats = booking.getScreenSeats();
	    int updatedSeats = screenSeats.getAvalSeats() - noOfSeats;
	    screenSeats.setAvalSeats(updatedSeats);
	    screenSeatsRepo.save(screenSeats);
	    bookingRepo.save(booking);
	    companyWalletRepo.save(companyWallet);
	    userWalletRepo.save(userWallet);

	    Transactions transaction = modelMapper.map(transactionDto, Transactions.class);
	    transaction.setSenderWallet(userWallet);
	    transaction.setReceiverWallet(companyWallet);
	    transaction.setTimestamp(LocalDateTime.now());
	    transaction.setAmount(bookingAmount);
	    Transactions updateTransaction = transactionsRepo.save(transaction);

	    return modelMapper.map(updateTransaction, TransactionDto.class);
	}


	
	
	
}
