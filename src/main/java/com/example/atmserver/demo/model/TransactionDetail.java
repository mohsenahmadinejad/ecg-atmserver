package com.example.atmserver.demo.model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.math.BigDecimal;
import java.util.Objects;

/**
 * Exercise
 */
@Entity
public class TransactionDetail {

	@Id
	@GeneratedValue
	private Long id;

	@NotNull
	@Positive
	private BigDecimal amount;

	@Enumerated(EnumType.STRING)
	@NotNull
	private TransactionType transactionType;

	@ManyToOne
	@JoinColumn(name = "FK_CARD")
	private Card card;

	@NotNull
	@Positive
	private BigDecimal balance;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public BigDecimal getAmount() {
		return amount;
	}

	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}

	public TransactionType getTransactionType() {
		return transactionType;
	}

	public void setTransactionType(TransactionType transactionType) {
		this.transactionType = transactionType;
	}

	public BigDecimal getBalance() {
		return balance;
	}

	public void setBalance(BigDecimal balance) {
		this.balance = balance;
	}

	public Card getCard() {
		return card;
	}

	public void setCard(Card card) {
		this.card = card;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		TransactionDetail that = (TransactionDetail) o;
		return  Objects.equals(amount, that.amount) && transactionType == that.transactionType && Objects.equals(balance, that.balance);
	}

	@Override
	public int hashCode() {
		return Objects.hash( amount, transactionType, balance);
	}
}

