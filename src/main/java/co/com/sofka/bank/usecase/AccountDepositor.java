package co.com.sofka.bank.usecase;

import co.com.sofka.bank.domain.operation.CashDeposit;
import co.com.sofka.bank.domain.operation.CashDepositProcessor;
import co.com.sofka.bank.domain.operation.TransferDeposit;
import co.com.sofka.bank.domain.operation.TransferDepositProcessor;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class AccountDepositor {
    private final CashDepositProcessor cashDepositProcessor;
    private final TransferDepositProcessor transferDepositProcessor;

    public boolean toDepositFromCash(CashDeposit cashDeposit) {
        try {
            cashDepositProcessor.toDeposit(cashDeposit);
            return true;
        } catch(Exception e) {
            return false;
        }
    }

    public boolean toDepositFromTransfer(TransferDeposit transferDeposit) {
        try {
            transferDepositProcessor.toDeposit(transferDeposit);
            return true;
        } catch(Exception e) {
            return false;
        }
    }
}
