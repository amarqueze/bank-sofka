package co.com.sofka.bank.domain.operation;

import co.com.sofka.bank.domain.clientinfo.AccountNumber;

import java.math.BigDecimal;
import java.util.Objects;

import static co.com.sofka.bank.domain.util.ArgumentChecker.checkIsValidArgument;

public record CashDeposit(BigDecimal amount, AccountNumber accountNumber) {
    public CashDeposit {
        checkIsValidArgument(a -> !Objects.isNull(a) && a.compareTo(BigDecimal.ZERO) > 0, amount, "amount is invalid");
        checkIsValidArgument(a -> !Objects.isNull(a), accountNumber, "accountNumber is required");
    }
}
