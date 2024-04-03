package co.com.sofka.bank.domain.auth;

public class AuthenticationFailed extends RuntimeException {
    public static String CODE = "AUTH-01";
    @Override
    public String getMessage() {
        return "nickname or password is invalid";
    }
}
