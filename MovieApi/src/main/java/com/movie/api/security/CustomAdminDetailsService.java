package com.movie.api.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.movie.api.model.Admin;
import com.movie.api.repository.AdminRepo;

@Service
public class CustomAdminDetailsService implements UserDetailsService {

	@Autowired
	private AdminRepo adminRepo;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		Admin admin=adminRepo.findByEmail(username).orElseThrow(()->new UsernameNotFoundException("Admin is not found."));
		return admin;
	}
}
