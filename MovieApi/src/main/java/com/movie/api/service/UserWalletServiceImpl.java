package com.movie.api.service;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.movie.api.dto.UserWalletsDto;
import com.movie.api.dto.UsersDto;
import com.movie.api.exception.UserNotFoundException;
import com.movie.api.exception.WalletAlreadyExistsException;
import com.movie.api.exception.WalletNotFoundException;
import com.movie.api.model.User;
import com.movie.api.model.UserWallet;
import com.movie.api.repository.UserRepo;
import com.movie.api.repository.UserWalletRepo;

@Service
public class UserWalletServiceImpl implements UserWalletsService {

	@Autowired
	private UserWalletRepo userWalletRepo;
	@Autowired
	private UserRepo userRepo;
	@Autowired
	private ModelMapper modelMapper;

	@Override

	public UserWalletsDto createWalletByUser(UserWalletsDto walletsDto, Integer userid)
			throws UserNotFoundException, WalletAlreadyExistsException {

		User user = userRepo.findById(userid)
				.orElseThrow(() -> new UserNotFoundException("User not found with ID: " + userid));

		boolean walletExists = userWalletRepo.existsByUser(user);
		if (walletExists) {
			throw new WalletAlreadyExistsException("Wallet already exists for user with ID: " + userid);
		}

		UserWallet wallets = modelMapper.map(walletsDto, UserWallet.class);
		wallets.setUser(user);
		UserWallet saveWallet = userWalletRepo.save(wallets);
		UserWalletsDto walletsDto2 = modelMapper.map(saveWallet, UserWalletsDto.class);

		UsersDto userDto = modelMapper.map(user, UsersDto.class);
		walletsDto2.setUsersDto(userDto);
		return walletsDto2;

	}

	@Override
	public String deletWalletFromUser(Integer userid, Integer walletId)
			throws UserNotFoundException, WalletNotFoundException {

		User user = userRepo.findById(userid)
				.orElseThrow(() -> new UserNotFoundException("User not found with ID: " + userid));
		UserWallet wallets = userWalletRepo.findById(walletId)
				.orElseThrow(() -> new WalletNotFoundException("Wallet not found with Id :" + walletId));
		if (!wallets.getUser().equals(user)) {
			throw new WalletNotFoundException("Wallet not found with ID: " + walletId + " for user with ID: " + userid);
		}

		wallets.setUser(null);
		userWalletRepo.delete(wallets);

		return "Wallet Removed Successfully..";

	}

	@Override
	public String addBalanceToWallet(Integer userid, Double amount)
			throws UserNotFoundException, WalletNotFoundException {

		User users = userRepo.findById(userid)
				.orElseThrow(() -> new UserNotFoundException("User not found with ID: " + userid));

		List<UserWallet> wallets = userWalletRepo.findByUser(users);
		if (wallets.isEmpty()) {
			throw new WalletNotFoundException("No wallets found for user with ID: " + userid);
		}

		for (UserWallet wallet : wallets) {
			Double currentBalance = wallet.getBalance();
			Double finalBalance = currentBalance + amount;
			wallet.setBalance(finalBalance);
			userWalletRepo.save(wallet);
		}

		return "Amount added - " + amount + " to all wallets successfully";

		
	}

}
