package com.movie.api.service;

import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.movie.api.dto.ScreenSeatsDto;
import com.movie.api.exception.ManagerNotFoundException;
import com.movie.api.exception.TheaterNotFoundException;
import com.movie.api.model.Manager;
import com.movie.api.model.ScreenSeats;
import com.movie.api.model.Theater;
import com.movie.api.repository.ManagerRepo;
import com.movie.api.repository.ScreenSeatsRepo;
import com.movie.api.repository.ScreenTimeRepo;
import com.movie.api.repository.TheaterRepo;

@Service
public class ScreenSeatServiceImpl implements ScreenSeatService {

	@Autowired
	private ModelMapper modelMapper;
	@Autowired
	private ScreenSeatsRepo screenSeatsRepo;

	@Autowired
	private ManagerRepo managerRepo;

	@Autowired
	private TheaterRepo theaterRepo;

	@Override
	public ScreenSeatsDto addScreenDetails(ScreenSeatsDto screenSeatsDto, Integer managerId, Integer theaterId)
			throws ManagerNotFoundException, TheaterNotFoundException {

		Optional<Manager> manager = managerRepo.findById(managerId);
		if (manager.isEmpty()) {
			throw new ManagerNotFoundException("Manager not found with this manager id" + managerId);
		}
		Optional<Theater> theater = theaterRepo.findById(theaterId);
		if (theater.isEmpty()) {
			throw new TheaterNotFoundException("Theater not found with this theater id" + theaterId);
		}

		ScreenSeats screenSeats = modelMapper.map(screenSeatsDto, ScreenSeats.class);
		screenSeats.setTheaterName(theater.get().getTheaterName());
		screenSeats.setTheater(theater.get());
		screenSeats.setManager(manager.get());

		ScreenSeats savedScreenSeats = screenSeatsRepo.save(screenSeats);
		ScreenSeats saved = modelMapper.map(screenSeats, ScreenSeats.class);

		return modelMapper.map(saved, ScreenSeatsDto.class);

	}

}
