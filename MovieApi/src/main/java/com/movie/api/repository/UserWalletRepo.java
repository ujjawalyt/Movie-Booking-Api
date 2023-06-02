package com.movie.api.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.movie.api.model.User;
import com.movie.api.model.UserWallet;


@Repository
public interface UserWalletRepo  extends JpaRepository<UserWallet, Integer>{

	boolean existsByUser(User user);

	List<UserWallet> findByUser(User users);

}
