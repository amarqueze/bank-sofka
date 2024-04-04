package co.com.sofka.bank.usecase;

import co.com.sofka.bank.domain.clientinfo.AccountDetails;
import co.com.sofka.bank.domain.clientinfo.AccountNotFound;
import co.com.sofka.bank.domain.clientinfo.AccountNumber;
import co.com.sofka.bank.domain.clientinfo.BankingInformationProvider;
import lombok.RequiredArgsConstructor;

import java.util.Objects;

@RequiredArgsConstructor
public class AccountDetailsFinder {
    private final BankingInformationProvider bankingInformationProvider;
    public AccountDetails find(AccountNumber accountNumber) {
        AccountDetails accountDetails = bankingInformationProvider.findAccount(accountNumber);
        if (Objects.isNull(accountDetails)) throw new AccountNotFound();

        return accountDetails;
    }
}
