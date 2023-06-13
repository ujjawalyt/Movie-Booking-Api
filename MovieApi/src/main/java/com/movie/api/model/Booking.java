package com.movie.api.model;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;



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
public class Booking {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private Integer bookingId;
	
	private String screen;
	
	private String theater;
	
	private Date date;
	
	private String time;
	
	private String category;
	
	private Integer noOfSeats;
	private Double bookingAmount;

	private String bookingStatus;
	
	
	@ManyToOne(cascade = CascadeType.ALL )
	@JoinColumn(name="userId")
	private User user;
	
	@ManyToOne(cascade = CascadeType.ALL )
	@JoinColumn(name="movieId")
	private Movie movie;
	
	@ManyToOne(cascade =CascadeType.ALL )
	@JoinColumn(name = "screenSeatId")
	private ScreenSeats screenSeats;
	
	
	
}
