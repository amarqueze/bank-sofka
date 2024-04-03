package co.com.sofka.bank.infrastructure.drivenadapters.h2;

import co.com.sofka.bank.domain.auth.AuthProvider;
import co.com.sofka.bank.domain.auth.Client;
import co.com.sofka.bank.domain.auth.UserAuth;
import co.com.sofka.bank.infrastructure.drivenadapters.h2.model.User;
import co.com.sofka.bank.infrastructure.drivenadapters.h2.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class H2AuthProvider implements AuthProvider {
    private final UserRepository userRepository;
    @Override
    public Client authenticate(UserAuth userAuth) {
        User user = userRepository.findByNicknameAndPassword(
                userAuth.getNickname(),
                userAuth.getPassword()
        );

        return Optional.ofNullable(user)
                .map(u -> Client.builder()
                        .dni(u.getClient().getDni())
                        .name(u.getClient().getName())
                        .build()
                )
                .orElse(null);
    }
}
