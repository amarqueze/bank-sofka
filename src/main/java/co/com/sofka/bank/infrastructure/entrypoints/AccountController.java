package co.com.sofka.bank.infrastructure.entrypoints;

import co.com.sofka.bank.domain.clientinfo.AccountDetails;
import co.com.sofka.bank.domain.clientinfo.AccountNumber;
import co.com.sofka.bank.usecase.AccountDetailsFinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/account")
public class AccountController {
    private final AccountDetailsFinder accountDetailsFinder;

    public AccountController(AccountDetailsFinder accountDetailsFinder) {
        this.accountDetailsFinder = accountDetailsFinder;
    }

    @GetMapping("/{accountNumber}")
    public AccountDetails findClient(@PathVariable String accountNumber) {
        return accountDetailsFinder.find(new AccountNumber(accountNumber));
    }
}
