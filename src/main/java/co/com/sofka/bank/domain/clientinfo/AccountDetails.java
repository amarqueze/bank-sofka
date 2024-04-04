package co.com.sofka.bank.domain.clientinfo;

import lombok.Builder;

import java.math.BigDecimal;
import java.util.List;

@Builder
public record AccountDetails(String accountNumber, BigDecimal balance, AccountStatus status, List<Transaction> transactions) {
}
