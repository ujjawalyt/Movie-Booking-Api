package com.movie.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.movie.api.dto.UsersDto;

import com.movie.api.exception.RoleNotFoundException;
import com.movie.api.exception.UserNotFoundException;

import com.movie.api.service.UsersService;

import io.swagger.v3.oas.annotations.parameters.RequestBody;

@RestController
@RequestMapping("/users")
public class UserController {

	@Autowired
	private UsersService usersService;

	@GetMapping("/welcome")
	public String myWelcome() {
		return "welcome to movie application";
	}

	@PostMapping("/register")
	public ResponseEntity<UsersDto> registerNewUSerHandler(@RequestBody UsersDto usersDto)
			throws UserNotFoundException, RoleNotFoundException {

		return new ResponseEntity<UsersDto>(usersService.registerAsUser(usersDto), HttpStatus.CREATED);
	}

}
