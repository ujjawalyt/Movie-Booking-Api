package com.movie.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.movie.api.dto.UserWalletsDto;
import com.movie.api.dto.UsersDto;

import com.movie.api.exception.RoleNotFoundException;
import com.movie.api.exception.UserNotFoundException;
import com.movie.api.exception.WalletAlreadyExistsException;
import com.movie.api.exception.WalletNotFoundException;
import com.movie.api.service.UserWalletsService;
import com.movie.api.service.UsersService;

import io.swagger.v3.oas.annotations.parameters.RequestBody;

@RestController
@RequestMapping("/users")
public class UserController {

	@Autowired
	private UsersService usersService;
	
	@Autowired
	private UserWalletsService userWalletsService;

	@GetMapping("/welcome")
	public String myWelcome() {
		return "welcome to movie application";
	}

	@PostMapping("/register")
	public ResponseEntity<UsersDto> registerNewUSerHandler(@RequestBody UsersDto usersDto)
			throws UserNotFoundException, RoleNotFoundException {

		return new ResponseEntity<UsersDto>(usersService.registerAsUser(usersDto), HttpStatus.CREATED);
	}
	
	
	
	
	@PostMapping("/create/user/{userid}")
	public ResponseEntity<UserWalletsDto> createWalletByUser
	(@RequestBody UserWalletsDto walletsDto, @PathVariable("userid")Integer userid) throws UserNotFoundException,WalletAlreadyExistsException{
		
		return new ResponseEntity<UserWalletsDto>(userWalletsService.createWalletByUser(walletsDto, userid),HttpStatus.CREATED);
				
	}
	
	@DeleteMapping("/delete/user/{userid}/wallet/{walletid}")
	public ResponseEntity<String> deletWalletByUser
	( @PathVariable("userid")Integer userid,  @PathVariable("walletid")Integer walletid) 
			throws UserNotFoundException, WalletNotFoundException{
		
		return new ResponseEntity<String>(userWalletsService.deletWalletFromUser(userid, walletid),HttpStatus.ACCEPTED);
				
	}
	
	@PutMapping("/update/user/{userid}/amount/{amount}")
	public ResponseEntity<String> updateBalanceByUser( @PathVariable("userid")Integer userid, 
			 @PathVariable("amount") Double amount
			) throws UserNotFoundException,WalletNotFoundException
	{
		return new ResponseEntity<String>(userWalletsService.addBalanceToWallet(userid, amount),HttpStatus.OK);
	}

}
