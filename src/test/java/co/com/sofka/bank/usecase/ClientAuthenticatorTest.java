package co.com.sofka.bank.usecase;

import co.com.sofka.bank.domain.auth.AuthProvider;
import co.com.sofka.bank.domain.auth.AuthenticationFailed;
import co.com.sofka.bank.domain.auth.Client;
import co.com.sofka.bank.domain.auth.UserAuth;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@SpringBootTest
class ClientAuthenticatorTest {
    @Mock
    private AuthProvider authProvider;
    @InjectMocks
    private ClientAuthenticator clientAuthenticator;

    @Test
    void testAuthenticateSuccessful() {
        UserAuth userAuth = new UserAuth("name.lastname", "password");
        Client expectedClient = Client.builder()
                .dni("123456789")
                .name("John")
                .build();

        when(authProvider.authenticate(userAuth)).thenReturn(expectedClient);
        Client actualClient = clientAuthenticator.authenticate(userAuth);

        assertNotNull(actualClient);
        assertEquals(expectedClient, actualClient);
    }

    @Test
    void testAuthenticateFailed() {
        UserAuth userAuth = new UserAuth("name.lastname", "password");
        when(authProvider.authenticate(userAuth)).thenReturn(null);

        assertThrows(AuthenticationFailed.class, () -> clientAuthenticator.authenticate(userAuth));
    }
}
