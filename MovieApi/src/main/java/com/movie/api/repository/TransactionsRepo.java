package com.movie.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.movie.api.model.Transactions;

public interface TransactionsRepo extends JpaRepository<Transactions, Integer> {

}
