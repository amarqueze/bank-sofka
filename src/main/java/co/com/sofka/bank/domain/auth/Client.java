package co.com.sofka.bank.domain.auth;

import lombok.Builder;

import static co.com.sofka.bank.domain.util.ArgumentChecker.checkIsValidString;

@Builder
public record Client(String dni, String name) {
    public Client {
        checkIsValidString(dni, "dni is required");
        checkIsValidString(name, "name is required");
    }
}
