package com.example.payments;

import java.math.BigDecimal;

public class Refund {
  private String creditCard;
  private BigDecimal amount;

  public Refund(String creditCard, BigDecimal amount) {
    this.creditCard = creditCard;
    this.amount = amount;
  }

  public String getCreditCard() {
    return creditCard;
  }

  public void setCreditCard(String creditCard) {
    this.creditCard = creditCard;
  }

  public BigDecimal getAmount() {
    return amount;
  }

  public void setAmount(BigDecimal amount) {
    this.amount = amount;
  }
}
