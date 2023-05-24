package com.movie.api.service;

import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.movie.api.constant.AppConstant;
import com.movie.api.dto.RolesDto;
import com.movie.api.dto.UsersDto;

import com.movie.api.exception.RoleNotFoundException;
import com.movie.api.exception.UserNotFoundException;
import com.movie.api.model.Address;

import com.movie.api.model.Roles;
import com.movie.api.model.User;
import com.movie.api.repository.RolesRepo;
import com.movie.api.repository.UserRepo;

@Service

public class UserServiceImpl implements UsersService {

	@Autowired
	private UserRepo userRepo;
    @Autowired
    private RolesRepo rolesRepo;
    @Autowired
    private ModelMapper modelMapper;
   

	@Override
	public UsersDto registerAsUser(UsersDto usersDto) throws UserNotFoundException, RoleNotFoundException {
	
		Optional<User> existingUserOptional = userRepo.findByEmail(usersDto.getEmail());
	    if (existingUserOptional.isPresent()) {
	        throw new UserNotFoundException("User is already present with this email address: " + usersDto.getEmail());
	    }
		
		
		User users = modelMapper.map(usersDto, User.class);
	    Address address = new Address();
	    address.setCity(usersDto.getAddress().getCity()); 
	    address.setCountry(usersDto.getAddress().getCountry()); 
	    address.setPincode(usersDto.getAddress().getPincode()); 
	    address.setStreet(usersDto.getAddress().getStreet()); 

	    users.setAddress(address);
	    
	   
	    Roles userRole = rolesRepo.findById(AppConstant.NORMAL_USER)
	            .orElseThrow(() -> new RoleNotFoundException("User role not found"));
	    
	    users.getRoles().add(userRole);
	    
	  
	    User savedUser= userRepo.save(users);
	    UsersDto savedUserDto = modelMapper.map(savedUser, UsersDto.class);
	    RolesDto userRoleDto = modelMapper.map(userRole, RolesDto.class);
//	    savedUserDto.getRoles().add(userRoleDto);

	   
	    return savedUserDto;
		
		
		
		
		
		
	}

}
