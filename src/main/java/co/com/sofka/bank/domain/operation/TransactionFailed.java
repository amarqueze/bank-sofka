package co.com.sofka.bank.domain.operation;

public class TransactionFailed extends RuntimeException {
    public static String CODE = "OPE-01";

    @Override
    public String getMessage() {
        return "Internal error processing transaction";
    }
}
