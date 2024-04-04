package co.com.sofka.bank.domain.clientinfo;

public class ClientNotFound extends RuntimeException {
    public static String CODE = "CLT-01";
    @Override
    public String getMessage() {
        return "Client not found";
    }
}
