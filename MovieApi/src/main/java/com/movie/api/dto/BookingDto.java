package com.movie.api.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BookingDto {

	private String screen;

	private String theater;

	private String date;

	private String time;

	private Integer noOfSeats;

	private Double bookingAmount;
}
