package co.com.sofka.bank.usecase;

import co.com.sofka.bank.domain.auth.AuthProvider;
import co.com.sofka.bank.domain.auth.AuthenticationFailed;
import co.com.sofka.bank.domain.auth.Client;
import co.com.sofka.bank.domain.auth.UserAuth;
import lombok.RequiredArgsConstructor;

import java.util.Objects;

@RequiredArgsConstructor
public class ClientAuthenticator {
    private final AuthProvider authProvider;

    public Client authenticate(UserAuth userAuth) {
        Client client = authProvider.authenticate(userAuth);
        if(Objects.isNull(client))
            throw new AuthenticationFailed();

        return client;
    }
}
