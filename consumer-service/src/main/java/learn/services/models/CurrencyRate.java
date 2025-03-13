package learn.services.models;

import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDateTime;

@Entity
@Data
@Table(name = "currency")
public class CurrencyRate {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "date", nullable = false)
    private LocalDateTime date;  // Gunakan LocalDateTime

    @Column(name = "base", nullable = false, length = 10)
    private String base;

    @Column(name = "fetch_date_at", nullable = false)
    private LocalDateTime fetchedAt;

    @Column(name = "rates", columnDefinition = "TEXT")  // Gunakan TEXT, bukan CLOB
    private String rate;
}
