package com.movie.api.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AdminWalletsDto {

	private Integer walletId;
	private Double balance;
	private ManagerDto managerDto;
}
