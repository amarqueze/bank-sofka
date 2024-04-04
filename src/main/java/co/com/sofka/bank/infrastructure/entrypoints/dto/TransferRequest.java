package co.com.sofka.bank.infrastructure.entrypoints.dto;

import co.com.sofka.bank.domain.clientinfo.AccountNumber;
import co.com.sofka.bank.domain.operation.TransferDeposit;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class TransferRequest {
    private String origin;
    private String destination;
    private BigDecimal amount;
    private String note;

    public TransferDeposit toTransferDeposit() {
        return TransferDeposit.builder()
                .origin(new AccountNumber(origin))
                .destination(new AccountNumber(destination))
                .amount(amount)
                .description(note)
                .build();
    }
}
