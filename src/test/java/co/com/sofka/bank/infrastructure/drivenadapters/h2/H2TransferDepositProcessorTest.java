package co.com.sofka.bank.infrastructure.drivenadapters.h2;

import co.com.sofka.bank.domain.clientinfo.AccountNumber;
import co.com.sofka.bank.domain.clientinfo.AccountStatus;
import co.com.sofka.bank.domain.operation.TransactionFailed;
import co.com.sofka.bank.domain.operation.TransferDeposit;
import co.com.sofka.bank.infrastructure.drivenadapters.h2.model.Account;
import co.com.sofka.bank.infrastructure.drivenadapters.h2.repository.AccountRepository;
import co.com.sofka.bank.infrastructure.drivenadapters.h2.repository.TransactionRepository;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@SpringBootTest
@Transactional
class H2TransferDepositProcessorTest {
    @Mock
    private AccountRepository accountRepository;
    @Mock
    private TransactionRepository transactionRepository;
    @InjectMocks
    private H2TransferDepositProcessor transferDepositProcessor;

    @Test
    void testToDeposit_Success() {
        Account sourceAccount = new Account();
        sourceAccount.setAccountNumber("123456789");
        sourceAccount.setStatus(AccountStatus.ACTIVE);
        sourceAccount.setBalance(new BigDecimal(1000));

        Account destinationAccount = new Account();
        destinationAccount.setAccountNumber("987654321");
        destinationAccount.setStatus(AccountStatus.ACTIVE);
        destinationAccount.setBalance(new BigDecimal(500));

        TransferDeposit transferDeposit = new TransferDeposit(
                new AccountNumber("123456789"),
                new AccountNumber("987654321"),
                new BigDecimal(50),
                null
        );

        when(accountRepository.findByAccountNumber(transferDeposit.origin().number())).thenReturn(sourceAccount);
        when(accountRepository.findByAccountNumber(transferDeposit.destination().number())).thenReturn(destinationAccount);

        transferDepositProcessor.toDeposit(transferDeposit);

        verify(accountRepository, times(1)).save(sourceAccount);
        verify(accountRepository, times(1)).save(destinationAccount);
        verify(transactionRepository, times(1)).save(any());
    }

    @Test
    void testToDeposit_SourceAccountNotFound() {
        TransferDeposit transferDeposit = new TransferDeposit(
                new AccountNumber("123456789"),
                new AccountNumber("987654321"),
                new BigDecimal(50),
                "Transfer description"
        );

        when(accountRepository.findByAccountNumber(transferDeposit.origin().number())).thenReturn(null);
        assertThrows(TransactionFailed.class, () -> transferDepositProcessor.toDeposit(transferDeposit));
    }

    @Test
    void testToDeposit_DestinationAccountNotFound() {
        Account sourceAccount = new Account();
        sourceAccount.setAccountNumber("123456789");
        sourceAccount.setStatus(AccountStatus.ACTIVE);
        sourceAccount.setBalance(new BigDecimal(1000));
        TransferDeposit transferDeposit = new TransferDeposit(
                new AccountNumber("123456789"),
                new AccountNumber("987654321"),
                new BigDecimal(50),
                "Transfer description"
        );

        when(accountRepository.findByAccountNumber(transferDeposit.origin().number())).thenReturn(sourceAccount);
        when(accountRepository.findByAccountNumber(transferDeposit.destination().number())).thenReturn(null);
        assertThrows(TransactionFailed.class, () -> transferDepositProcessor.toDeposit(transferDeposit));
    }

    @Test
    void testToDeposit_InsufficientBalance() {
        Account sourceAccount = new Account();
        sourceAccount.setAccountNumber("123456789");
        sourceAccount.setStatus(AccountStatus.ACTIVE);
        sourceAccount.setBalance(new BigDecimal(1000));

        Account destinationAccount = new Account();
        destinationAccount.setAccountNumber("987654321");
        destinationAccount.setStatus(AccountStatus.ACTIVE);
        destinationAccount.setBalance(new BigDecimal(500));

        TransferDeposit transferDeposit = new TransferDeposit(
                new AccountNumber("123456789"),
                new AccountNumber("987654321"),
                new BigDecimal(5000),
                "Transfer description"
        );

        when(accountRepository.findByAccountNumber(sourceAccount.getAccountNumber())).thenReturn(sourceAccount);
        when(accountRepository.findByAccountNumber(destinationAccount.getAccountNumber())).thenReturn(destinationAccount);
        assertThrows(TransactionFailed.class, () -> transferDepositProcessor.toDeposit(transferDeposit));
    }
}
