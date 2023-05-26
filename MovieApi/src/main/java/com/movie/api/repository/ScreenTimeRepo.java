package com.movie.api.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.movie.api.model.ScreenTime;
import com.movie.api.model.Theater;

@Repository
public interface ScreenTimeRepo extends JpaRepository<ScreenTime, Integer>{

	Optional<ScreenTime> findByScreenAndShowTime(String screen, String showTime);

	int countByTheater(Theater theater);
    List<ScreenTime> findByTheaterName(String theaterName);

}
