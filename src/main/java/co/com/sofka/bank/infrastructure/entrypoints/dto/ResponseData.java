package co.com.sofka.bank.infrastructure.entrypoints.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ResponseData {
    private String status;
    private String msg;
}
