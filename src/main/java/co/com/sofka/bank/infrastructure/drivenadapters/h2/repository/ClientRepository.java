package co.com.sofka.bank.infrastructure.drivenadapters.h2.repository;

import co.com.sofka.bank.infrastructure.drivenadapters.h2.model.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ClientRepository extends JpaRepository<Client, Long> {
    @Query("SELECT c FROM Client c LEFT JOIN FETCH c.accounts WHERE c.dni = :dni")
    Client findByDni(String dni);
}
