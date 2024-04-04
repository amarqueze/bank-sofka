package co.com.sofka.bank.usecase;

import co.com.sofka.bank.domain.clientinfo.AccountNumber;
import co.com.sofka.bank.domain.operation.*;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doThrow;

@SpringBootTest
class AccountDepositorTest {
    @Mock
    private CashDepositProcessor cashDepositProcessor;
    @Mock
    private TransferDepositProcessor transferDepositProcessor;
    @InjectMocks
    private AccountDepositor accountDepositor;

    @Test
    void testToDepositFromCash_Success() {
        CashDeposit cashDeposit = new CashDeposit(new BigDecimal(100), new AccountNumber("123456789"));
        doNothing().when(cashDepositProcessor).toDeposit(cashDeposit);

        boolean result = accountDepositor.toDepositFromCash(cashDeposit);
        assertTrue(result);
    }

    @Test
    void testToDepositFromCash_Failure() {
        CashDeposit cashDeposit = new CashDeposit(new BigDecimal(100), new AccountNumber("123456789"));
        doThrow(new TransactionFailed()).when(cashDepositProcessor).toDeposit(cashDeposit);

        boolean result = accountDepositor.toDepositFromCash(cashDeposit);
        assertFalse(result);
    }

    @Test
    void testToDepositFromTransfer_Success() {
        TransferDeposit transferDeposit = TransferDeposit.builder()
                .origin(new AccountNumber("123456789"))
                .destination(new AccountNumber("987654321"))
                .amount(new BigDecimal(50))
                .description("Some description")
                .build();
        doNothing().when(transferDepositProcessor).toDeposit(transferDeposit);

        boolean result = accountDepositor.toDepositFromTransfer(transferDeposit);
        assertTrue(result);
    }

    @Test
    void testToDepositFromTransfer_Failure() {
        TransferDeposit transferDeposit = TransferDeposit.builder()
                .origin(new AccountNumber("123456789"))
                .destination(new AccountNumber("987654321"))
                .amount(new BigDecimal(50))
                .description("Some description")
                .build();
        doThrow(new RuntimeException()).when(transferDepositProcessor).toDeposit(transferDeposit);

        boolean result = accountDepositor.toDepositFromTransfer(transferDeposit);
        assertFalse(result);
    }
}
