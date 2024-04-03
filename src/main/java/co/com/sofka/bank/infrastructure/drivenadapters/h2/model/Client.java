package co.com.sofka.bank.infrastructure.drivenadapters.h2.model;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Table(name = "clients")
@Entity
@Data
public class Client {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique = true)
    private String dni;
    private String name;
    private String lastName;
    private String address;
    private String phone;

    @OneToMany(mappedBy = "client")
    private List<Account> accounts;
}
