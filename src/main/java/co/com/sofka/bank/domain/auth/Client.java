package co.com.sofka.bank.domain.auth;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class Client {
    private String dni;
    private String name;

    public Client(String dni, String name) {
        /* TODO validaciones*/
        this.dni = dni;
        this.name = name;
    }
}
