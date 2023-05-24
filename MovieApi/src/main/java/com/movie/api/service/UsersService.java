package com.movie.api.service;

import org.springframework.stereotype.Service;

import com.movie.api.dto.UsersDto;
import com.movie.api.exception.RoleNotFoundException;
import com.movie.api.exception.UserNotFoundException;

@Service
public interface UsersService {

	public UsersDto registerAsUser(UsersDto usersDto) throws UserNotFoundException, RoleNotFoundException;
}
