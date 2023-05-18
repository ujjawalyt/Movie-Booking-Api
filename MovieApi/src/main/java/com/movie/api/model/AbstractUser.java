package com.movie.api.model;

import javax.persistence.Embedded;
import javax.persistence.MappedSuperclass;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@MappedSuperclass
@Data
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class AbstractUser {

	private String firstName;
	private String lastName;
	private String phoneNumber;
	private String email;
	private String password;

	@Embedded
	private Address address;
}
