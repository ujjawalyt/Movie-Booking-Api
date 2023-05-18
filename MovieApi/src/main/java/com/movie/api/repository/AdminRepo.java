package com.movie.api.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.movie.api.model.Admin;

public interface AdminRepo extends JpaRepository<Admin, Integer>{

	Optional<Admin> findByEmail(String username);

}
