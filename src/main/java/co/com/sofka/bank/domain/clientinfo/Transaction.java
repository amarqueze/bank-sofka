package co.com.sofka.bank.domain.clientinfo;

import lombok.Builder;

import java.math.BigDecimal;
import java.util.Date;

@Builder
public record Transaction(String description, BigDecimal amount, String Operation, Date date) {
}
