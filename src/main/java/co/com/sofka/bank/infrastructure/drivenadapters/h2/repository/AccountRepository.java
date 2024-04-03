package co.com.sofka.bank.infrastructure.drivenadapters.h2.repository;


import co.com.sofka.bank.infrastructure.drivenadapters.h2.model.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountRepository extends JpaRepository<Account, Long> {
    public Account findByAccountNumber(String accountNumber);
}
