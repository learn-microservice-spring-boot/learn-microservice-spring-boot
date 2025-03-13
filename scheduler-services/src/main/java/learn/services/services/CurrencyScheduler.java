package learn.services.services;

import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class CurrencyScheduler {
    private final RestTemplate restTemplate;

    public CurrencyScheduler(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Scheduled(fixedRate = 900000) // hit per 15 menit
//    @Scheduled(fixedRate = 30000) // hit per 30 detik
    public void scheduleFetchCurrencyData() {
        ResponseEntity<String> response = restTemplate.getForEntity("http://localhost:3002/api/currency/fetch", String.class);
        System.out.println("Response: " + response.getBody());
    }
}

