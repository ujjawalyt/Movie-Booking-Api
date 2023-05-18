package com.movie.api.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.movie.api.model.User;

public interface UserRepo extends JpaRepository<User, Integer> {

	Optional<User> findByEmail(String username);

}
