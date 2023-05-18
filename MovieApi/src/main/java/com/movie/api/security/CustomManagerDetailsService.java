package com.movie.api.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.movie.api.model.Manager;
import com.movie.api.repository.ManagerRepo;

@Service
public class CustomManagerDetailsService implements UserDetailsService {

	@Autowired
	private ManagerRepo managerRepo;
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		Manager manager=managerRepo.findByEmail(username).orElseThrow(()->new UsernameNotFoundException("Manager is not Found"));
		return manager;
	}

}
