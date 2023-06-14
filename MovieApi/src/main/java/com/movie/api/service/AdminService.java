package com.movie.api.service;

import org.springframework.stereotype.Service;

import com.movie.api.dto.AdminDto;
import com.movie.api.dto.AdminWalletsDto;
import com.movie.api.dto.UserWalletsDto;
import com.movie.api.exception.AdminNotFoundException;
import com.movie.api.exception.RoleNotFoundException;
import com.movie.api.exception.UserNotFoundException;
import com.movie.api.exception.WalletAlreadyExistsException;

@Service
public interface AdminService {

	public AdminDto registerNewAdmin(AdminDto adminDto) throws AdminNotFoundException,RoleNotFoundException;
	
	public AdminWalletsDto createWalletByAdmin(AdminWalletsDto walletsDto , Integer manager)
	         throws AdminNotFoundException,WalletAlreadyExistsException;
}
