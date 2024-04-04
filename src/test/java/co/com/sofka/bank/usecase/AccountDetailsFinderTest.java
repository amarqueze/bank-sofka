package co.com.sofka.bank.usecase;

import co.com.sofka.bank.domain.clientinfo.*;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@SpringBootTest
class AccountDetailsFinderTest {
    @Mock
    private BankingInformationProvider bankingInformationProvider;
    @InjectMocks
    private AccountDetailsFinder accountDetailsFinder;

    @Test
    void testFindAccount() {
        AccountNumber accountNumber = new AccountNumber("123456789");
        AccountDetails expectedAccountDetails = AccountDetails.builder()
                .accountNumber("123456789")
                .balance(new BigDecimal(1000))
                .status(AccountStatus.ACTIVE)
                .transactions(new ArrayList<>())
                .build();
        when(bankingInformationProvider.findAccount(accountNumber)).thenReturn(expectedAccountDetails);

        AccountDetails actualAccountDetails = accountDetailsFinder.find(accountNumber);
        assertNotNull(actualAccountDetails);
        assertEquals(expectedAccountDetails, actualAccountDetails);
    }

    @Test
    void testFindAccountNotFound() {
        AccountNumber accountNumber = new AccountNumber("123456789");
        when(bankingInformationProvider.findAccount(accountNumber)).thenReturn(null);

        assertThrows(AccountNotFound.class, () -> accountDetailsFinder.find(accountNumber));
    }
}
