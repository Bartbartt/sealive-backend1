package org.example.controller;

import org.example.model.Account;
import org.example.repository.AccountRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDate;
import java.util.List;

import static org.hamcrest.Matchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
public class AccountControllerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private AccountRepository accountRepository;

    private Account account;

    @BeforeEach
    public void setUp() {
        // Find all accounts with the username "testuser"
        //List<Account> testUsers = accountRepository.findByUsername("testuser");

        // Delete each account found
        //for (Account testUser : testUsers) {
        //    accountRepository.delete(testUser);
        //}

        // Create a sample account
        account = new Account();
        account.setUsername("testuser");
        account.setBiography("Test biography");
        account.setBirthday(LocalDate.of(2000, 1, 1));
    }

    @Test
    public void testCreateAccount() throws Exception {
        mockMvc.perform(post("/account/create")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"username\":\"testuser\",\"biography\":\"Test biography\",\"birthday\":\"2000-01-01\"}"))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.username", is("testuser")))
                .andExpect(jsonPath("$.biography", is("Test biography")))
                .andExpect(jsonPath("$.birthday", is("2000-01-01")));
    }

    @Test
    public void testGetAllAccounts() throws Exception {
        // First, create an account to retrieve
        accountRepository.save(account);

        mockMvc.perform(get("/account/getAll")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].username", is("testuser")))
                .andExpect(jsonPath("$[0].biography", is("Test biography")))
                .andExpect(jsonPath("$[0].birthday", is("2000-01-01")));
    }
}
