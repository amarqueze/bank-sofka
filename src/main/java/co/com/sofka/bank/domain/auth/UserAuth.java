package co.com.sofka.bank.domain.auth;

import lombok.Getter;

@Getter
public class UserAuth {
    private String nickname;
    private String password;

    public static UserAuth newSession(String nickname, String password) {
        return new UserAuth(nickname, password);
    }

    public UserAuth(String nickname, String password) {
        /* TODO Validation */
        this.nickname = nickname;
        this.password = password;
    }
}
