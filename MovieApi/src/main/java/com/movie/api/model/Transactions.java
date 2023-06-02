package com.movie.api.model;

import java.time.LocalDateTime;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Table(name="transactions")
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Transactions {

	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "transaction_id")
	private Integer transactionId;
	
	private Double amount;
	
	private LocalDateTime timestamp;
	
	@Column(name = "transaction_type")
    private String transactionType;
	
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name="sender_wallet_id")
	private UserWallet  senderWallet;
	
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name="receiver_wallet_id")
	private MovieCompanyWallet receiverWallet;
}
