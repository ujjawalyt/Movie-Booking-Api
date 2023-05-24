package com.movie.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.movie.api.dto.ManagerDto;

import com.movie.api.exception.AdminNotFoundException;

import com.movie.api.exception.RoleNotFoundException;
import com.movie.api.service.ManagerService;

import io.swagger.v3.oas.annotations.parameters.RequestBody;

@RestController
@RequestMapping("/manager")
public class ManagerController {

	@Autowired
	private ManagerService managerService;

	@PostMapping("admin/{adminId}")
	public ResponseEntity<ManagerDto> registerNewManagerHandler(@PathVariable("adminId") Integer adminId,
			@RequestBody ManagerDto managerDto) throws AdminNotFoundException, RoleNotFoundException {

		return new ResponseEntity<ManagerDto>(managerService.AdminRegisterManager(adminId, managerDto),
				HttpStatus.CREATED);
	}
}
