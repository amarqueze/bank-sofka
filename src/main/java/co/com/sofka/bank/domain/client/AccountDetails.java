package co.com.sofka.bank.domain.client;

import lombok.Builder;

import java.math.BigDecimal;

@Builder
public record AccountDetails(String accountNumber, BigDecimal balance, AccountStatus status) {
}
