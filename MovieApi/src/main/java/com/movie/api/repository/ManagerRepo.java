package com.movie.api.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.movie.api.model.Manager;

public interface ManagerRepo extends JpaRepository<Manager, Integer>  {

	Optional<Manager> findByEmail(String username);

}
