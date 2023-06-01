package com.movie.api.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.movie.api.Enum.ShowTime;
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
public class ScreenTimeServiceImpl implements ScreenTimeService {

	@Autowired
	private ModelMapper modelMapper;
	@Autowired
	private ScreenTimeRepo screenTimeRepo;
	@Autowired
	private ManagerRepo managerRepo;
	@Autowired
	private TheaterRepo theaterRepo;

	@Override
	public ScreenTimeDto addScreenTime(ScreenTimeDto screenTimeDto, Integer managerId, Integer theaterId)
			throws ManagerNotFoundException, TheaterNotFoundException, ScreenTimeNotFoundException {

		Optional<Manager> manager = managerRepo.findById(managerId);
		if (manager.isEmpty()) {
			throw new ManagerNotFoundException("Manager not found with this manager id" + managerId);
		}
		Optional<Theater> theater = theaterRepo.findById(theaterId);
		if (theater.isEmpty()) {
			throw new TheaterNotFoundException("Theater not found with this theater id" + theaterId);
		}

		if (theater.get().getManager().equals(manager.get())) {

			System.out.println("correct");

			String screenString = screenTimeDto.getScreen();
			int screenInt = Integer.parseInt(screenString);

			if (screenInt <= theater.get().getNumberOfScreens()) {
				System.out.println("screen correct");

				ShowTime showtime = screenTimeDto.getShowTime();
				System.out.println(showtime);
				Optional<ScreenTime> screenOptional = screenTimeRepo.findByScreenAndShowTime(screenString, showtime);
				if (screenOptional.isPresent()) {
					throw new TheaterNotFoundException("Already screenTime set for this show");
				}
				ScreenTime screenTime = modelMapper.map(screenTimeDto, ScreenTime.class);

				screenTime.setManager(manager.get());
				screenTime.setTheater(theater.get());
				screenTime.setTheaterName(theater.get().getTheaterName());
				screenTime.setShowTime(showtime);
				ScreenTime savedScreen = screenTimeRepo.save(screenTime);
				return modelMapper.map(savedScreen, ScreenTimeDto.class);

			} else {
				System.out.println("Screen is Exceed ");
				throw new TheaterNotFoundException("Screen is Exceed ");
			}

		} else {
			System.out.println("error");
			throw new TheaterNotFoundException("this Theater is not assciate with  this manager ");
		}

		

	}

}
