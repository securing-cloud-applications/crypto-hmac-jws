# crypto-aes-jwe

Demo project showing how to use JSON Web Encryption with AES in GCM mode with 256-bit key to
guarantee integrity,  authenticity, and confidentiality between two applications. 
There are three subprojects in this repo.

* utils - contains shared utility classes `CrptoUtils` and `JsonUtils`.  
* warehouse - generates `data/refunds.jws` file encrypted with AES/GCM, depends on the utils project. 
* payments -  reads and decrypts `data/refunds.jws`, depends on utils project.

## Critical Warning

**DO NOT blindly copy/paste code from the samples into production applications**. 

The samples in this repo are for educational purposes to demonstrate security concepts in an easy
to understand way. NO effort has been put into making the code production ready. The key 
constraint for the code is that it can fit on a printed page and on slides. Therefore, we don't do 
validation in places where it should be done, we don't handle Java exceptions in a production worthy
way. Furthermore, the samples typically explains one aspect of security and do the simplest thing
to make the sample work, for example storing keys in plain text files, which is super insecure and
should not be done in production.

Use this repo to learn security concepts so that you can better understand security protocols,
patterns and libraries. Once you learn the concepts it is your responsibility to implement those
concepts in a production quality, secure manner in your application. Please work with your 
information security team to determine the suitability of using the patterns shown in the 
samples to your specific situation.

## software prerequisites 

* Java 11 JDK 
* Java IDE 

## run on the command line

* run warehouse app `java -jar warehouse/target/warehouse-0.0.1-SNAPSHOT.jar` to generate the 
  `data/refunds.jws` file
* run payments app `java -jar payments/target/payments-0.0.1-SNAPSHOT.jar` to read the 
  `data/refunds.jws` and verify and decrypt it.
* edit `data/refunds.jws` to simulate corruption. you can add a newline at the end of the file.
* run payments app `java -jar payments/target/payments-0.0.1-SNAPSHOT.jar` you will an exception. 

## run from the IDE 

* run `com.example.warehouse.WarehouseApplication` to generate the `data/refunds.jws`
* run `com.example.payments.PaymentsApplication` to read the `data/refunds.jws`  verify and decrypt 
* edit `data/refunds.jws` to simulate corruption you can copy and paste text below. The payment amount 
has been changed from 500 to 600 in the payload. 
```
eyJhbGciOiJIUzI1NiJ9.W3siY3JlZGl0Q2FyZCI6IjU1NTU1NTU1NTU1NTQ0NDQiLCJhbW91bnQiOjU2MDB9LHsiY3JlZGl0Q2FyZCI6IjQwMTI4ODg4ODg4ODE4ODEiLCJhbW91bnQiOjI1MH1d.lU9ad8QlWsX-AYInrYlK522YOba7L5BjNiGqJnUMaGo
```
* run `com.example.payments.PaymentsApplication` to get a data corruption exception 
* restore `data/refunds.jws` to its original state
* edit the refunds key in `payments/src/main/resources/application.yml`
* run `com.example.payments.PaymentsApplication` to get a data corruption exception 
* restore the refunds key in `payments/src/main/resources/application.yml` to its original state
* go to [jwt.io](https://jwt.io)
* copy and paste the `data/refunds.jws` into the decoder on the page, notice that you can see
 the payload. The decoder should indicate the  payload is not verified.  
* copy and paste the key from the `payments/src/main/resources/application.yml` into the decoder
and use it to verify the signature. 

## interesting files to look at 

* `util/src/main/java/com/example/util/CryptoUtils.java` to examine AES encryption
* `warehouse/src/main/java/com/example/warehouse/RefundGenerationService.java` to examine the code
that generates the `refund.jws` file
* `payments/src/main/java/com/example/payments/PaymentService.java` to examine the code that 
 verifies and decrypts the `refund.jws` file before consuming it.

## Generating a random 256-bit key with OpenSSL

Using the openssl command line you can generate 256-bit encoded as  16 bytes of hex using the 
command `openssl rand -hex 16`
