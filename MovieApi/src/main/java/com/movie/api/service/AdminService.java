package com.movie.api.service;

import org.springframework.stereotype.Service;

import com.movie.api.dto.AdminDto;
import com.movie.api.exception.AdminNotFoundException;
import com.movie.api.exception.RoleNotFoundException;

@Service
public interface AdminService {

	public AdminDto registerNewAdmin(AdminDto adminDto) throws AdminNotFoundException,RoleNotFoundException;
}
