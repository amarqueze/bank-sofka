package co.com.sofka.bank.usecase;

import co.com.sofka.bank.domain.client.BankingInformationProvider;
import co.com.sofka.bank.domain.client.Client;
import co.com.sofka.bank.domain.client.ClientDNI;
import co.com.sofka.bank.domain.client.ClientNotFound;

import java.util.Objects;

public class ClientFinder {
    private final BankingInformationProvider bankingInformationProvider;

    public ClientFinder(BankingInformationProvider bankingInformationProvider) {
        this.bankingInformationProvider = bankingInformationProvider;
    }

    public Client find(ClientDNI clientDNI) {
        Client client = bankingInformationProvider.findClient(clientDNI);
        if (Objects.isNull(client)) throw new ClientNotFound();

        return client;
    }
}
