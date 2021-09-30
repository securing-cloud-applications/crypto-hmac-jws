package com.example.warehouse;

import java.math.BigDecimal;

public class Refund {
  private String orderId;
  private BigDecimal amount;

  public Refund(String creditCard, BigDecimal amount) {
    this.orderId = creditCard;
    this.amount = amount;
  }

  public String getOrderId() {
    return orderId;
  }

  public void setOrderId(String orderId) {
    this.orderId = orderId;
  }

  public BigDecimal getAmount() {
    return amount;
  }

  public void setAmount(BigDecimal amount) {
    this.amount = amount;
  }
}
