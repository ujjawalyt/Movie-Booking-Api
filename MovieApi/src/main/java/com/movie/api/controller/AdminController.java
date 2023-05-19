package com.movie.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.movie.api.dto.AdminDto;
import com.movie.api.exception.AdminNotFoundException;
import com.movie.api.exception.RoleNotFoundException;
import com.movie.api.service.AdminService;

import io.swagger.v3.oas.annotations.parameters.RequestBody;

@RestController
@RequestMapping("/admin")
public class AdminController {

	@Autowired
	private AdminService adminService;
	
	@PostMapping("/register")
	public ResponseEntity<AdminDto> registerNewAdminHandler(@RequestBody AdminDto adminDto) 
			throws AdminNotFoundException,RoleNotFoundException{
		
		return new ResponseEntity<AdminDto>(adminService.registerNewAdmin(adminDto),HttpStatus.CREATED);
	}
}
