package co.com.sofka.bank.infrastructure.entrypoints;

import co.com.sofka.bank.domain.auth.AuthenticationFailed;
import co.com.sofka.bank.domain.client.AccountNotFound;
import co.com.sofka.bank.domain.client.ClientNotFound;
import co.com.sofka.bank.infrastructure.entrypoints.dto.ResponseException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(AuthenticationFailed.class)
    public ResponseEntity<ResponseException> handleException(AuthenticationFailed e) {
        return new ResponseEntity<>(
                ResponseException.builder()
                        .code(AuthenticationFailed.CODE)
                        .msg(e.getMessage())
                        .build(),
                HttpStatus.UNAUTHORIZED);
    }

    @ExceptionHandler(ClientNotFound.class)
    public ResponseEntity<ResponseException> handleException(ClientNotFound e) {
        return new ResponseEntity<>(
                ResponseException.builder()
                        .code(ClientNotFound.CODE)
                        .msg(e.getMessage())
                        .build(),
                HttpStatus.UNAUTHORIZED);
    }

    @ExceptionHandler(AccountNotFound.class)
    public ResponseEntity<ResponseException> handleException(AccountNotFound e) {
        return new ResponseEntity<>(
                ResponseException.builder()
                        .code(AccountNotFound.CODE)
                        .msg(e.getMessage())
                        .build(),
                HttpStatus.UNAUTHORIZED);
    }

}
