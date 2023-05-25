package com.movie.api.dto;

import java.util.Date;
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

	private String movieId;
	private String theaterName;
	private String screen;
	private Integer shows;
	private String movieName;
	private Date startdate;
	private Date endDate;
}
