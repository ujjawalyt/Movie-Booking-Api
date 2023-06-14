package com.movie.api.dto;

import java.time.LocalDateTime;

import com.movie.api.model.MovieCompanyWallet;
import com.movie.api.model.UserWallet;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TransactionDto {

	private Integer transactionId;
	private Double amount;
	private LocalDateTime timestamp;
	private String transactionType;
	private String senderName;
	private String receiverName;
}
