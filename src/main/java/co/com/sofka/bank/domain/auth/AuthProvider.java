package co.com.sofka.bank.domain.auth;

public interface AuthProvider {
    Client authenticate(UserAuth userAuth);
}
