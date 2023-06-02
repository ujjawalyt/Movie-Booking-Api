package com.movie.api.dto;


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
public class MovieDto {

	private Integer movieId;
	private String theaterName;
	private String screen;
	private String shows;
	private String movieName;
	private String startdate;
	private String endDate;
}
