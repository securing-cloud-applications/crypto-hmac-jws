# crypto-aes-jwe

Demo project showing how to use JSON Web Encryption with AES in GCM mode with 256-bit key to
guarantee integrity,  authenticity, and confidentiality between two applications. 
There are three subprojects in this repo.

* utils - contains shared utility classes `CrptoUtils` and `JsonUtils`.  
* warehouse - generates `data/refunds.jwe` file encrypted with AES/GCM, depends on the utils project. 
* payments -  reads and decrypts `data/refunds.jwe`, depends on utils project.

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
  `data/refunds.jwe` file
* run payments app `java -jar payments/target/payments-0.0.1-SNAPSHOT.jar` to read the 
  `data/refunds.jwe` and verify and decrypt it.
* edit `data/refunds.jwe` to simulate corruption. you can add a newline at the end of the file.
* run payments app `java -jar payments/target/payments-0.0.1-SNAPSHOT.jar` you will an exception. 

## run from the IDE 

* run `com.example.warehouse.WarehouseApplication` to generate the `data/refunds.jwe`
* run `com.example.payments.PaymentsApplication` to read the `data/refunds.jwe`  verify and decrypt 
* edit `data/refunds.jwe` to simulate corruption. you can add a newline at the end of the file.
* run `com.example.payments.PaymentsApplication` to get a data corruption exception 
* restore `data/refunds.jwe` to its original state
* edit the refunds password in `payments/src/main/resources/application.yml`
* run `com.example.payments.PaymentsApplication` to get a data corruption exception 

## interesting files to look at 

* `util/src/main/java/com/example/util/CryptoUtils.java` to examine AES encryption
* `warehouse/src/main/java/com/example/warehouse/RefundGenerationService.java` to examine the code
that generates the `refund.jwe` file
* `payments/src/main/java/com/example/payments/PaymentService.java` to examine the code that 
 verifies and decrypts the `refund.jwe` file before consuming it.

## Generating a random 256-bit key with OpenSSL

Using the openssl command line you can generate 256-bit encoded as  16 bytes of hex using the 
command `openssl rand -hex 16`
