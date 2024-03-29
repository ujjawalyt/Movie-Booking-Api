package com.movie.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.movie.api.dto.ManagerDto;
import com.movie.api.dto.MovieDto;
import com.movie.api.dto.ScreenSeatsDto;
import com.movie.api.dto.ScreenTimeDto;
import com.movie.api.dto.TheaterDto;
import com.movie.api.exception.AdminNotFoundException;
import com.movie.api.exception.ManagerNotFoundException;
import com.movie.api.exception.RoleNotFoundException;
import com.movie.api.exception.ScreenSeatNotFoundException;
import com.movie.api.exception.ScreenTimeNotFoundException;
import com.movie.api.exception.TheaterNotFoundException;
import com.movie.api.service.ManagerService;
import com.movie.api.service.MovieService;
import com.movie.api.service.ScreenSeatService;
import com.movie.api.service.ScreenTimeService;
import com.movie.api.service.TheaterService;

import io.swagger.v3.oas.annotations.parameters.RequestBody;

@RestController
@RequestMapping("/manager")
public class ManagerController {

	@Autowired
	private ManagerService managerService;

	@Autowired
	private TheaterService theaterService;

	@Autowired
	private ScreenSeatService screenSeatService;

	@Autowired
	private ScreenTimeService screenTimeService;
	@Autowired
	private MovieService movieService;
	

	@PostMapping("admin/{adminId}")
	public ResponseEntity<ManagerDto> registerNewManagerHandler(@PathVariable("adminId") Integer adminId,
			@RequestBody ManagerDto managerDto) throws AdminNotFoundException, RoleNotFoundException {

		return new ResponseEntity<ManagerDto>(managerService.AdminRegisterManager(adminId, managerDto),
				HttpStatus.CREATED);
	}

	@PostMapping("manager/{managerId}")
	public ResponseEntity<TheaterDto> registerTheaterHandler(@PathVariable("managerId") Integer managerId,
			@RequestBody TheaterDto theaterDto) throws ManagerNotFoundException {

		return new ResponseEntity<TheaterDto>(theaterService.addTheater(theaterDto, managerId), HttpStatus.CREATED);
	}

	@PostMapping("screentime/manager/{managerId}/theater/{theaterId}")
	public ResponseEntity<ScreenTimeDto> registerScreenTimeByManagerHandler(
			@PathVariable("managerId") Integer managerId, @PathVariable("theaterId") Integer theaterId,
			@RequestBody ScreenTimeDto screenTimeDto)
			throws ManagerNotFoundException, TheaterNotFoundException, ScreenTimeNotFoundException {

		return new ResponseEntity<ScreenTimeDto>(screenTimeService.addScreenTime(screenTimeDto, managerId, theaterId),
				HttpStatus.CREATED);
	}

	@PostMapping("manager/{managerId}/theater/{theaterId}/screenTime/{screenTimeId}")
	public ResponseEntity<ScreenSeatsDto> registerScreenSeatsByManagerHandler(
			@PathVariable("managerId") Integer managerId, @PathVariable("theaterId") Integer theaterId,
			@RequestBody ScreenSeatsDto screenSeatsDto, @PathVariable("screenTimeId") Integer screenTimeId)
			throws ManagerNotFoundException, TheaterNotFoundException, ScreenTimeNotFoundException {
		return new ResponseEntity<ScreenSeatsDto>(
				screenSeatService.addSeatsToScreen(screenSeatsDto, managerId, theaterId, screenTimeId),
				HttpStatus.CREATED);
	}
	
	
	@PostMapping("manager/{managerId}/theater/{theaterId}/ScreenTimeId/{screenTimeId}/screenSeatId/{screenSeatId}")
	public ResponseEntity<MovieDto> addMovieHandler(
	        @PathVariable Integer managerId,
	        @PathVariable Integer theaterId,
	        @RequestBody MovieDto movieDto,
	        @PathVariable Integer screenTimeId,
	        @PathVariable Integer screenSeatId
	) throws  ManagerNotFoundException, TheaterNotFoundException,
	ScreenTimeNotFoundException, ScreenSeatNotFoundException
	
	{
	 
	        MovieDto addedMovie = movieService.addMovie(movieDto, managerId, theaterId, screenTimeId, screenSeatId);
	        return new ResponseEntity<MovieDto>(addedMovie, HttpStatus.CREATED);
	    
	        
	     
	    
	}
	

}
