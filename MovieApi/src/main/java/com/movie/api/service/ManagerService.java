package com.movie.api.service;

import org.springframework.stereotype.Service;

import com.movie.api.dto.ManagerDto;
import com.movie.api.exception.AdminNotFoundException;
import com.movie.api.exception.ManagerNotFoundException;
import com.movie.api.exception.RoleNotFoundException;

@Service
public interface ManagerService {

	
	public ManagerDto AdminRegisterManager(Integer adminId , ManagerDto managerDto)
	throws RoleNotFoundException,AdminNotFoundException;
}
