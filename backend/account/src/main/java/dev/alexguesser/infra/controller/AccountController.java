package dev.alexguesser.infra.controller;


import dev.alexguesser.application.usecase.GetAccount;
import dev.alexguesser.application.usecase.Signup;
import dev.alexguesser.infra.controller.dto.AccountDetailDto;
import dev.alexguesser.infra.controller.dto.CreateAccountDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/account")
public class AccountController {

    @Autowired
    private GetAccount getAccount;

    @Autowired
    private Signup signup;

    @GetMapping("/{uuid}")
    public ResponseEntity<AccountDetailDto> getAccountById(@PathVariable String uuid) {
        return ResponseEntity.ok(
                AccountDetailDto.from(
                        getAccount.execute(UUID.fromString(uuid))
                )
        );
    }

    @PostMapping("/signup")
    public ResponseEntity<AccountDetailDto> signup(@RequestBody CreateAccountDto dto) {
        return ResponseEntity.ok(
                AccountDetailDto.from(
                        signup.execute(
                                CreateAccountDto.toInput(dto)
                        )
                )
        );
    }

}
