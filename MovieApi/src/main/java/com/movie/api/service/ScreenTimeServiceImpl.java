package com.movie.api.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.movie.api.dto.ScreenTimeDto;
import com.movie.api.exception.ManagerNotFoundException;
import com.movie.api.exception.ScreenTimeNotFoundException;
import com.movie.api.exception.TheaterNotFoundException;
import com.movie.api.model.Manager;
import com.movie.api.model.ScreenTime;
import com.movie.api.model.Theater;
import com.movie.api.repository.ManagerRepo;
import com.movie.api.repository.ScreenTimeRepo;
import com.movie.api.repository.TheaterRepo;

@Service
public class ScreenTimeServiceImpl  implements ScreenTimeService{

	@Autowired
	private ModelMapper modelMapper;
	@Autowired
	private ScreenTimeRepo screenTimeRepo;
	@Autowired
	private ManagerRepo managerRepo;
	@Autowired
	private TheaterRepo theaterRepo;
	
	@Override
	public ScreenTimeDto addScreenTime(ScreenTimeDto screenTimeDto, Integer managerId,Integer theaterId) throws
ManagerNotFoundException ,TheaterNotFoundException, ScreenTimeNotFoundException {
		
		Optional<Manager> manager = managerRepo.findById(managerId);
		if(manager.isEmpty()) {
			throw new ManagerNotFoundException("Manager not found with this manager id"+ managerId);
		}
		Optional<Theater> theater = theaterRepo.findById(theaterId);
		if (theater.isEmpty()) {
			throw new TheaterNotFoundException("Theater not found with this theater id" + theaterId);
		}

		
		int numberOfScreens = theater.get().getNumberOfScreens();
		String theaterName = theater.get().getTheaterName();
		List<ScreenTime> screenTimes = screenTimeRepo.findByTheaterName(theaterName);
		int currentNumberOfScreens = screenTimes.stream()
		        .map(ScreenTime::getScreen)
		        .distinct()
		        .collect(Collectors.toList())
		        .size();

		if (currentNumberOfScreens >= numberOfScreens) {
		    throw new IllegalArgumentException("Number of screens exceeded the limit for the theater: " + theaterName);
		}

		String screen = screenTimeDto.getScreen();
		String showTime = screenTimeDto.getShowTime();

		boolean isScreenBooked = screenTimes.stream()
		        .anyMatch(screenTime ->
		                screenTime.getScreen().equals(screen) && !screenTime.getShowTime().equals(showTime));

		if (isScreenBooked) {
		    throw new IllegalArgumentException("Screen " + screen + " is already booked at a different show time");
		}
		
		
	
		
//		int numberOfScreens = theater.get().getNumberOfScreens();
//		String theaterName = theater.get().getTheaterName();
//		List<ScreenTime> screenTimes = screenTimeRepo.findByTheaterName(theaterName);
//		int currentNumberOfScreens = screenTimes.size();
//		if (currentNumberOfScreens >= numberOfScreens) {
//		    throw new ScreenTimeNotFoundException("Number of screens exceeded the limit for the theater: " + theaterName);
//		}
		

		     
		
		
		
		
//		
//		String screen = screenTimeDto.getScreen();
//		    String showTime = screenTimeDto.getShowTime();
//		
//		 Optional<ScreenTime> existingScreenTime = screenTimeRepo.findByScreenAndShowTime(screenTimeDto.getScreen(), screenTimeDto.getShowTime());
//		    if (existingScreenTime.isPresent()) {
//		        throw new ScreenTimeNotFoundException("Screen and time combination already exists");
//		    }
//		    
//		    
		
		
		
		

		
	ScreenTime screenTime = modelMapper.map(screenTimeDto,ScreenTime.class );
	screenTime.setManager(manager.get());
	screenTime.setTheater(theater.get());
	screenTime.setTheaterName(theater.get().getTheaterName());
	
	ScreenTime savedSceenTime = screenTimeRepo.save(screenTime);
   return modelMapper.map(savedSceenTime, ScreenTimeDto.class);
		    
	}
	
	
	
}
