package com.movie.api.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Embedded;
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
public class Theater {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private Integer theaterId;
	
	private String theaterName;
	private String theaterType;
	private String contactNumber;
	private Integer numberOfScreens;
	private String theaterEmail;
	@Embedded 
	private Address address;
	
	
	
	@ManyToOne
	@JoinColumn(name="managerId")
	private Manager manager;
	
	
	@OneToMany(cascade = CascadeType.ALL,mappedBy = "theater")
	private List<Movie> movies=new ArrayList<>();;
	
	@OneToMany(cascade = CascadeType.ALL,mappedBy = "theater")
	private List<ScreenTime> screenTimes=new ArrayList<>();
	
	@OneToMany(mappedBy = "theater",cascade =CascadeType.ALL )
	List<ScreenSeats>  screenSeats=new ArrayList<>();
	
	
	
}