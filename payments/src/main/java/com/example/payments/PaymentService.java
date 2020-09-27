package com.example.payments;

import com.example.util.CryptoUtils;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import org.springframework.stereotype.Component;

@Component
public class PaymentService {

  public void processRefunds(Path refundsFile, byte[] key) {
    try {
      String jwe = Files.readString(refundsFile);
      String refundsJson =
          CryptoUtils.verifyJwsHmacSha256AndGetPayload(jwe, key)
              .orElseThrow(() -> new CorruptRefundFileException());
      System.out.println("Issuing Refund to");
      System.out.println(refundsJson);
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
  }
}
