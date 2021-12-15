package com.example.atmserver.demo.model;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.List;
import javax.validation.Constraint;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

/**
 * As JPA/H2 doesn't easily let us save the 2d arrays, the sudoku
 * board will be saved as a simple list.
 */

@Entity
public class Card {

  @Id
  @GeneratedValue(strategy= GenerationType.AUTO)
  private Long id;

  @NotNull
  private String cardNumber;


  @NotNull
  @Positive
  private BigDecimal balance;


  @NotNull
  private Boolean enabled;

  @OneToMany(mappedBy = "card")
  private List<TransactionDetail> transactionDetailList;


  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
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

  public Boolean getEnabled() {
    return enabled;
  }

  public void setEnabled(Boolean enabled) {
    this.enabled = enabled;
  }

  public List<TransactionDetail> getTransactionDetailList() {
    return transactionDetailList;
  }

  public void setTransactionDetailList(List<TransactionDetail> transactionDetailList) {
    this.transactionDetailList = transactionDetailList;
  }

  public Card() {
  }

  public Card(@NotNull String cardNumber,  @NotNull BigDecimal balance,  @NotNull Boolean enabled) {
    this.cardNumber = cardNumber;
    this.balance = balance;
    this.enabled = enabled;
  }
}
