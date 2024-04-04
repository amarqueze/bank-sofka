package co.com.sofka.bank.infrastructure.drivenadapters.h2;

import co.com.sofka.bank.domain.clientinfo.*;
import co.com.sofka.bank.infrastructure.drivenadapters.h2.model.Account;
import co.com.sofka.bank.infrastructure.drivenadapters.h2.repository.AccountRepository;
import co.com.sofka.bank.infrastructure.drivenadapters.h2.repository.ClientRepository;
import co.com.sofka.bank.infrastructure.drivenadapters.h2.repository.TransactionRepository;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@SpringBootTest
class H2BankingInformationProviderTest {
    @Mock
    private ClientRepository clientRepository;
    @Mock
    private AccountRepository accountRepository;
    @Mock
    private TransactionRepository transactionRepository;
    @InjectMocks
    private H2BankingInformationProvider bankingInformationProvider;

    @Test
    void testFindClient_Success() {
        ClientDNI clientDNI = new ClientDNI("123456789");
        co.com.sofka.bank.infrastructure.drivenadapters.h2.model.Client clientDAO = new co.com.sofka.bank.infrastructure.drivenadapters.h2.model.Client();
        clientDAO.setDni("1234567890");
        clientDAO.setName("John");
        clientDAO.setLastName("Doe");
        clientDAO.setAddress("CL 45");
        clientDAO.setPhone("123");

        List<Account> accounts = new ArrayList<>();
        clientDAO.setAccounts(accounts);

        when(clientRepository.findByDni(clientDNI.dni())).thenReturn(clientDAO);

        Client client = bankingInformationProvider.findClient(clientDNI);
        assertNotNull(client);
        assertEquals("1234567890", client.dni());
        assertEquals("John", client.name());
        assertEquals("Doe", client.lastName());
        assertEquals("CL 45", client.address());
        assertEquals("123", client.phone());
    }

    @Test
    void testFindClient_NotFound() {
        ClientDNI clientDNI = new ClientDNI("1234567890");
        when(clientRepository.findByDni(clientDNI.dni())).thenReturn(null);

        Client client = bankingInformationProvider.findClient(clientDNI);
        assertNull(client);
    }

    @Test
    void testFindAccount_Success() {
        AccountNumber accountNumber = new AccountNumber("123456789");
        co.com.sofka.bank.infrastructure.drivenadapters.h2.model.Account accountDAO = new co.com.sofka.bank.infrastructure.drivenadapters.h2.model.Account();
        accountDAO.setAccountNumber("123456789");
        accountDAO.setBalance(new BigDecimal("1000"));
        accountDAO.setStatus(AccountStatus.ACTIVE);

        List<co.com.sofka.bank.infrastructure.drivenadapters.h2.model.Transaction> transactions = new ArrayList<>();
        co.com.sofka.bank.infrastructure.drivenadapters.h2.model.Transaction transactionDAO = new co.com.sofka.bank.infrastructure.drivenadapters.h2.model.Transaction();
        transactionDAO.setDate(new Date());
        transactionDAO.setAmount(new BigDecimal(100));
        transactionDAO.setDescription("Test transaction");
        transactionDAO.setSourceAccount("000000000");
        transactionDAO.setDestinationAccount("123456789");
        transactions.add(transactionDAO);

        when(accountRepository.findByAccountNumber(accountNumber.number())).thenReturn(accountDAO);
        when(transactionRepository.findByAccountNumber(accountNumber.number())).thenReturn(transactions);

        AccountDetails accountDetails = bankingInformationProvider.findAccount(accountNumber);

        assertNotNull(accountDetails);
        assertEquals("123456789", accountDetails.accountNumber());
        assertEquals(new BigDecimal(1000), accountDetails.balance());
        assertEquals(AccountStatus.ACTIVE, accountDetails.status());
    }

    @Test
    void testFindAccount_NotFound() {
        AccountNumber accountNumber = new AccountNumber("123456789");
        when(accountRepository.findByAccountNumber(accountNumber.number())).thenReturn(null);
        AccountDetails accountDetails = bankingInformationProvider.findAccount(accountNumber);

        assertNull(accountDetails);
    }
}
