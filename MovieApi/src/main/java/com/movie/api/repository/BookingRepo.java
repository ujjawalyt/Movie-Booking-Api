package com.movie.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.movie.api.model.Booking;

public interface BookingRepo extends JpaRepository<Booking, Integer>{

}
