package co.com.sofka.bank.infrastructure.drivenadapters.h2;

import co.com.sofka.bank.domain.auth.UserAuth;
import co.com.sofka.bank.infrastructure.drivenadapters.h2.model.Client;
import co.com.sofka.bank.infrastructure.drivenadapters.h2.model.User;
import co.com.sofka.bank.infrastructure.drivenadapters.h2.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.Mockito.when;

@SpringBootTest
class H2AuthProviderTest {
    @Mock
    private UserRepository userRepository;
    @InjectMocks
    private H2AuthProvider h2AuthProvider;

    @Test
    void testAuthenticate_Success() {
        UserAuth userAuth = new UserAuth("name.lastname", "password");
        User user = new User();
        Client client = new Client();
        client.setDni("123456789");
        client.setName("John");
        user.setClient(client);
        when(userRepository.findByNicknameAndPassword(userAuth.nickname(), userAuth.password())).thenReturn(user);

        co.com.sofka.bank.domain.auth.Client clientResult = h2AuthProvider.authenticate(userAuth);
        assertEquals("123456789", clientResult.dni());
        assertEquals("John", clientResult.name());
    }

    @Test
    void testAuthenticate_UserNotFound() {
        UserAuth userAuth = new UserAuth("username", "password");
        when(userRepository.findByNicknameAndPassword(userAuth.nickname(), userAuth.password())).thenReturn(null);

        co.com.sofka.bank.domain.auth.Client client = h2AuthProvider.authenticate(userAuth);
        assertNull(client);
    }
}
