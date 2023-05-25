package com.movie.api.service;

import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.movie.api.dto.TheaterDto;
import com.movie.api.exception.ManagerNotFoundException;
import com.movie.api.model.Address;
import com.movie.api.model.Manager;
import com.movie.api.model.Theater;
import com.movie.api.repository.ManagerRepo;
import com.movie.api.repository.TheaterRepo;

@Service
public class TheaterServiceImpl implements TheaterService{

	@Autowired
	private ModelMapper modelMapper;
	
	@Autowired
	private ManagerRepo managerRepo;
	
	@Autowired
	private TheaterRepo theaterRepo;

	@Override
	public TheaterDto addTheater(TheaterDto theaterDto, Integer managerId) throws ManagerNotFoundException {
		
		Optional<Manager> manager = managerRepo.findById(managerId);
		if(manager.isEmpty()) {
			throw new ManagerNotFoundException("Manager not found with this manager id"+ managerId);
		}
		
		Theater theater = modelMapper.map(theaterDto, Theater.class);
		
	
		 Address address = new Address();
		    address.setCity(theaterDto.getAddress().getCity()); 
		    address.setCountry(theaterDto.getAddress().getCountry()); 
		    address.setPincode(theaterDto.getAddress().getPincode()); 
		    address.setStreet(theaterDto.getAddress().getStreet()); 
		  
		    theater.setAddress(address);
		    theater.setManager(manager.get());
		    
		    Theater savedTheater = theaterRepo.save(theater);
		    return modelMapper.map(savedTheater, TheaterDto.class);
		
	}
	
	
	
} 
