package co.com.sofka.bank.domain.client;

import lombok.Builder;

@Builder
public record Account(String accountNumber, AccountStatus status) {
}
