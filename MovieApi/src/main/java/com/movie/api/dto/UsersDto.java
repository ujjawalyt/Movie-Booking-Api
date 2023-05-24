package com.movie.api.dto;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Embedded;

import com.movie.api.model.Address;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class UsersDto {

	
	private String firstName;
	private String lastName;
	private String phoneNumber;
	private String email;
	private String password;

	 private List<RolesDto>roles = new ArrayList();
	@Embedded
	private Address address;
	
}
