package co.com.sofka.bank.infrastructure.drivenadapters.h2;

import co.com.sofka.bank.domain.clientinfo.AccountNumber;
import co.com.sofka.bank.domain.clientinfo.AccountStatus;
import co.com.sofka.bank.domain.operation.CashDeposit;
import co.com.sofka.bank.domain.operation.TransactionFailed;
import co.com.sofka.bank.infrastructure.drivenadapters.h2.model.Account;
import co.com.sofka.bank.infrastructure.drivenadapters.h2.repository.AccountRepository;
import co.com.sofka.bank.infrastructure.drivenadapters.h2.repository.TransactionRepository;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@SpringBootTest
@Transactional
class H2CashDepositProcessorTest {
    @Mock
    private AccountRepository accountRepository;
    @Mock
    private TransactionRepository transactionRepository;
    @InjectMocks
    private H2CashDepositProcessor cashDepositProcessor;

    @Test
    void testToDeposit_Success() {
        Account account = new Account();
        account.setAccountNumber("123456789");
        account.setStatus(AccountStatus.ACTIVE);
        account.setBalance(new BigDecimal(100));
        CashDeposit cashDeposit = new CashDeposit(new BigDecimal(100), new AccountNumber("123456789"));

        when(accountRepository.findByAccountNumber(cashDeposit.accountNumber().number())).thenReturn(account);

        cashDepositProcessor.toDeposit(cashDeposit);
        verify(accountRepository, times(1)).save(account);
        verify(transactionRepository, times(1)).save(any());
    }

    @Test
    void testToDeposit_AccountNotFound() {
        CashDeposit cashDeposit = new CashDeposit(new BigDecimal(100), new AccountNumber("123456789"));
        when(accountRepository.findByAccountNumber(cashDeposit.accountNumber().number())).thenReturn(null);

        assertThrows(TransactionFailed.class, () -> cashDepositProcessor.toDeposit(cashDeposit));
    }

    @Test
    void testToDeposit_InactiveAccount() {
        Account account = new Account();
        account.setStatus(AccountStatus.INACTIVE);
        CashDeposit cashDeposit = new CashDeposit(new BigDecimal("123456789"), new AccountNumber("123456789"));
        when(accountRepository.findByAccountNumber(cashDeposit.accountNumber().number())).thenReturn(account);

        assertThrows(TransactionFailed.class, () -> cashDepositProcessor.toDeposit(cashDeposit));
    }
}
