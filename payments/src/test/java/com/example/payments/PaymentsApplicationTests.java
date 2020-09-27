package com.example.payments;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest(args = "--refundsPath=../data/refunds.jws")
class PaymentsApplicationTests {

  @Test
  void contextLoads() {}
}
