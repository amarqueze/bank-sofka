package co.com.sofka.bank.infrastructure.entrypoints.dto;

import co.com.sofka.bank.domain.auth.UserAuth;
import lombok.Data;

@Data
public class LoginRequest {
    private String nickname;
    private String password;

    public UserAuth toUserAuth() {
        return UserAuth.newSession(nickname, password);
    }
}
