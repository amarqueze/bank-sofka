package co.com.sofka.bank.domain.operation;

import co.com.sofka.bank.domain.clientinfo.AccountNumber;
import lombok.Builder;

import java.math.BigDecimal;
import java.util.Objects;

import static co.com.sofka.bank.domain.util.ArgumentChecker.checkIsValidArgument;

@Builder
public record TransferDeposit(AccountNumber origin, AccountNumber destination, BigDecimal amount, String description) {
    public TransferDeposit {
        checkIsValidArgument(a -> !Objects.isNull(a) && a.compareTo(BigDecimal.ZERO) > 0, amount, "amount is invalid");
        checkIsValidArgument(a -> !Objects.isNull(a), origin, "Source accountNumber is required");
        checkIsValidArgument(a -> !Objects.isNull(a), destination, "Destination accountNumber is required");
    }
}
