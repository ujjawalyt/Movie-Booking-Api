package com.movie.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.movie.api.model.Theater;

@Repository
public interface TheaterRepo extends JpaRepository<Theater, Integer> {

	
}
