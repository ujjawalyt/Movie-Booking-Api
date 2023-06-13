package com.movie.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.movie.api.model.MovieCompanyWallet;

public interface CompanyWalletRepo  extends JpaRepository<MovieCompanyWallet, Integer>{

	
}
