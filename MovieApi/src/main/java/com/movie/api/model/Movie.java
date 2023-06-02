package com.movie.api.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class Movie {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private Integer movieId;
	private String theaterName;
	private String screen;
	private String shows;
	private String movieName;
	private String startdate;
	private String endDate;
	
	@ManyToOne
	@JoinColumn(name = "managerId")
	private Manager manager;

	@ManyToOne
	@JoinColumn(name="theaterId")
	private Theater theater;
	
	
	@OneToMany(mappedBy = "movie")
	private List<Booking> bookings=new ArrayList<>();
}
