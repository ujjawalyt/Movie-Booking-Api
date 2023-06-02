package com.movie.api.service;

import org.springframework.stereotype.Service;

import com.movie.api.dto.UserWalletsDto;
import com.movie.api.exception.UserNotFoundException;
import com.movie.api.exception.WalletAlreadyExistsException;
import com.movie.api.exception.WalletNotFoundException;

@Service
public interface UserWalletsService {

	public UserWalletsDto createWalletByUser(UserWalletsDto walletsDto , Integer userid)
	         throws UserNotFoundException,WalletAlreadyExistsException;
		public String deletWalletFromUser(Integer userid, Integer walletId)
				throws UserNotFoundException ,WalletNotFoundException;
		public String addBalanceToWallet(Integer userid,  Double amount)
				throws UserNotFoundException ,WalletNotFoundException;
}
