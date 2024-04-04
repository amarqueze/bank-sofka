package co.com.sofka.bank.domain.clientinfo;

public interface BankingInformationProvider {
    Client findClient(ClientDNI dni);
    AccountDetails findAccount(AccountNumber accountNumber);
}
