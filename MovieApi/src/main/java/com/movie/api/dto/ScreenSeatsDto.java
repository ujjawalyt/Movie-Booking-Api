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
public class ScreenSeatsDto {

	private Integer screenSeatsId;
	private String theaterName;
	private String screen;
	private String category;
	private Integer totalSeats;
	private Double price;
	private Integer avalSeats;
}
