package com.movie.api.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class UserWalletsDto {

	
	private Integer walletId;
	private Double balance;
	private UsersDto usersDto;
}
