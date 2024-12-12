package dev.alexguesser.account.infra.controller.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import dev.alexguesser.account.domain.entity.Account;
import dev.alexguesser.account.domain.vo.CarPlate;
import jakarta.annotation.Nullable;

@JsonIgnoreProperties(ignoreUnknown = true)
public record AccountDetailDto(
        String accountId,
        String name,
        String email,
        String cpf,
        @Nullable String carPlate,
        @JsonProperty("isPassenger")
        boolean isPassenger,
        @JsonProperty("isDriver")
        boolean isDriver
) {

    public static AccountDetailDto from(Account account) {
        return new AccountDetailDto(
                account.getAccountId().toString(),
                account.getName().value(),
                account.getEmail().value(),
                account.getCpf().value(),
                account.getCarPlate().map(CarPlate::value).orElse(null),
                account.isPassenger(),
                account.isDriver()
        );
    }

}
