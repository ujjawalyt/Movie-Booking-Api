package com.movie.api.service;

import org.springframework.stereotype.Service;

import com.movie.api.dto.ScreenSeatsDto;
import com.movie.api.exception.ManagerNotFoundException;
import com.movie.api.exception.ScreenTimeNotFoundException;
import com.movie.api.exception.TheaterNotFoundException;

@Service
public interface ScreenSeatService {

	public ScreenSeatsDto addSeatsToScreen(ScreenSeatsDto screenSeatsDto, Integer managerId, Integer theaterId,
			Integer screenTimeId) throws ManagerNotFoundException, 
	TheaterNotFoundException ,ScreenTimeNotFoundException;
}
