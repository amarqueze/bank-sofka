package co.com.sofka.bank.domain.clientinfo;

import static co.com.sofka.bank.domain.util.ArgumentChecker.checkIsValidArgument;
import static co.com.sofka.bank.domain.util.ArgumentChecker.checkIsValidString;

public record AccountNumber(String number) {
    public AccountNumber {
        checkIsValidString(number, "AccountNumber is required");
        checkIsValidArgument(accountNumber -> accountNumber.length() <= 9, number, "AccountNumber cannot be greater than 9");
    }
}
