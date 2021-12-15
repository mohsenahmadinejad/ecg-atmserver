package com.example.atmserver.demo.dto;

import com.example.atmserver.demo.model.Card;


import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.math.BigDecimal;

public class CardDTO {

  @NotNull
  private String cardNumber;
  @NotNull
  private String password;

  @NotNull
  @Positive
  private BigDecimal balance;

  private int failedAttempt;

  private Boolean enabled;

  public static Card fromDTO(final CardDTO dto) {
    final Card c = new Card();
    c.setBalance(dto.getBalance());
    c.setCardNumber(dto.getCardNumber());
    c.setEnabled(dto.getEnabled());
    return c;
  }
  public static CardDTO toDTO(final Card card) {
    final CardDTO cardDto = new CardDTO();
    cardDto.setBalance(card.getBalance());
    cardDto.setCardNumber(card.getCardNumber());
    cardDto.setEnabled(card.getEnabled());
    return cardDto;
  }
  public String getCardNumber() {
    return cardNumber;
  }

  public void setCardNumber(String cardNumber) {
    this.cardNumber = cardNumber;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  public BigDecimal getBalance() {
    return balance;
  }

  public void setBalance(BigDecimal balance) {
    this.balance = balance;
  }

  public int getFailedAttempt() {
    return failedAttempt;
  }

  public void setFailedAttempt(int failedAttempt) {
    this.failedAttempt = failedAttempt;
  }

  public Boolean getEnabled() {
    return enabled;
  }

  public void setEnabled(Boolean enabled) {
    this.enabled = enabled;
  }
}
