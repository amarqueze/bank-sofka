package co.com.sofka.bank.infrastructure.drivenadapters.h2.model;

import co.com.sofka.bank.domain.clientinfo.AccountStatus;
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
    @Column(unique = true)
    private String accountNumber;
    private BigDecimal balance;
    @Enumerated(EnumType.STRING)
    private AccountStatus status;

    @ManyToOne
    private Client client;
}
