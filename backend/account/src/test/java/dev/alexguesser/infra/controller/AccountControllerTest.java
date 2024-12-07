package dev.alexguesser.infra.controller;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.List;
import java.util.UUID;

import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.fasterxml.jackson.databind.ObjectMapper;

import dev.alexguesser.domain.vo.PasswordType;
import dev.alexguesser.infra.controller.dto.CreateAccountDto;
import dev.alexguesser.infra.persistence.AccountEntity;
import dev.alexguesser.infra.persistence.AccountRepository;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK)
@ExtendWith(SpringExtension.class)
@AutoConfigureMockMvc
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class AccountControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private AccountRepository accountRepository;

    @Test
    @Order(1)
    void signup_shouldCreateNewAccount() throws Exception {
        assertThat(accountRepository.findAll()).isEmpty();
        ObjectMapper mapper = new ObjectMapper();
        mockMvc.perform(post("/account/signup")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(mapper.writeValueAsString(
                                new CreateAccountDto(
                                        "John Doe", "john.doe@gmail.com", "97456321558", "", "12345678", PasswordType.TEXT_PLAIN, false, false)
                        ))
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.content()
                        .contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.jsonPath("$.name").value("John Doe"));
        accountRepository.findAll();
        assertThat(accountRepository.findAll()).map(AccountEntity::getName).containsExactly("John Doe");
    }

    @Test
    void signup_shouldReturnBadRequestForInvalidEmail() throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        mockMvc.perform(post("/account/signup")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(mapper.writeValueAsString(
                                new CreateAccountDto(
                                        "John Doe", "john.doe", "97456321558", "", "12345678", PasswordType.TEXT_PLAIN, false, false)
                        ))
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());
    }

    @Test
    @Order(2)
    void getAccountById_shouldReturnTheCorrectAccount() throws Exception {
        List<AccountEntity> allAccounts = accountRepository.findAll();
        assertThat(allAccounts).map(AccountEntity::getName).containsExactly("John Doe");
        mockMvc.perform(MockMvcRequestBuilders.get("/account/" + allAccounts.get(0).getAccountId()))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.content()
                        .contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.jsonPath("$.name").value("John Doe"));
    }

    @Test
    void getAccountById_shouldReturnNotFoundForInvalidId() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/account/" + UUID.randomUUID()))
                .andExpect(status().isNotFound());
    }

}