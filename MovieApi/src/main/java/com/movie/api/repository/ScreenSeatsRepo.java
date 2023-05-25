package com.movie.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.movie.api.model.ScreenSeats;

@Repository
public interface ScreenSeatsRepo  extends JpaRepository< ScreenSeats, Integer>{

}
