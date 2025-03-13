package learn.services.controllers;

import learn.services.services.CurrencyFetchService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/currency")
public class CurrencyController {
    private final CurrencyFetchService currencyFetchService;

    public CurrencyController(CurrencyFetchService currencyFetchService) {
        this.currencyFetchService = currencyFetchService;
    }

    @GetMapping("/fetch")
    public ResponseEntity<String> fetchCurrencyData() {
        currencyFetchService.fetchAndSendData();
        return ResponseEntity.ok("Currency data fetched and sent to Kafka");
    }
}

