package co.com.sofka.bank.domain.auth;

import static co.com.sofka.bank.domain.util.ArgumentChecker.checkIsValidString;

public record UserAuth(String nickname, String password) {
    public static UserAuth newSession(String nickname, String password) {
        return new UserAuth(nickname, password);
    }

    public UserAuth {
        checkIsValidString(nickname, "nickname is required");
        checkIsValidString(password, "password is required");
    }
}
