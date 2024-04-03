package co.com.sofka.bank.infrastructure.drivenadapters.h2.model;

import jakarta.persistence.*;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

@Table(name = "transactions")
@Entity
@Data
public class Transaction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String sourceAccount;
    private String destinationAccount;
    private BigDecimal amount;
    private String description;
    @Temporal(TemporalType.TIMESTAMP)
    private Date date;
}
