package com.in28minutes.microservices.currencyexchangeservices;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.ratelimiter.annotation.RateLimiter;
import io.github.resilience4j.retry.annotation.Retry;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.logging.Logger;

@RestController
public class CircuitBreakerController {

    private Logger logger = Logger.getLogger(CircuitBreakerController.class.getName());

    @GetMapping("/sample-api")
    //@Retry(name="sample-api",fallbackMethod = "hardcodedResponse") // 3 kere deneyecek ve 3. denemede hata alırsa hata fırlatacak
    // bu 3 kereyi değiştirebiliyoruz application.properties dosyasından

    //@CircuitBreaker(name="default",fallbackMethod = "hardcodedResponse")

    @RateLimiter(name="default",fallbackMethod = "hardcodedResponse") // 10 saniyede 100 istekten fazla istek gelirse hata fırlatacak
    public String sampleApi() {
        logger.info("Sample api call received");
        ResponseEntity<String> forEntity = new RestTemplate().getForEntity(
                "http://localhost:8080/some-dummy-url",
                String.class);

        return forEntity.getBody();
    }
    public String hardcodedResponse(Exception ex){
        return "fallback-response";
    }
}
