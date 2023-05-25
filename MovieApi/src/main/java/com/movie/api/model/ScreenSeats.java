package com.movie.api.model;

import java.util.ArrayList;
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
public class ScreenSeats {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private Integer screenSeatsId;
	private String theaterName;
	private String screen;
	private String category;
	private Integer totalSeats;
	private Double price;
	private Integer avalSeats;

	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "theaterId")
	private Theater theater;

	@ManyToOne()
	@JoinColumn(name = "managerId")
	private Manager manager;

	@OneToMany(mappedBy = "screenSeats")
	private List<Booking> bookings = new ArrayList<>();

}
