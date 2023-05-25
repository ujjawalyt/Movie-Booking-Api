package com.movie.api.service;

import org.springframework.stereotype.Service;

import com.movie.api.dto.TheaterDto;
import com.movie.api.exception.ManagerNotFoundException;

@Service
public interface TheaterService {

	public TheaterDto addTheater(TheaterDto theaterDto , Integer managerId) throws ManagerNotFoundException;
}
