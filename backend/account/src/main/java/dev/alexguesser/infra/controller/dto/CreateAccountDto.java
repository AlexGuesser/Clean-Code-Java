package dev.alexguesser.infra.controller.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import dev.alexguesser.application.usecase.Signup;
import dev.alexguesser.domain.vo.PasswordType;

@JsonIgnoreProperties(ignoreUnknown = true)
public record CreateAccountDto(
        String name,
        String email,
        String cpf,
        String carPlate,
        String password,
        PasswordType passwordType,
        @JsonProperty("isPassenger")
        boolean isPassenger,
        @JsonProperty("isDriver")
        boolean isDriver
) {
    public static Signup.SignupInput toInput(CreateAccountDto dto) {
        return new Signup.SignupInput(
                dto.name(),
                dto.email(),
                dto.cpf(),
                dto.carPlate(),
                dto.password(),
                dto.passwordType(),
                dto.isPassenger(),
                dto.isDriver()
        );
    }
}
