package com.example.payments;

import java.math.BigDecimal;

public class Refund {
  private String orderId;
  private BigDecimal amount;

  public Refund(String orderId, BigDecimal amount) {
    this.orderId = orderId;
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
