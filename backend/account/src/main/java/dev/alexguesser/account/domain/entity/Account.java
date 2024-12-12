package dev.alexguesser.account.domain.entity;

import dev.alexguesser.account.domain.vo.*;
import jakarta.annotation.Nullable;

import java.util.Optional;
import java.util.UUID;

public class Account {

    private UUID accountId;
    private Name name;
    private Email email;
    private Cpf cpf;
    private @Nullable CarPlate carPlate;
    private Password password;
    private boolean isPassenger;
    private boolean isDriver;

    private Account(UUID accountId, Name name, Email email, Cpf cpf, @Nullable CarPlate carPlate, Password password, boolean isPassenger, boolean isDriver) {
        this.accountId = accountId;
        this.name = name;
        this.email = email;
        this.cpf = cpf;
        this.carPlate = isDriver ? carPlate : null;
        this.password = password;
        this.isPassenger = isPassenger;
        this.isDriver = isDriver;
    }

    public Account(UUID accountId, String name, String email, String cpf, @Nullable String carPlate, String password, PasswordType passwordType, boolean isPassenger, boolean isDriver) {
        this(
                accountId,
                new Name(name),
                new Email(email),
                new Cpf(cpf),
                isDriver ? new CarPlate(carPlate) : null,
                PasswordFactory.create(password, passwordType),
                isPassenger,
                isDriver
        );
    }

    static public Account createAccount(String name, String email, String cpf, @Nullable String carPlate, String password, PasswordType passwordType, boolean isPassenger, boolean isDriver) {
        return new Account(
                UUID.randomUUID(),
                new Name(name),
                new Email(email),
                new Cpf(cpf),
                isDriver ? new CarPlate(carPlate) : null,
                PasswordFactory.create(password, passwordType),
                isPassenger,
                isDriver
        );
    }

    public UUID getAccountId() {
        return accountId;
    }

    public Name getName() {
        return name;
    }

    public Email getEmail() {
        return email;
    }

    public Cpf getCpf() {
        return cpf;
    }

    public Optional<CarPlate> getCarPlate() {
        return Optional.ofNullable(carPlate);
    }

    public Password getPassword() {
        return password;
    }

    public boolean isPassenger() {
        return isPassenger;
    }

    public boolean isDriver() {
        return isDriver;
    }
}
