package co.com.sofka.bank.domain.auth;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class Client {
    private String dni;
    private String name;
    private String lastName;
    private String address;
    private String phone;

    public Client(String dni, String name, String lastName, String address) {
        /* TODO validaciones*/
        this.dni = dni;
        this.name = name;
        this.lastName = lastName;
        this.address = address;
    }
}
