package learn.services.services;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import learn.services.models.CurrencyRate;
import learn.services.repositories.CurrencyRateRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Iterator;
import java.util.Map;

import java.time.ZonedDateTime;

@Service
public class CurrencyConsumer {
    private static final Logger logger = LoggerFactory.getLogger(CurrencyConsumer.class);
    private final CurrencyRateRepository repository;
    private final ObjectMapper objectMapper;

    // Formatter yang sesuai dengan format dari Kafka
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ssX");

    public CurrencyConsumer(CurrencyRateRepository repository, ObjectMapper objectMapper) {
        this.repository = repository;
        this.objectMapper = objectMapper;
    }

    @KafkaListener(topics = "${kafka.topics}", groupId = "${spring.kafka.consumer.group-id}")
    public void consume(String message) {
        try {
            JsonNode jsonNode = objectMapper.readTree(message);

            // Mengambil tanggal dari JSON atau set default jika tidak ada
            String dateStr = jsonNode.has("date") ? jsonNode.get("date").asText() : null;
            LocalDateTime date = (dateStr != null)
                    ? ZonedDateTime.parse(dateStr, FORMATTER).toLocalDateTime()
                    : LocalDateTime.now();

            // Mengambil base currency
            String baseCurrency = jsonNode.has("base") ? jsonNode.get("base").asText() : "UNKNOWN";

            // Mengambil rates
            JsonNode rates = jsonNode.get("rates");

            if (rates != null && rates.isObject()) {
                for (Iterator<Map.Entry<String, JsonNode>> it = rates.fields(); it.hasNext(); ) {
                    Map.Entry<String, JsonNode> entry = it.next();
                    String currency = entry.getKey();
                    BigDecimal rate = new BigDecimal(entry.getValue().asText());

                    // Simpan data ke database
                    CurrencyRate currencyRate = new CurrencyRate();
                    currencyRate.setDate(date);
                    currencyRate.setBase(baseCurrency);
                    currencyRate.setRate(rate.toString());
                    currencyRate.setFetchedAt(LocalDateTime.now());

                    repository.save(currencyRate);
                    logger.info("Saved rate: {} -> {} ({})", currency, rate, date);
                }
            } else {
                logger.warn("No valid 'rates' field found in the message.");
            }
        } catch (Exception e) {
            logger.error("Error processing Kafka message: {}", e.getMessage(), e);
        }
    }
}
