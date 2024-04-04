package co.com.sofka.bank.domain.clientinfo;

import static co.com.sofka.bank.domain.util.ArgumentChecker.checkIsValidString;

public record ClientDNI(String dni) {
    public ClientDNI {
        checkIsValidString(dni, "dni is required");
    }
}
