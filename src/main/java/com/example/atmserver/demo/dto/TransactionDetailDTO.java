package com.example.atmserver.demo.dto;

import com.example.atmserver.demo.model.Card;
import com.example.atmserver.demo.model.TransactionDetail;
import com.example.atmserver.demo.model.TransactionType;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

public class TransactionDetailDTO {


  @NotNull
  private BigDecimal amount;

  @Enumerated(EnumType.STRING)
  @NotNull
  private TransactionType transactionType;

  @NotNull
  private String cardNumber;
  @NotNull
  private BigDecimal balance;

  public static TransactionDetail fromDTO(final TransactionDetailDTO dto) {
    final TransactionDetail transactionDetail = new TransactionDetail();
    transactionDetail.setTransactionType(dto.getTransactionType());
    transactionDetail.setAmount(dto.getAmount());

    return transactionDetail;
  }
  public static TransactionDetailDTO toDTO(final TransactionDetail transactionDetail) {
    final TransactionDetailDTO dto = new TransactionDetailDTO();
    dto.setTransactionType(transactionDetail.getTransactionType());
    dto.setAmount(transactionDetail.getAmount());
    dto.setBalance(transactionDetail.getBalance());
    dto.setCardNumber(transactionDetail.getCard().getCardNumber());

    return dto;
  }

  public TransactionDetailDTO() {
  }

  public TransactionDetailDTO(@NotNull BigDecimal amount, @NotNull TransactionType transactionType, @NotNull String cardNumber, @NotNull BigDecimal balance) {
    this.amount = amount;
    this.transactionType = transactionType;
    this.cardNumber = cardNumber;
    this.balance = balance;
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

  public String getCardNumber() {
    return cardNumber;
  }

  public void setCardNumber(String cardNumber) {
    this.cardNumber = cardNumber;
  }

  public BigDecimal getBalance() {
    return balance;
  }

  public void setBalance(BigDecimal balance) {
    this.balance = balance;
  }
}
