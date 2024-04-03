package co.com.sofka.bank.infrastructure.entrypoints.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ResponseException {
    String code;
    String msg;
}
