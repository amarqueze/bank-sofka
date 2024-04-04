package co.com.sofka.bank.infrastructure.entrypoints.dto;

import co.com.sofka.bank.domain.clientinfo.AccountNumber;
import co.com.sofka.bank.domain.operation.CashDeposit;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class DepositRequest {
    private String destinationAccount;
    private BigDecimal amount;

    public CashDeposit toCashDeposit() {
        return new CashDeposit(amount, new AccountNumber(destinationAccount));
    }
}
