package co.com.sofka.bank.infrastructure.drivenadapters.h2.repository;

import co.com.sofka.bank.infrastructure.drivenadapters.h2.model.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction, Long> {
    @Query("SELECT t FROM Transaction t WHERE t.sourceAccount = :accountNumber OR t.destinationAccount = :accountNumber")
    List<Transaction> findByAccountNumber(@Param("accountNumber") String accountNumber);
}
