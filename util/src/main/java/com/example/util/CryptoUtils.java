package com.example.util;

import com.nimbusds.jose.JOSEException;
import com.nimbusds.jose.JWSAlgorithm;
import com.nimbusds.jose.JWSHeader;
import com.nimbusds.jose.JWSObject;
import com.nimbusds.jose.JWSVerifier;
import com.nimbusds.jose.Payload;
import com.nimbusds.jose.crypto.MACSigner;
import com.nimbusds.jose.crypto.MACVerifier;
import java.text.ParseException;
import java.util.Optional;

public class CryptoUtils {

  public static Optional<String> verifyJwsHmacSha256AndGetPayload(String jws, byte[] key) {
    try {
      JWSObject jwsObject = JWSObject.parse(jws);
      JWSVerifier verifier = new MACVerifier(key);
      if (jwsObject.verify(verifier)) {
        return Optional.of(jwsObject.getPayload().toString());
      }
      return Optional.empty();
    } catch (ParseException | JOSEException e) {
      throw new RuntimeException(e);
    }
  }

  public static String signJwsHmacSha256(String content, byte[] key) {
    try {
      JWSHeader header = new JWSHeader(JWSAlgorithm.HS256);
      Payload payload = new Payload(content);
      JWSObject jwsObject = new JWSObject(header, payload);
      jwsObject.sign(new MACSigner(key));
      return jwsObject.serialize();
    } catch (JOSEException e) {
      throw new RuntimeException(e);
    }
  }
}
