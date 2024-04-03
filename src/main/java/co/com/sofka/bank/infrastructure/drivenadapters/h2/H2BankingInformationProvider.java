package co.com.sofka.bank.infrastructure.drivenadapters.h2;

import co.com.sofka.bank.domain.client.*;
import co.com.sofka.bank.infrastructure.drivenadapters.h2.repository.AccountRepository;
import co.com.sofka.bank.infrastructure.drivenadapters.h2.repository.ClientRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class H2BankingInformationProvider implements BankingInformationProvider {
    private final ClientRepository clientRepository;
    private final AccountRepository accountRepository;

    @Override
    public Client findClient(ClientDNI clientDNI) {
        co.com.sofka.bank.infrastructure.drivenadapters.h2.model.Client clientDAO
                = clientRepository.findByDni(clientDNI.dni());

        return Optional.ofNullable(clientDAO)
                .map(c -> Client.builder()
                        .dni(c.getDni())
                        .name(c.getName())
                        .lastName(c.getLastName())
                        .address(c.getAddress())
                        .phone(c.getPhone())
                        .accounts(c.getAccounts().stream()
                                .map(a -> new Account(a.getAccountNumber(), a.getStatus()))
                                .toList()
                        )
                        .build()
                )
                .orElse(null);
    }

    @Override
    public AccountDetails findAccount(AccountNumber accountNumber) {
        co.com.sofka.bank.infrastructure.drivenadapters.h2.model.Account accountDAO
                = accountRepository.findByAccountNumber(accountNumber.number());

        return Optional.ofNullable(accountDAO)
                .map(a -> AccountDetails.builder()
                        .accountNumber(a.getAccountNumber())
                        .balance(a.getBalance())
                        .status(a.getStatus())
                        .build()
                )
                .orElse(null);
    }
}
