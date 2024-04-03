package co.com.sofka.bank.domain.client;

public class ClientNotFound extends RuntimeException {
    public static String CODE = "CLT-01";
    @Override
    public String getMessage() {
        return "Client not found";
    }
}
