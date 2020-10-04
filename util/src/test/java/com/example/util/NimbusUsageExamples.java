package com.example.util;

import com.nimbusds.jose.JOSEException;
import com.nimbusds.jose.JWSAlgorithm;
import com.nimbusds.jose.JWSHeader;
import com.nimbusds.jose.JWSObject;
import com.nimbusds.jose.JWSSigner;
import com.nimbusds.jose.JWSVerifier;
import com.nimbusds.jose.Payload;
import com.nimbusds.jose.crypto.MACSigner;
import com.nimbusds.jose.crypto.MACVerifier;
import java.text.ParseException;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

class NimbusUsageExamples {

  @Test
  void testJwsCreation() throws JOSEException, ParseException {
    String sharedSecret = "262878bf9f89cbc3d7912a26cc272e6c";

    // create the JWS object
    JWSObject jwsObject = new JWSObject(new JWSHeader(JWSAlgorithm.HS256), new Payload("Hello, world!"));
    JWSSigner signer = new MACSigner(sharedSecret);
    jwsObject.sign(signer);

    // Serialize the JWS to its URL Compact serialization
    String jws = jwsObject.serialize();
    System.out.println(jws);
    Assertions.assertThat(jws).isEqualTo("eyJhbGciOiJIUzI1NiJ9.SGVsbG8sIHdvcmxkIQ.nKwQ2xzsvZHWJkrjFHQYFSVt0Flf_hLXvsV2QDwp3Lk");

    // parse the JWS
    var  parsedJws = JWSObject.parse(jws);
    JWSVerifier verifier = new MACVerifier(sharedSecret);
    Assertions.assertThat(parsedJws.verify(verifier)).isTrue();
    Assertions.assertThat(parsedJws.getPayload().toString()).isEqualTo("Hello, world!");
  }
}
