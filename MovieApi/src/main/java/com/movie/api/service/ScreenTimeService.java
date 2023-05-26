package com.movie.api.service;

import org.springframework.stereotype.Service;

import com.movie.api.dto.ScreenTimeDto;
import com.movie.api.exception.ManagerNotFoundException;
import com.movie.api.exception.ScreenTimeNotFoundException;
import com.movie.api.exception.TheaterNotFoundException;

@Service
public interface ScreenTimeService {

	public ScreenTimeDto addScreenTime(ScreenTimeDto screenTimeDto , Integer managerId, Integer theaterId)
	throws ManagerNotFoundException, TheaterNotFoundException, ScreenTimeNotFoundException;
}
