package co.com.sofka.bank.infrastructure.drivenadapters.h2;

import jakarta.persistence.*;
import lombok.Data;

import java.math.BigDecimal;

@Table(name = "accounts")
@Entity
@Data
public class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String accountNumber;
    private BigDecimal balance;
}
