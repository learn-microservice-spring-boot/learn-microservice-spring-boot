package learn.services.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class CurrencyFetchService {
    private final RestTemplate restTemplate;
    private final KafkaTemplate<String, String> kafkaTemplate;

    @Value("${currency.api.url}")
    private String currencyApiUrl;

    @Value("${currency.api.key}")
    private String currencyApiKey;

    @Value("${kafka.topics}")
    private String kafkaTopic;

    @Autowired
    public CurrencyFetchService(RestTemplate restTemplate, KafkaTemplate<String, String> kafkaTemplate) {
        this.restTemplate = restTemplate;
        this.kafkaTemplate = kafkaTemplate;
    }

    public void fetchAndSendData() {
        String url = currencyApiUrl + "?apikey=" + currencyApiKey;
        ResponseEntity<String> response = restTemplate.getForEntity(url, String.class);

        if (response.getStatusCode() == HttpStatus.OK) {
            kafkaTemplate.send(kafkaTopic, response.getBody());
            System.out.println("Data sent to Kafka: " + response.getBody());
        }
    }
}

