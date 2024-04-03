package co.com.sofka.bank.infrastructure.drivenadapters.h2.model;

import jakarta.persistence.*;
import lombok.Data;

@Table(name = "users")
@Entity
@Data
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nickname;
    private String password;

    @OneToOne
    @JoinColumn(name = "client_id")
    private Client client;
}
