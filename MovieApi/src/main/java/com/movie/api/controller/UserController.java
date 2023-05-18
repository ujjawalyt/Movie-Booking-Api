package com.movie.api.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

	@GetMapping("/welcome")
	public String myWelcome() {
		return "welcom to movie application";
	}
}
