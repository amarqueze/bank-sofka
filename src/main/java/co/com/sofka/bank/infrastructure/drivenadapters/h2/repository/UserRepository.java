package co.com.sofka.bank.infrastructure.drivenadapters.h2.repository;

import co.com.sofka.bank.infrastructure.drivenadapters.h2.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    @Query("SELECT u FROM User u JOIN FETCH u.client WHERE u.nickname = :nickname AND u.password = :password")
    User findByNicknameAndPassword(String nickname, String password);
}
