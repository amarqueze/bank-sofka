package co.com.sofka.bank.domain.client;

public interface BankingInformationProvider {
    Client findClient(ClientDNI dni);
    AccountDetails findAccount(AccountNumber accountNumber);
}
