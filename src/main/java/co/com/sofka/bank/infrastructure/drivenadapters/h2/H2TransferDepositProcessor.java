package co.com.sofka.bank.infrastructure.drivenadapters.h2;

import co.com.sofka.bank.domain.clientinfo.AccountStatus;
import co.com.sofka.bank.domain.operation.TransactionFailed;
import co.com.sofka.bank.domain.operation.TransferDeposit;
import co.com.sofka.bank.domain.operation.TransferDepositProcessor;
import co.com.sofka.bank.infrastructure.drivenadapters.h2.model.Account;
import co.com.sofka.bank.infrastructure.drivenadapters.h2.model.Transaction;
import co.com.sofka.bank.infrastructure.drivenadapters.h2.repository.AccountRepository;
import co.com.sofka.bank.infrastructure.drivenadapters.h2.repository.TransactionRepository;
import lombok.RequiredArgsConstructor;
import org.apache.logging.log4j.util.Strings;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Date;
import java.util.Objects;

@Component
@RequiredArgsConstructor
public class H2TransferDepositProcessor implements TransferDepositProcessor {
    private final AccountRepository accountRepository;
    private final TransactionRepository transactionRepository;

    @Override
    @Transactional
    public synchronized void toDeposit(TransferDeposit transferDeposit) {
        Account sourceAccount = accountRepository.findByAccountNumber(transferDeposit.origin().number());
        if (Objects.isNull(sourceAccount) || sourceAccount.getStatus().equals(AccountStatus.INACTIVE))
            throw new TransactionFailed();

        Account destinationAccount = accountRepository.findByAccountNumber(transferDeposit.destination().number());
        if (Objects.isNull(destinationAccount) || destinationAccount.getStatus().equals(AccountStatus.INACTIVE))
            throw new TransactionFailed();

        if (sourceAccount.getBalance().compareTo(transferDeposit.amount()) < 0) {
            throw new TransactionFailed();
        }

        sourceAccount.setBalance(sourceAccount.getBalance().subtract(transferDeposit.amount()));
        destinationAccount.setBalance(destinationAccount.getBalance().add(transferDeposit.amount()));

        accountRepository.save(sourceAccount);
        accountRepository.save(destinationAccount);

        ZoneId bogotaZone = ZoneId.of("America/Bogota");
        ZonedDateTime bogotaTime = LocalDateTime.now().atZone(bogotaZone);
        Date date = Date.from(bogotaTime.toInstant());

        Transaction transaction = new Transaction();
        transaction.setSourceAccount(sourceAccount.getAccountNumber());
        transaction.setDestinationAccount(destinationAccount.getAccountNumber());
        transaction.setAmount(transferDeposit.amount());
        transaction.setDescription(Strings.isBlank(transferDeposit.description())
                ? "TRANSFERENCIA SUCURSAL VIRTUAL" : transferDeposit.description());
        transaction.setDate(date);
        transactionRepository.save(transaction);
    }
}
