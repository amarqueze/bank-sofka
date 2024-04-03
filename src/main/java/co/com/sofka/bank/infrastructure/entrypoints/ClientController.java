package co.com.sofka.bank.infrastructure.entrypoints;

import co.com.sofka.bank.domain.client.Client;
import co.com.sofka.bank.domain.client.ClientDNI;
import co.com.sofka.bank.usecase.ClientFinder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/client")
public class ClientController {
    private final ClientFinder clientFinder;

    @Autowired
    public ClientController(ClientFinder clientFinder) {
        this.clientFinder = clientFinder;
    }

    @GetMapping("/{dni}")
    public Client findClient(@PathVariable String dni) {
        return clientFinder.find(new ClientDNI(dni));
    }
}
