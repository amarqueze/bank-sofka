package co.com.sofka.bank;

import co.com.sofka.bank.infrastructure.drivenadapters.h2.H2AuthProvider;
import co.com.sofka.bank.infrastructure.drivenadapters.h2.H2BankingInformationProvider;
import co.com.sofka.bank.infrastructure.drivenadapters.h2.H2CashDepositProcessor;
import co.com.sofka.bank.infrastructure.drivenadapters.h2.H2TransferDepositProcessor;
import co.com.sofka.bank.usecase.AccountDepositor;
import co.com.sofka.bank.usecase.AccountDetailsFinder;
import co.com.sofka.bank.usecase.ClientAuthenticator;
import co.com.sofka.bank.usecase.ClientFinder;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.GetMapping;

@SpringBootApplication
public class BankApplication {

	public static void main(String[] args) {
		SpringApplication.run(BankApplication.class, args);
	}

	@Bean
	public ClientAuthenticator getClientAuthenticator(H2AuthProvider provider) {
		return new ClientAuthenticator(provider);
	}

	@Bean
	public ClientFinder getClientFinder(H2BankingInformationProvider provider) {
		return new ClientFinder(provider);
	}

	@Bean
	public AccountDetailsFinder getAccountDetailsFinder(H2BankingInformationProvider provider) {
		return new AccountDetailsFinder(provider);
	}

	@Bean
	public AccountDepositor getAccountDepositor(H2CashDepositProcessor cashDepositProcessor, H2TransferDepositProcessor transferDepositProcessor) {
		return new AccountDepositor(cashDepositProcessor, transferDepositProcessor);
	}
}
