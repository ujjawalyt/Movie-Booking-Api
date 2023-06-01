package com.movie.api.dto;



import com.movie.api.Enum.ShowTime;

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
public class ScreenTimeDto {

	
	private Integer screenTimeId;
	private String theaterName;
	private String screen;
	private ShowTime showTime;
	private String ShowName;
}
