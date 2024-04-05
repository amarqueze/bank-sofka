package co.com.sofka.bank.infrastructure.drivenadapters.h2;

import co.com.sofka.bank.domain.clientinfo.AccountStatus;
import co.com.sofka.bank.domain.operation.CashDeposit;
import co.com.sofka.bank.domain.operation.CashDepositProcessor;
import co.com.sofka.bank.domain.operation.TransactionFailed;
import co.com.sofka.bank.infrastructure.drivenadapters.h2.model.Account;
import co.com.sofka.bank.infrastructure.drivenadapters.h2.model.Transaction;
import co.com.sofka.bank.infrastructure.drivenadapters.h2.repository.AccountRepository;
import co.com.sofka.bank.infrastructure.drivenadapters.h2.repository.TransactionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Date;
import java.util.Objects;

@Component
@RequiredArgsConstructor
public class H2CashDepositProcessor implements CashDepositProcessor {
    private final AccountRepository accountRepository;
    private final TransactionRepository transactionRepository;

    @Override
    @Transactional
    public synchronized void toDeposit(CashDeposit cashDeposit) {
        Account account = accountRepository.findByAccountNumber(cashDeposit.accountNumber().number());
        if (Objects.isNull(account) || account.getStatus().equals(AccountStatus.INACTIVE))
            throw new TransactionFailed();

        account.setBalance(account.getBalance().add(cashDeposit.amount()));
        accountRepository.save(account);

        ZoneId bogotaZone = ZoneId.of("America/Bogota");
        ZonedDateTime bogotaTime = LocalDateTime.now().atZone(bogotaZone);
        Date date = Date.from(bogotaTime.toInstant());

        Transaction transaction = new Transaction();
        transaction.setSourceAccount("000000000");
        transaction.setDestinationAccount(account.getAccountNumber());
        transaction.setAmount(cashDeposit.amount());
        transaction.setDescription("ABONO EN CAJA");
        transaction.setDate(date);
        transactionRepository.save(transaction);
    }
}
