package co.com.sofka.bank.domain.clientinfo;

public class AccountNotFound extends RuntimeException {
    public static String CODE = "CLT-02";
    @Override
    public String getMessage() {
        return "Account not found";
    }
}
