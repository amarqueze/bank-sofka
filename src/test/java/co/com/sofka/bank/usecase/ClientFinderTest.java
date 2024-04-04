package co.com.sofka.bank.usecase;

import co.com.sofka.bank.domain.clientinfo.BankingInformationProvider;
import co.com.sofka.bank.domain.clientinfo.Client;
import co.com.sofka.bank.domain.clientinfo.ClientDNI;
import co.com.sofka.bank.domain.clientinfo.ClientNotFound;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@SpringBootTest
class ClientFinderTest {
    @Mock
    private BankingInformationProvider bankingInformationProvider;
    @InjectMocks
    private ClientFinder clientFinder;

    @Test
    void testFindClient() {
        ClientDNI clientDNI = new ClientDNI("12345678A");
        Client expectedClient = Client.builder()
                .dni("123456789")
                .name("John")
                .lastName("Doe")
                .phone("28291")
                .address("CLL 35A")
                .accounts(new ArrayList<>())
                .build();
        when(bankingInformationProvider.findClient(clientDNI)).thenReturn(expectedClient);
        Client actualClient = clientFinder.find(clientDNI);
        assertNotNull(actualClient);
        assertEquals(expectedClient, actualClient);
    }

    @Test
    void testFindClientNotFound() {
        ClientDNI clientDNI = new ClientDNI("223456789");
        when(bankingInformationProvider.findClient(clientDNI)).thenReturn(null);
        assertThrows(ClientNotFound.class, () -> clientFinder.find(clientDNI));
    }
}
