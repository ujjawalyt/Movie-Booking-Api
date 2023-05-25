package com.movie.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.movie.api.model.ScreenTime;

@Repository
public interface ScreenTimeRepo extends JpaRepository<ScreenTime, Integer>{

}
