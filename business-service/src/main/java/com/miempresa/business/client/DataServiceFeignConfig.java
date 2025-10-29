// src/main/java/com/miempresa/business/client/DataServiceFeignConfig.java
package main.java.com.miempresa.business.client;

import feign.Logger;
import feign.codec.ErrorDecoder;
import org.springframework.context.annotation.Bean;

public class DataServiceFeignConfig {
  @Bean Logger.Level feignLoggerLevel() { return Logger.Level.BASIC; }
  @Bean ErrorDecoder errorDecoder() { return new FeignHttpErrorDecoder(); }
}

