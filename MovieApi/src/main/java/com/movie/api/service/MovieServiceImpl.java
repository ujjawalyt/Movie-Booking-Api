package com.movie.api.service;

import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.movie.api.Enum.ShowTime;
import com.movie.api.dto.MovieDto;
import com.movie.api.exception.ManagerNotFoundException;
import com.movie.api.exception.ScreenSeatNotFoundException;
import com.movie.api.exception.ScreenTimeNotFoundException;
import com.movie.api.exception.TheaterNotFoundException;
import com.movie.api.model.Manager;
import com.movie.api.model.Movie;
import com.movie.api.model.ScreenSeats;
import com.movie.api.model.ScreenTime;
import com.movie.api.model.Theater;
import com.movie.api.repository.ManagerRepo;
import com.movie.api.repository.MovieRepo;
import com.movie.api.repository.ScreenSeatsRepo;
import com.movie.api.repository.ScreenTimeRepo;
import com.movie.api.repository.TheaterRepo;

@Service
public class MovieServiceImpl implements MovieService{

	@Autowired
	private MovieRepo movieRepo;
	@Autowired
	private ScreenTimeRepo screenTimeRepo;
	@Autowired
	private ScreenSeatsRepo screenSeatsRepo;
	@Autowired
	private TheaterRepo theaterRepo;
	@Autowired
	private ModelMapper modelMapper;
	@Autowired
	private ManagerRepo managerRepo;
	
	
	@Override
	public MovieDto addMovie(MovieDto movieDto, Integer managerId, Integer theaterId, Integer screenTimeId,
			Integer screenSeatId) throws ManagerNotFoundException, TheaterNotFoundException,
			ScreenTimeNotFoundException, ScreenSeatNotFoundException {
		
		
		Optional<Manager> manager = managerRepo.findById(managerId);
		if (manager.isEmpty()) {
			throw new ManagerNotFoundException("Manager not found with this manager id" + managerId);
		}
		Optional<Theater> theater = theaterRepo.findById(theaterId);
		if (theater.isEmpty()) {
			throw new TheaterNotFoundException("Theater not found with this theater id" + theaterId);
		}

		Optional<ScreenTime> screenTimeOptional = screenTimeRepo.findById(screenTimeId);
		if (screenTimeOptional.isEmpty()) {
			throw new ScreenTimeNotFoundException("ScreenTime not found with this screen id" + screenTimeId);
		}

		Optional<ScreenSeats> screenSeatOptional = screenSeatsRepo.findById(screenSeatId);
		if (screenSeatOptional.isEmpty()) {
			throw new ScreenSeatNotFoundException("ScreenSeat not found with this screen id" + screenSeatId);
		}
		
		
		
		if(manager.get().getManagerId().equals(theater.get().getManager().getManagerId()) 
				&& screenSeatOptional.get().getManager().getManagerId().equals(screenTimeOptional.get().getManager().getManagerId())
				&& screenSeatOptional.get().getScreen().equals(screenTimeOptional.get().getScreen())
				) {

			Optional<Movie> moviee=movieRepo.findByMovieNameAndShows(movieDto.getMovieName(),screenTimeOptional.get().getShowTime().getDisplayName());
			if(moviee.isPresent()) {
				throw new TheaterNotFoundException("Already set this movie in this show");
			}
					
			Movie movie =modelMapper.map(movieDto, Movie.class);
			movie.setManager(manager.get());
			movie.setTheater(theater.get());
			movie.setTheaterName(theater.get().getTheaterName());
			movie.setScreen(screenSeatOptional.get().getScreen());
			movie.setShows(screenTimeOptional.get().getShowTime().getDisplayName());
			
			Movie saved=movieRepo.save(movie);
			return modelMapper.map(saved, movieDto.getClass());
	
}

			else {
				throw new TheaterNotFoundException("Manager is not matched with one this enetiy");
			}

		
	}
	
}
