package com.movie.api.service;

import org.springframework.stereotype.Service;

import com.movie.api.dto.MovieDto;
import com.movie.api.exception.ManagerNotFoundException;
import com.movie.api.exception.ScreenSeatNotFoundException;
import com.movie.api.exception.ScreenTimeNotFoundException;
import com.movie.api.exception.TheaterNotFoundException;

@Service
public interface MovieService {

	public MovieDto addMovie(MovieDto movieDto, Integer managerId, Integer theaterId, Integer screenTimeId,
			Integer screenSeatId) throws ManagerNotFoundException, TheaterNotFoundException,
			ScreenTimeNotFoundException, ScreenSeatNotFoundException;
}
