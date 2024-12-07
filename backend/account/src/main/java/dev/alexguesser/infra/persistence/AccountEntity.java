package dev.alexguesser.infra.persistence;

import dev.alexguesser.domain.vo.PasswordType;
import jakarta.persistence.*;

import java.util.UUID;

@Entity
@Table(name = "account")
public class AccountEntity {

    @Id
    @Column(name = "account_id")
    private UUID accountId;
    private String name;
    private String email;
    private String cpf;
    @Column(name = "car_plate", nullable = true)
    private String carPlate;
    private String password;
    @Column(name = "password_type")
    @Enumerated(EnumType.STRING)
    private PasswordType passwordType;
    @Column(name = "is_passenger")
    private boolean isPassenger;
    @Column(name = "is_driver")
    private boolean isDriver;

    public AccountEntity(UUID accountId, String name, String email, String cpf, String carPlate, String password, PasswordType passwordType, boolean isPassenger, boolean isDriver) {
        this.accountId = accountId;
        this.name = name;
        this.email = email;
        this.cpf = cpf;
        this.carPlate = carPlate;
        this.password = password;
        this.passwordType = passwordType;
        this.isPassenger = isPassenger;
        this.isDriver = isDriver;
    }

    private AccountEntity() {

    }

    public UUID getAccountId() {
        return accountId;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getCpf() {
        return cpf;
    }

    public String getCarPlate() {
        return carPlate;
    }

    public String getPassword() {
        return password;
    }

    public PasswordType getPasswordType() {
        return passwordType;
    }

    public boolean isPassenger() {
        return isPassenger;
    }

    public boolean isDriver() {
        return isDriver;
    }
}
