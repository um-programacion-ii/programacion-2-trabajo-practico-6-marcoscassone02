// src/main/java/com/miempresa/business/BusinessServiceApplication.java
package main.java.com.miempresa.business;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class BusinessServiceApplication {
  public static void main(String[] args) {
    SpringApplication.run(BusinessServiceApplication.class, args);
  }
}
