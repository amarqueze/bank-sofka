package co.com.sofka.bank.domain.client;

import lombok.Builder;

import java.util.Objects;

import static co.com.sofka.bank.domain.util.ArgumentChecker.checkIsValidArgument;
import static co.com.sofka.bank.domain.util.ArgumentChecker.checkIsValidString;

@Builder
public record Account(String accountNumber, AccountStatus status) {
    public Account {
        checkIsValidString(accountNumber, "AccountNumber is required");
        checkIsValidArgument(account -> account.length() <= 9, accountNumber, "AccountNumber cannot be greater than 9");
        checkIsValidArgument(sts -> !Objects.isNull(sts), status, "status is required");
    }
}
