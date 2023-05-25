package com.movie.api.dto;



import javax.persistence.Embedded;

import com.movie.api.model.Address;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class TheaterDto {

private Integer theaterId;
	
	private String theaterName;
	private String theaterType;
	private String contactNumber;
	private Integer numberOfScreens;
	private String theaterEmail;
	@Embedded 
	private Address address;
}
