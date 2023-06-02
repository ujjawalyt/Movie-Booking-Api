package com.movie.api.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.movie.api.model.Movie;

public interface MovieRepo extends JpaRepository<Movie , Integer>{

	Optional<Movie> findByMovieNameAndShows(String movieName, String displayName);

	
}
