package co.com.sofka.bank.infrastructure.entrypoints;

import co.com.sofka.bank.domain.auth.Client;
import co.com.sofka.bank.infrastructure.entrypoints.dto.LoginRequest;
import co.com.sofka.bank.usecase.ClientAuthenticator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
public class AuthController {
    private final ClientAuthenticator clientAuthenticator;

    @Autowired
    public AuthController(ClientAuthenticator clientAuthenticator) {
        this.clientAuthenticator = clientAuthenticator;
    }

    @PostMapping
    public Client authenticate(@RequestBody LoginRequest loginRequest) {
        return clientAuthenticator.authenticate(loginRequest.toUserAuth());
    }

}
