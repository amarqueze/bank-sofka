package co.com.sofka.bank.domain.clientinfo;

import lombok.Builder;

import java.util.List;

@Builder
public record Client(String dni, String name, String lastName, String address, String phone, List<Account> accounts) {
}
