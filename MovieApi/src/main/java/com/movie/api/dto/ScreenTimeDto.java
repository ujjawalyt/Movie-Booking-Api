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
public class ScreenTimeDto {

	
	private Integer screenTimeId;
	private String theaterName;
	private String screen;
	private String showTime;
	private String ShowName;
}
